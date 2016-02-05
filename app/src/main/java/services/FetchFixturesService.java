package services;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hahaha.sandy.soccer.database.SoccerContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

public class FetchFixturesService extends Service {
    public static final String LOG_TAG = FetchFixturesService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                String fixtureData = null;
                try {
                    String id = intent.getStringExtra("Id");
                    URL mUrl = new URL("http://api.football-data.org/v1/soccerseasons/" + id + "/fixtures");
                    httpURLConnection = (HttpURLConnection)mUrl.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("X-Auth-Token", "YOUR_KEY");
                    httpURLConnection.connect();
                    InputStream mInputStream = httpURLConnection.getInputStream();
                    if(mInputStream == null)
                    {
                        return ;
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(mInputStream));
                    String feed;
                    StringBuffer stringBuffer = new StringBuffer();
                    while((feed = bufferedReader.readLine()) != null)
                    {
                        stringBuffer.append(feed + "\n");
                    }
                    if(stringBuffer == null)
                    {
                        return ;
                    }
                    fixtureData = stringBuffer.toString();
                    getFixtureDataFromJSON(fixtureData);
                }catch (IOException e)
                {
                    Log.e(LOG_TAG, "Error: I/O Exception");
                }finally {
                    if(httpURLConnection != null)
                    {
                        httpURLConnection.disconnect();
                    }
                    if(bufferedReader != null)
                    {
                        try {
                            bufferedReader.close();
                        }catch (IOException e)
                        {
                            Log.e(LOG_TAG,"Error: I/OException");
                        }
                    }
                }
            }
        }.start();

        return START_NOT_STICKY;
    }

    public String[] getFixtureDataFromJSON(String fixtureData)
    {
        try {
            String date;
            String status;
            String homeTeam;
            String awayTeam;
            String goalHomeTeam;
            String goalAwayTeam;
            String id;
            String id1;
            String h_id;
            String a_id;
            Vector<ContentValues> contentValuesVector = new Vector<>();
            JSONObject mObject = new JSONObject(fixtureData);
            JSONArray fixture = mObject.getJSONArray("fixtures");
            for(int i=0;i<fixture.length();i++)
            {
                JSONObject fixtureObject = fixture.getJSONObject(i);
                JSONObject result = fixtureObject.getJSONObject("result");
                JSONObject links = fixtureObject.getJSONObject("_links");
                JSONObject homeLink = links.getJSONObject("homeTeam");
                JSONObject awayLink = links.getJSONObject("awayTeam");
                id = homeLink.getString("href");
                String[] iden;
                iden = id.split("/");
                h_id = iden[5];
                id1 = awayLink.getString("href");
                String[] iden2;
                iden2 = id1.split("/");
                a_id = iden2[5];
                ContentValues contentValues = new ContentValues();
                goalHomeTeam = result.getString("goalsHomeTeam");
                goalAwayTeam = result.getString("goalsAwayTeam");
                date = fixtureObject.getString("date");
                StringTokenizer tokenizer = new StringTokenizer(date,"T");
                String dateString = tokenizer.nextToken();
                String Time = tokenizer.nextToken();
                StringTokenizer tokenizer1 = new StringTokenizer(Time,"Z");
                String timeStamp = tokenizer1.nextToken();
                status = fixtureObject.getString("status");
                homeTeam = fixtureObject.getString("homeTeamName");
                awayTeam = fixtureObject.getString("awayTeamName");

                contentValues.put(SoccerContract.FixtureEntry.HOME_TEAM_NAME,homeTeam);
                contentValues.put(SoccerContract.FixtureEntry.AWAY_TEAM_NAME,awayTeam);
                contentValues.put(SoccerContract.FixtureEntry.GOAL_HOME_TEAM,goalHomeTeam);
                contentValues.put(SoccerContract.FixtureEntry.GOAL_AWAY_TEAM,goalAwayTeam);
                contentValues.put(SoccerContract.FixtureEntry.GAME_STATUS,status);
                contentValues.put(SoccerContract.FixtureEntry.HOME_TEAM_ID,h_id);
                contentValues.put(SoccerContract.FixtureEntry.AWAY_TEAM_ID,a_id);
                contentValues.put(SoccerContract.FixtureEntry.GAME_DATE,dateString);
                contentValues.put(SoccerContract.FixtureEntry.GAME_TIME,timeStamp);

                contentValuesVector.add(contentValues);

            }
            if(contentValuesVector.size() > 0)
            {
                ContentValues[] contentValues = new ContentValues[contentValuesVector.size()];
                contentValuesVector.toArray(contentValues);
                getContentResolver().delete(SoccerContract.FixtureEntry.CONTENT_URI, null, null);

                getContentResolver().bulkInsert(SoccerContract.FixtureEntry.CONTENT_URI, contentValues);
            }
        }catch (JSONException e)
        {
            Log.e(LOG_TAG,"Error: JSONException Error");
        }
        return null;
    }
}
