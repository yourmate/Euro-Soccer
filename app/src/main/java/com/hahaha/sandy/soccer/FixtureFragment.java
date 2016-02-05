package com.hahaha.sandy.soccer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

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
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.namespace.NamespaceContext;

import services.FetchFixturesService;

public class FixtureFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    ProgressDialog dialog;
    public static final int LOADER_ID = 1;
    public static String id = "394";
    public static final String[] FIXTURE_COLUMNS = new String[]{
            SoccerContract.FixtureEntry.TABLE_NAME + "." + SoccerContract.FixtureEntry._ID,
            SoccerContract.FixtureEntry.HOME_TEAM_NAME,
            SoccerContract.FixtureEntry.AWAY_TEAM_NAME,
            SoccerContract.FixtureEntry.GOAL_HOME_TEAM,
            SoccerContract.FixtureEntry.GOAL_AWAY_TEAM,
            SoccerContract.FixtureEntry.GAME_DATE,
            SoccerContract.FixtureEntry.GAME_TIME,
            SoccerContract.FixtureEntry.GAME_STATUS,
            SoccerContract.FixtureEntry.HOME_TEAM_ID,
            SoccerContract.FixtureEntry.AWAY_TEAM_ID,
    };
    public static final int ID_PRIMARY = 0;
    public static final int COL_HOME_TEAM = 1;
    public static final int COL_AWAY_TEAM = 2;
    public static final int COL_HOME_GOAL = 3;
    public static final int COL_AWAY_GOAL = 4;
    public static final int COL_DATE = 5;
    public static final int COL_TIME = 6;
    public static final int COL_STATUS = 7;
    public static final int COL_HOME_ID = 8;
    public static final int COL_AWAY_ID = 9;
    FixtureAdapter mFixtureAdapter;
    public static final String LOG_TAG = FixtureFragment.class.getSimpleName();

    public FixtureFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fixtures, container, false);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString(FixtureFragment.id);
        }
        AdView adView =(AdView)rootView.findViewById(R.id.fixture_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mFixtureAdapter = new FixtureAdapter(getActivity(), null, 0);
        ListView listView = (ListView) rootView.findViewById(R.id.fixture_list_view);
        listView.setAdapter(mFixtureAdapter);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_frag, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iden = item.getItemId();
        if (iden == R.id.league_table) {
            Intent intent = new Intent(getActivity(), LeagueTableActivity.class);
            intent.putExtra("Id", id);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                SoccerContract.FixtureEntry.CONTENT_URI,
                FIXTURE_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mFixtureAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mFixtureAdapter.swapCursor(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getActivity(),FetchFixturesService.class);
        intent.putExtra("Id", id);
       getActivity().startService(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().stopService(new Intent(getActivity(),FetchFixturesService.class));
    }
}



