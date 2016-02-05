package com.hahaha.sandy.soccer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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


public class LeagueFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    ProgressDialog dialog;
    public interface Callback
    {
        void onItemClicked(int position);
    }
    public static final int LOADER_ID = 0;
    public static final String[] LEAGUE_COLUMNS = new String[]{
            SoccerContract.LeaguesEntry.TABLE_NAME + "." + SoccerContract.LeaguesEntry._ID,
            SoccerContract.LeaguesEntry.LEAGUE_NAME,
            SoccerContract.LeaguesEntry.LEAGUE_MATCH_DAY,
            SoccerContract.LeaguesEntry.TOTAL_TEAMS,
            SoccerContract.LeaguesEntry.TOTAL_GAMES,
            SoccerContract.LeaguesEntry.LEAGUE_ID
    };
    public static final int COL_LEAGUE_PRIMARY_ID = 0;
    public static final int COL_LEAGUE_NAME = 1;
    public static final int COL_LEAGUE_MATCH_DAY = 2;
    public static final int COL_TOTAL_TEAMS = 3;
    public static final int COL_TOTAL_GAMES = 4;
    public static final int COL_LEAGUE_ID = 5;

    public static final String LOG_TAG = LeagueFragment.class.getSimpleName();
    mAdapter mAdapter;
    public LeagueFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        mAdapter = new mAdapter(getActivity(),null,0);
        ListView ls = (ListView)rootView.findViewById(R.id.leagues_listview);
        ls.setAdapter(mAdapter);
        AdView adView =(AdView)rootView.findViewById(R.id.league_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Callback) getActivity()).onItemClicked(394 + position);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getActivity().getLoaderManager().initLoader(LOADER_ID,null,this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setInverseBackgroundForced(false);
        dialog.setCancelable(true);
        dialog.show();
        FetchLeagues leagues = new FetchLeagues();
        leagues.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.about_action)
        {
            FragmentManager fragmentManager = getFragmentManager();
            DialogFragmentAbout dialogFragmentAbout = new DialogFragmentAbout();
            dialogFragmentAbout.show(fragmentManager,"About_fragment");
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                SoccerContract.LeaguesEntry.CONTENT_URI,
                LEAGUE_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
    public class FetchLeagues extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String soccerData = null;
            try {
                URL url = new URL("http://api.football-data.org/v1/soccerseasons");
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-Auth-Token", "YOUR_KEY");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null)
                {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line=reader.readLine())!= null)
                {
                    buffer.append(line+"\n");
                }
                if(buffer == null)
                {
                    return null;
                }
                soccerData = buffer.toString();
                getLeagueDataFromJSON(soccerData);
            }catch (IOException e)
            {
                Log.e(LOG_TAG, "Error: I/O Exception");
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
                        Log.e(LOG_TAG,"Error: Unable to close reader");
                    }
                }
            }
            return null;
        }
        public String[] getLeagueDataFromJSON(String leagueData)
        {   try {
            JSONArray mJsonArray = new JSONArray(leagueData);
            String id;
            String leagueName;
            String matchDay;
            String totalTeams;
            Vector<ContentValues> vector = new Vector<>();
            String totalGames;
            String[] finalResult = new String[mJsonArray.length()];
            for(int i =0;i<mJsonArray.length();i++)
            {
                JSONObject mObject = mJsonArray.getJSONObject(i);
                id = mObject.getString("id");
                leagueName = mObject.getString("caption");
                matchDay = mObject.getString("currentMatchday");
                totalTeams = mObject.getString("numberOfTeams");
                totalGames = mObject.getString("numberOfGames");
//                finalResult[i] = leagueName + " - " + matchDay + " - " + totalTeams + " - " +
//                        totalGames ;
                ContentValues contentValues = new ContentValues();
                contentValues.put(SoccerContract.LeaguesEntry.LEAGUE_ID,id);
                contentValues.put(SoccerContract.LeaguesEntry.LEAGUE_NAME,leagueName);
                contentValues.put(SoccerContract.LeaguesEntry.LEAGUE_MATCH_DAY,matchDay);
                contentValues.put(SoccerContract.LeaguesEntry.TOTAL_TEAMS,totalTeams);
                contentValues.put(SoccerContract.LeaguesEntry.TOTAL_GAMES,totalGames);
                vector.add(contentValues);
            }
            if(vector.size() > 0)
            {
                ContentValues[] content = new ContentValues[vector.size()];
                vector.toArray(content);

                getActivity().getContentResolver().delete(SoccerContract.LeaguesEntry.CONTENT_URI, null, null);

                getActivity().getContentResolver().bulkInsert(SoccerContract.LeaguesEntry.CONTENT_URI, content);

            }
        }catch (JSONException e) {
            Log.e(LOG_TAG,"Error: JSONException Error");
        }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.hide();
        }
    }
}
