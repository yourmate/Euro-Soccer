package com.hahaha.sandy.soccer;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hahaha.sandy.soccer.database.SoccerContract;

/**
 * Created by hahaha on 17/01/16.
 */
public class LeagueTableFullFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER_ID = 3;
    Uri mUri;
    ImageView teamIcon;
    TextView teamName;
    TextView teamPosition;
    TextView teamMatchPlayed;
    TextView teamGoal;
    TextView teamWins;
    TextView teamDraws;
    TextView teamLosses;
    TextView teamGoalDiffrence;
    TextView teamPoints;
    TextView teamHomeGoals;
    TextView teamHomeGoalsAgainst;
    TextView teamHomeWins;
    TextView teamHomeLosses;
    TextView teamHomeDraws;
    TextView teamAwayGoals;
    TextView teamAwayGoalsAgainst;
    TextView teamAwayWins;
    TextView teamAwayLosses;
    TextView teamAwayDraws;
    TextView teamGoalAgainst;
    public static final String[] COLUMNS_DETAIL = new String[]{
            SoccerContract.LeagueTableEntry.TABLE_NAME + "." + SoccerContract.LeagueTableEntry._ID,
            SoccerContract.LeagueTableEntry.TEAM_NAME,
            SoccerContract.LeagueTableEntry.TEAM_POINTS,
            SoccerContract.LeagueTableEntry.TEAM_GAMES_PLAYED,
            SoccerContract.LeagueTableEntry.TEAM_POSITION,
            SoccerContract.LeagueTableEntry.TEAM_LOSSES,
            SoccerContract.LeagueTableEntry.TEAM_GOALS,
            SoccerContract.LeagueTableEntry.TEAM_DRAWS,
            SoccerContract.LeagueTableEntry.TEAM_WINS,
            SoccerContract.LeagueTableEntry.TEAM_ID,
            SoccerContract.LeagueTableEntry.TEAM_HOME_GOALS,
            SoccerContract.LeagueTableEntry.TEAM_HOME_GOAL_AGAINST,
            SoccerContract.LeagueTableEntry.TEAM_HOME_WINS,
            SoccerContract.LeagueTableEntry.TEAM_HOME_DRAWS,
            SoccerContract.LeagueTableEntry.TEAM_HOME_LOSSES,
            SoccerContract.LeagueTableEntry.TEAM_AWAY_GOALS,
            SoccerContract.LeagueTableEntry.TEAM_AWAY_GOAL_AGAINST,
            SoccerContract.LeagueTableEntry.TEAM_AWAY_WINS,
            SoccerContract.LeagueTableEntry.TEAM_AWAY_LOSSES,
            SoccerContract.LeagueTableEntry.TEAM_AWAY_DRAWS,
            SoccerContract.LeagueTableEntry.TEAM_GOAL_DIFFERENCE,
            SoccerContract.LeagueTableEntry.TEAM_GOALS_AGAINST

    };
    public static final int COL_PRIMARY = 0;
    public static final int COL_TEAM_NAME = 1;
    public static final int COL_TEAM_POINTS = 2;
    public static final int COL_TEAM_GAMES_PLAYED = 3;
    public static final int COL_TEAM_POSITION = 4;
    public static final int COL_TEAM_LOSSES = 5;
    public static final int COL_TEAM_GOALS = 6;
    public static final int COL_TEAM_DRAWS = 7;
    public static final int COL_TEAM_WINS = 8;
    public static final int COL_TEAM_ID = 9;
    public static final int COL_TEAM_HOME_GOALS = 10;
    public static final int COL_TEAM_HOME_GOALS_AGAINST = 11;
    public static final int COL_TEAM_HOME_WINS = 12;
    public static final int COL_TEAM_HOME_DRAWS = 13;
    public static final int COL_TEAM_HOME_LOSSES = 14;
    public static final int COL_TEAM_AWAY_GOALS = 15;
    public static final int COL_TEAM_AWAY_GOALS_AGAINST = 16;
    public static final int COL_TEAM_AWAY_WINS = 17;
    public static final int COL_TEAM_AWAY_LOSSES = 18;
    public static final int COL_TEAM_AWAY_DRAWS = 19;
    public static final int COL_TEAM_GOAL_DIFFERENCE = 20;
    public static final int COL_TEAM_GOAL_AGAINST = 21;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.league_table_full_fragment,container,false);
        mUri = getActivity().getIntent().getData();
        teamIcon = (ImageView)rootView.findViewById(R.id.team_image);
        teamName = (TextView)rootView.findViewById(R.id.team_name_detail);
        teamPosition = (TextView)rootView.findViewById(R.id.team_position_detail);
        teamMatchPlayed = (TextView)rootView.findViewById(R.id.games_played);
        teamGoal = (TextView)rootView.findViewById(R.id.team_goals);
        teamWins = (TextView)rootView.findViewById(R.id.team_wins);
        teamDraws = (TextView)rootView.findViewById(R.id.team_draws);
        teamLosses = (TextView)rootView.findViewById(R.id.team_losses);
        teamGoalDiffrence = (TextView)rootView.findViewById(R.id.goal_difference);
        teamPoints = (TextView)rootView.findViewById(R.id.points);
        teamHomeGoals = (TextView)rootView.findViewById(R.id.team_home_goal);
        teamHomeGoalsAgainst = (TextView)rootView.findViewById(R.id.team_home_goal_against);
        teamHomeWins = (TextView)rootView.findViewById(R.id.team_home_wins);
        teamHomeLosses = (TextView)rootView.findViewById(R.id.team_home_losses);
        teamHomeDraws = (TextView)rootView.findViewById(R.id.team_home_draw);
        teamAwayGoals = (TextView)rootView.findViewById(R.id.team_away_goal);
        teamAwayGoalsAgainst = (TextView)rootView.findViewById(R.id.team_away_goal_against);
        teamAwayWins = (TextView)rootView.findViewById(R.id.team_away_wins);
        teamAwayLosses = (TextView)rootView.findViewById(R.id.team_away_losses);
        teamAwayDraws = (TextView)rootView.findViewById(R.id.team_away_draw);
        teamGoalAgainst = (TextView)rootView.findViewById(R.id.team_goals_against);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(getActivity(),mUri,COLUMNS_DETAIL,null,null,null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID,null,this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst())
        {
            teamIcon.setImageResource(Utility.getTeamsImage(getActivity(), data.getString(COL_TEAM_ID)));
            teamPoints.setText(Utility.formatPoint(getActivity(), data.getInt(COL_TEAM_POINTS)));
            teamName.setText(data.getString(COL_TEAM_NAME));
            teamPosition.setText(Utility.formatPosition(getActivity(), data.getInt(COL_TEAM_POSITION)));
            teamWins.setText(Utility.formatWins(getActivity(), data.getInt(COL_TEAM_WINS)));
            teamLosses.setText(Utility.formatLosses(getActivity(), data.getInt(COL_TEAM_LOSSES)));
            teamDraws.setText(Utility.formatDraws(getActivity(), data.getInt(COL_TEAM_DRAWS)));
            teamGoal.setText(Utility.formatGoals(getActivity(), data.getInt(COL_TEAM_GOALS)));
            teamGoalAgainst.setText(Utility.formatGoalAgainst(getActivity(),data.getInt(COL_TEAM_GOAL_AGAINST)));
            teamGoalDiffrence.setText(Utility.formatGoalDifference(getActivity(),data.getInt(COL_TEAM_GOAL_DIFFERENCE)));
            teamMatchPlayed.setText(Utility.formatMatches(getActivity(),data.getInt(COL_TEAM_GAMES_PLAYED)));
            teamHomeGoals.setText(Utility.formatGoals(getActivity(),data.getInt(COL_TEAM_HOME_GOALS)));
            teamHomeGoalsAgainst.setText(Utility.formatGoalAgainst(getActivity(),data.getInt(COL_TEAM_HOME_GOALS_AGAINST)));
            teamHomeWins.setText(Utility.formatWins(getActivity(),data.getInt(COL_TEAM_HOME_WINS)));
            teamHomeLosses.setText(Utility.formatLosses(getActivity(),data.getInt(COL_TEAM_HOME_LOSSES)));
            teamHomeDraws.setText(Utility.formatDraws(getActivity(),data.getInt(COL_TEAM_HOME_DRAWS)));
            teamAwayGoals.setText(Utility.formatGoals(getActivity(),data.getInt(COL_TEAM_AWAY_GOALS)));
            teamAwayGoalsAgainst.setText(Utility.formatGoalAgainst(getActivity(),data.getInt(COL_TEAM_AWAY_GOALS_AGAINST)));
            teamAwayWins.setText(Utility.formatWins(getActivity(),data.getInt(COL_TEAM_AWAY_WINS)));
            teamAwayLosses.setText(Utility.formatLosses(getActivity(),data.getInt(COL_TEAM_AWAY_LOSSES)));
            teamAwayDraws.setText(Utility.formatDraws(getActivity(),data.getInt(COL_TEAM_AWAY_DRAWS)));
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
