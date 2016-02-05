package com.hahaha.sandy.soccer;

import android.app.Fragment;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import services.FetchTeamsService;


public class TeamsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    ProgressDialog dialog;
    String id;
    public static final int LOADER_ID = 4;
    TeamAdapter mTeamAdapter;
    public static final String[] TEAM_COLUMNS = new String[]{
            SoccerContract.TeamsEntry.TABLE_NAME + "." + SoccerContract.TeamsEntry._ID,
            SoccerContract.TeamsEntry.TEAM_NAME,
            SoccerContract.TeamsEntry.LOGO_ID,
            SoccerContract.TeamsEntry.TEAM_MARKET_VALUE
    };
    public static final int COL_PRIMARY = 0;
    public static final int COL_TEAM_NAME = 1;
    public static final int COL_TEAM_LOGO = 2;
    public static final int COL_TEAM_MARKET_VALUE = 3;
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                SoccerContract.TeamsEntry.CONTENT_URI,
                TEAM_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mTeamAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTeamAdapter.swapCursor(null);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public static final String LOG_TAG = TeamsFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teams,container,false);
        ListView ls = (ListView)rootView.findViewById(R.id.list_view_teams);
        mTeamAdapter = new TeamAdapter(getActivity(),null,0);
        ls.setAdapter(mTeamAdapter);
        AdView adView =(AdView)rootView.findViewById(R.id.team_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        id= getActivity().getIntent().getStringExtra("Id");
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PlayersActivity.class);
                ImageView tv = (ImageView)view.findViewById(R.id.teams_image);
                String iden = tv.getContentDescription().toString();
                intent.putExtra("Id",iden);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.teams_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int identity =item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID,null,this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getActivity(), FetchTeamsService.class);
        intent.putExtra("Id",id);
        getActivity().startService(intent);
    }


    @Override
    public void onStop() {
        super.onStop();
        getActivity().stopService(new Intent(getActivity(),FetchTeamsService.class));
    }
}

