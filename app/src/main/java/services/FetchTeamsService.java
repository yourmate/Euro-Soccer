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
import java.util.Vector;

public class FetchTeamsService extends Service {
    public static final String LOG_TAG = FetchTeamsService.class.getSimpleName();

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                BufferedReader reader = null;
                String Teams;
                try {
                    String id = intent.getStringExtra("Id");
                    URL url = new URL("http://api.football-data.org/v1/soccerseasons/"+ id +"/teams");
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("X-Auth-Token", "YOUR_KEY");
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if(inputStream == null)
                    {
                        return;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuffer buffer = new StringBuffer();
                    while((line=reader.readLine()) != null)
                    {
                        buffer.append(line+"\n");
                    }
                    if(buffer == null)
                    {
                        return ;
                    }
                    Teams = buffer.toString();
                    getTeamsFromJSON(Teams);
                }catch (IOException e)
                {
                    Log.e(LOG_TAG, "Error: I/OException");
                }
                finally {
                    if(httpURLConnection != null)
                    {
                        httpURLConnection.disconnect();
                    }
                    if(reader != null)
                    {
                        try {
                            reader.close();
                        }catch (IOException e)
                        {
                            Log.e(LOG_TAG,"Error: IOException");
                        }
                    }
                }
            }
        }.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public String[] getTeamsFromJSON(String data)
    {
        try {
            String link;
            String logo;
            String teamName;
            String marketValue;

            JSONObject mObject = new JSONObject(data);
            JSONArray array = mObject.getJSONArray("teams");
            String[] finalResult = new String[array.length()];
            Vector<ContentValues> contentValuesVector = new Vector<>();
            for(int i=0;i<array.length();i++)
            {
                JSONObject object = array.getJSONObject(i);
                JSONObject links =  object.getJSONObject("_links");
                JSONObject self = links.getJSONObject("self");
                link = self.getString("href");
                String[] split;
                split=link.split("/");
                logo = split[5];
                teamName = object.getString("name");
                marketValue = object.getString("squadMarketValue");
                ContentValues values = new ContentValues();
                values.put(SoccerContract.TeamsEntry.LOGO_ID,logo);
                values.put(SoccerContract.TeamsEntry.TEAM_NAME,teamName);
                values.put(SoccerContract.TeamsEntry.TEAM_MARKET_VALUE,marketValue);
                contentValuesVector.add(values);
            }
            if(contentValuesVector.size() > 0)
            {
                ContentValues[] contentValues = new ContentValues[contentValuesVector.size()];
                contentValuesVector.toArray(contentValues);

                getContentResolver().delete(SoccerContract.TeamsEntry.CONTENT_URI, null, null);

                getContentResolver().bulkInsert(SoccerContract.TeamsEntry.CONTENT_URI, contentValues);
            }
        }catch (JSONException e)
        {
            Log.e(LOG_TAG,"Error: JSONException");
        }
        return null;
    }
}
