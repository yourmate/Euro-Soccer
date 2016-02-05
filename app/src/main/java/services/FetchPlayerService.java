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

public class FetchPlayerService extends Service {
    public static final String LOG_TAG = FetchPlayerService.class.getSimpleName();

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                String playersData;
                try {
                    String id = intent.getStringExtra("Id");
                    URL url = new URL("http://api.football-data.org/v1/teams/"+ id +"/players");
                    urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("X-Auth-Token", "YOUR_KEY");
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    if(inputStream == null)
                    {
                        return ;
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
                    playersData = buffer.toString();
                    getPlayersDataFromJSON(playersData);
                }catch (IOException e)
                {
                    Log.e(LOG_TAG,"Error: I/OException");
                }
                finally {
                    if(urlConnection != null)
                    {
                        urlConnection.disconnect();
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

    public void getPlayersDataFromJSON(String data)
    {
        try {
            String name;
            String position;
            String jerseyNumber;
            String dateOfBirth;
            String nationality;
            String contractUntil;
            String marketValue;

            JSONObject jsonObject = new JSONObject(data);
            JSONArray playerArray = jsonObject.getJSONArray("players");
            String[] finalResult = new String[playerArray.length()];
            Vector<ContentValues> contentValuesVector = new Vector<>();
            for(int i=0;i<playerArray.length();i++)
            {
                JSONObject object = playerArray.getJSONObject(i);
                name = object.getString("name");
                position = object.getString("position");
                jerseyNumber = object.getString("jerseyNumber");
                dateOfBirth = object.getString("dateOfBirth");
                nationality = object.getString("nationality");
                contractUntil = object.getString("contractUntil");
                marketValue = object.getString("marketValue");
                ContentValues values = new ContentValues();
                values.put(SoccerContract.PlayersEntry.PLAYER_NAME,name);
                values.put(SoccerContract.PlayersEntry.PLAYER_POSITION,position);
                values.put(SoccerContract.PlayersEntry.PLAYER_JERSEY_NUMBER,jerseyNumber);
                values.put(SoccerContract.PlayersEntry.PLAYER_DATE_OF_BIRTH,dateOfBirth);
                values.put(SoccerContract.PlayersEntry.PLAYER_NATIONALITY,nationality);
                values.put(SoccerContract.PlayersEntry.PLAYER_CONTRACT_UNTIL,contractUntil);
                values.put(SoccerContract.PlayersEntry.PLAYER_MARKET_VALUE,marketValue);

                contentValuesVector.add(values);
            }
            if(contentValuesVector.size() > 0)
            {
                ContentValues[] contentValues = new ContentValues[contentValuesVector.size()];
                contentValuesVector.toArray(contentValues);

                getContentResolver().delete(SoccerContract.PlayersEntry.CONTENT_URI, null, null);

                getContentResolver().bulkInsert(SoccerContract.PlayersEntry.CONTENT_URI,contentValues);
            }

        }catch (JSONException e)
        {
            Log.e(LOG_TAG, "Error: JSONException");
        }
    }
}
