package services;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
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

public class FetchLeagueTableService extends Service {
    public static final String LOG_TAG = FetchLeagueTableService.class.getSimpleName();

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
                HttpURLConnection urlConnection = null;
                BufferedReader bufferedReader = null;
                String leagueString;
                try {
                    String id = intent.getStringExtra("Id");
                    URL url = new URL("http://api.football-data.org/v1/soccerseasons/"+ id +"/leagueTable");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("X-Auth-Token", "YOUR_KEY");
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    if (inputStream == null) {
                        return ;
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuffer buffer = new StringBuffer();
                    while ((line = bufferedReader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                    if (buffer == null) {
                        return ;
                    }
                    leagueString = buffer.toString();
                    getLeagueTableFromJSON(leagueString);
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error: I/O Exception");
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e) {
                            Log.e(LOG_TAG, "Error: Unable to close reader");
                        }
                    }
                }

            }
        }.start();
        return START_NOT_STICKY;

    }
        public void getLeagueTableFromJSON(String leagueTable) {

            try {
                JSONObject jsonObject = new JSONObject(leagueTable);
                JSONArray standing = jsonObject.getJSONArray("standing");
                String position;
                String teamName;
                String playedGames;
                String points;
                String goals;
                String goalsAgainst;
                String goalDifference;
                String wins;
                String draws;
                String losses;
                String home_goals;
                String home_goalsAgainst;
                String home_wins;
                String home_draws;
                String home_losses;
                String away_goals;
                String away_goalsAgainst;
                String away_wins;
                String away_draws;
                String away_losses;
                String team_logo_id;
                String team_id;
                String[] finalResult = new String[standing.length()];
                Vector<ContentValues> valuesVector = new Vector<>();

                for (int i = 0; i < standing.length(); i++) {
                    JSONObject mObject = standing.getJSONObject(i);
                    JSONObject link = mObject.getJSONObject("_links");
                    JSONObject team = link.getJSONObject("team");
                    team_logo_id = team.getString("href");
                    JSONObject homeObject = mObject.getJSONObject("home");
                    JSONObject awayObject = mObject.getJSONObject("away");
                    position = mObject.getString("position");
                    teamName = mObject.getString("teamName");
                    playedGames = mObject.getString("playedGames");
                    points = mObject.getString("points");
                    goals = mObject.getString("goals");
                    goalsAgainst = mObject.getString("goalsAgainst");
                    goalDifference = mObject.getString("goalDifference");
                    wins = mObject.getString("wins");
                    draws = mObject.getString("draws");
                    losses = mObject.getString("losses");
                    home_goals = homeObject.getString("goals");
                    home_goalsAgainst = homeObject.getString("goalsAgainst");
                    home_wins = homeObject.getString("wins");
                    home_draws = homeObject.getString("draws");
                    home_losses = homeObject.getString("losses");
                    away_goals = awayObject.getString("goals");
                    away_goalsAgainst = awayObject.getString("goalsAgainst");
                    away_wins = awayObject.getString("wins");
                    away_draws = awayObject.getString("draws");
                    away_losses = awayObject.getString("losses");
                    String[] logo_id;
                    logo_id = team_logo_id.split("/");
                    team_id = logo_id[5];
                    ContentValues values = new ContentValues();
                    values.put(SoccerContract.LeagueTableEntry.TEAM_ID, team_id);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_NAME, teamName);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_POSITION, position);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_GAMES_PLAYED, playedGames);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_POINTS, points);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_GOALS, goals);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_GOALS_AGAINST, goalsAgainst);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_GOAL_DIFFERENCE, goalDifference);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_WINS, wins);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_DRAWS, draws);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_LOSSES, losses);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_HOME_GOALS, home_goals);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_HOME_GOAL_AGAINST, home_goalsAgainst);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_HOME_WINS, home_wins);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_HOME_DRAWS, home_draws);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_HOME_LOSSES, home_losses);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_AWAY_GOALS, away_goals);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_AWAY_GOAL_AGAINST, away_goalsAgainst);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_AWAY_WINS, away_wins);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_AWAY_DRAWS, away_draws);
                    values.put(SoccerContract.LeagueTableEntry.TEAM_AWAY_LOSSES, away_losses);
                    valuesVector.add(values);

                }
                if (valuesVector.size() > 0) {
                    ContentValues[] contentValues = new ContentValues[valuesVector.size()];
                    valuesVector.toArray(contentValues);
                    getContentResolver().delete(SoccerContract.LeagueTableEntry.CONTENT_URI,null,null);
                    getContentResolver().bulkInsert(SoccerContract.LeagueTableEntry.CONTENT_URI, contentValues);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error: JSONException");
            }
        }
}
