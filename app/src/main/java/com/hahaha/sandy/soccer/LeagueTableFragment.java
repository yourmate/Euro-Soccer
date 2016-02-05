package com.hahaha.sandy.soccer;

import android.app.Fragment;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hahaha.sandy.soccer.database.SoccerContract;

import services.FetchLeagueTableService;


public class LeagueTableFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    ProgressDialog dialog;
   String id;
    Uri uri;
    public static final int LOADER_ID = 2;
    public static final String[] COLUMNS_LEAGUE_TABLE = new String[]{
            SoccerContract.LeagueTableEntry.TABLE_NAME + "." + SoccerContract.LeagueTableEntry._ID,
            SoccerContract.LeagueTableEntry.TEAM_NAME,
            SoccerContract.LeagueTableEntry.TEAM_ID,
            SoccerContract.LeagueTableEntry.TEAM_POINTS,
            SoccerContract.LeagueTableEntry.TEAM_POSITION,
            SoccerContract.LeagueTableEntry.TEAM_GAMES_PLAYED
    };
    public static final int COL_PRIMARY_KEY = 0;
    public static final int COL_TEAM_NAME = 1;
    public static final int COL_TEAM_ID = 2;
    public static final int COL_TEAM_POINTS = 3;
    public static final int COL_TEAM_POSITION = 4;
    public static final int COL_TEAM_MATCHES_PLAYED = 5;
    public static final String LOG_TAG = LeagueTableFragment.class.getSimpleName();
    LeagueTableAdapter mLeagueTableAdapter;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        Log.v("Main", "Called");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.league_table_fragment, container, false);
        listView = (ListView) rootView.findViewById(R.id.league_table);
        mLeagueTableAdapter = new LeagueTableAdapter(getActivity(),null,0);
        id=getActivity().getIntent().getStringExtra("Id");
        if(id.equals("405") || id.equals("425"))
        {
            Toast.makeText(getActivity(),"No league table",Toast.LENGTH_SHORT).show();
        }
        listView.setAdapter(mLeagueTableAdapter);
        AdView adView =(AdView)rootView.findViewById(R.id.table_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.team_name);
                String name = tv.getText().toString();
                uri = SoccerContract.LeagueTableEntry.buildUriWithTeamName(name);
                Intent intent = new Intent(getActivity(), LeagueTableFullActivity.class);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.league_table, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iden = item.getItemId();
        if (iden == R.id.team_btn) {
            Intent intent = new Intent(getActivity(), TeamsActivity.class);
            intent.putExtra("Id", id);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                SoccerContract.LeagueTableEntry.CONTENT_URI,
                COLUMNS_LEAGUE_TABLE,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mLeagueTableAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mLeagueTableAdapter.swapCursor(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getActivity(), FetchLeagueTableService.class);
        intent.putExtra("Id", id);
        getActivity().startService(intent);
    }


    @Override
    public void onStop() {
        super.onStop();
        getActivity().stopService(new Intent(getActivity(), FetchLeagueTableService.class));
    }
}


