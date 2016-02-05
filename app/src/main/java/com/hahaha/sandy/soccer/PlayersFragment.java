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
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import services.FetchFixturesService;
import services.FetchPlayerService;

public class PlayersFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    ProgressDialog dialog;
    String id;
    public static final int LOADER_ID = 6;
    public static final String[] COLUMNS_PLAYER = new String[]{
            SoccerContract.PlayersEntry.TABLE_NAME + "." + SoccerContract.PlayersEntry._ID,
            SoccerContract.PlayersEntry.PLAYER_NAME,
            SoccerContract.PlayersEntry.PLAYER_POSITION,
            SoccerContract.PlayersEntry.PLAYER_JERSEY_NUMBER,
            SoccerContract.PlayersEntry.PLAYER_DATE_OF_BIRTH,
            SoccerContract.PlayersEntry.PLAYER_NATIONALITY,
            SoccerContract.PlayersEntry.PLAYER_CONTRACT_UNTIL,
            SoccerContract.PlayersEntry.PLAYER_MARKET_VALUE
    };
    public static final int COL_ID_PRIMARY = 0;
    public static final int COL_PLAYER_NAME = 1;
    public static final int COL_PLAYER_POSITION = 2;
    public static final int COL_PLAYER_JERSEY_NUMBER = 3;
    public static final int COL_PLAYER_DATE_OF_BIRTH = 4;
    public static final int COL_PLAYER_NATIONALITY = 5;
    public static final int COL_PLAYER_CONTRACT_UNTIL = 6;
    public static final int COL_PLAYER_MARKET_VALUE = 7;
    PlayerAdapter mPlayerAdapter;
    public static final String LOG_TAG = PlayersFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_players,container,false);
        id = getActivity().getIntent().getStringExtra("Id");
        ListView ls = (ListView)rootView.findViewById(R.id.player_list_view);
        AdView adView =(AdView)rootView.findViewById(R.id.player_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mPlayerAdapter = new PlayerAdapter(getActivity(),null,0);
        ls.setAdapter(mPlayerAdapter);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.players_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iden = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                SoccerContract.PlayersEntry.CONTENT_URI,
                COLUMNS_PLAYER,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mPlayerAdapter.swapCursor(data);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID,null,this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mPlayerAdapter.swapCursor(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getActivity(), FetchPlayerService.class);
        intent.putExtra("Id",id);
        getActivity().startService(intent);

    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().stopService(new Intent(getActivity(),FetchPlayerService.class));
    }
}

