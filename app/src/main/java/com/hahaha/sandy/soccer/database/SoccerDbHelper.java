package com.hahaha.sandy.soccer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SoccerDbHelper extends SQLiteOpenHelper {
    //constant for Database version and it changes whenever we modify database
    public static final int DATABASE_VERSION = 9;
    public static final String DATABASE_NAME = "soccer.db";

    //constructor to intialize database version and database name
    public SoccerDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Constant String to create table and defining various constraints on them 
        final String CREATE_LEAGUE_TABLE = "CREATE TABLE " + SoccerContract.LeaguesEntry.TABLE_NAME
                + "( " + SoccerContract.LeaguesEntry._ID + " INTEGER PRIMARY KEY, "
                + SoccerContract.LeaguesEntry.LEAGUE_ID + " INTEGER NOT NULL, "
                + SoccerContract.LeaguesEntry.LEAGUE_NAME + " TEXT NOT NULL, "
                + SoccerContract.LeaguesEntry.LEAGUE_MATCH_DAY + " INTEGER NOT NULL, "
                + SoccerContract.LeaguesEntry.TOTAL_TEAMS + " INTEGER NOT NULL, "
                + SoccerContract.LeaguesEntry.TOTAL_GAMES + " INTEGER NOT NULL, "
                + "UNIQUE (" + SoccerContract.LeaguesEntry._ID + ") ON CONFLICT REPLACE);";

        final String CREATE_FIXTURE_TABLE = "CREATE TABLE " + SoccerContract.FixtureEntry.TABLE_NAME
                + "( " + SoccerContract.FixtureEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SoccerContract.FixtureEntry.HOME_TEAM_NAME + " TEXT NOT NULL, "
                + SoccerContract.FixtureEntry.AWAY_TEAM_NAME + " TEXT NOT NULL, "
                + SoccerContract.FixtureEntry.GOAL_HOME_TEAM + " INTEGER NOT NULL, "
                + SoccerContract.FixtureEntry.GOAL_AWAY_TEAM + " INTEGER NOT NULL, "
                + SoccerContract.FixtureEntry.GAME_DATE + " TEXT NOT NULL, "
                + SoccerContract.FixtureEntry.GAME_TIME + " TEXT NOT NULL, "
                + SoccerContract.FixtureEntry.GAME_STATUS + " TEXT NOT NULL, "
                + SoccerContract.FixtureEntry.HOME_TEAM_ID + " TEXT NOT NULL, "
                + SoccerContract.FixtureEntry.AWAY_TEAM_ID + " TEXT NOT NULL, "
                + "UNIQUE (" + SoccerContract.FixtureEntry._ID +") ON CONFLICT REPLACE);";

        final String CREATE_LEAGUE_POINTS_TABLE = "CREATE TABLE " + SoccerContract.LeagueTableEntry.TABLE_NAME + "( "
                + SoccerContract.LeagueTableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SoccerContract.LeagueTableEntry.TEAM_ID + " TEXT NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_POSITION + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_NAME + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_GAMES_PLAYED + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_POINTS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_GOALS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_GOALS_AGAINST + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_GOAL_DIFFERENCE + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_WINS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_DRAWS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_LOSSES + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_HOME_GOALS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_HOME_GOAL_AGAINST + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_HOME_WINS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_HOME_DRAWS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_HOME_LOSSES + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_AWAY_GOALS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_AWAY_GOAL_AGAINST + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_AWAY_WINS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_AWAY_DRAWS + " INTEGER NOT NULL, "
                + SoccerContract.LeagueTableEntry.TEAM_AWAY_LOSSES + " INTEGER NOT NULL, "
                + "UNIQUE (" + SoccerContract.LeagueTableEntry._ID + ") ON CONFLICT REPLACE);";

        final String CREATE_TEAMS_TABLE = "CREATE TABLE " + SoccerContract.TeamsEntry.TABLE_NAME + "( "
                + SoccerContract.TeamsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SoccerContract.TeamsEntry.LOGO_ID + " TEXT NOT NULL, "
                + SoccerContract.TeamsEntry.TEAM_NAME + " TEXT NOT NULL, "
                + SoccerContract.TeamsEntry.TEAM_MARKET_VALUE + " TEXT NOT NULL, "
                +"UNIQUE (" + SoccerContract.TeamsEntry._ID + ") ON CONFLICT REPLACE);";

        final String CREATE_PLAYER_TABLE = "CREATE TABLE " + SoccerContract.PlayersEntry.TABLE_NAME + "( "
                + SoccerContract.PlayersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SoccerContract.PlayersEntry.PLAYER_NAME + " TEXT NOT NULL, "
                + SoccerContract.PlayersEntry.PLAYER_POSITION + " TEXT NOT NULL, "
                + SoccerContract.PlayersEntry.PLAYER_JERSEY_NUMBER + " TEXT NOT NULL, "
                + SoccerContract.PlayersEntry.PLAYER_DATE_OF_BIRTH + " TEXT NOT NULL, "
                + SoccerContract.PlayersEntry.PLAYER_NATIONALITY + " TEXT NOT NULL, "
                + SoccerContract.PlayersEntry.PLAYER_CONTRACT_UNTIL + " TEXT NOT NULL, "
                + SoccerContract.PlayersEntry.PLAYER_MARKET_VALUE + " TEXT NOT NULL, "
                +"UNIQUE (" + SoccerContract.PlayersEntry._ID +") ON CONFLICT REPLACE);";
        
        //this is where the table is created
        db.execSQL(CREATE_LEAGUE_TABLE);
        db.execSQL(CREATE_FIXTURE_TABLE);
        db.execSQL(CREATE_LEAGUE_POINTS_TABLE);
        db.execSQL(CREATE_TEAMS_TABLE);
        db.execSQL(CREATE_PLAYER_TABLE);
    }

//this method is called only when there is database version change
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drops table if it already existed
        db.execSQL("DROP TABLE IF EXISTS " + SoccerContract.LeaguesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SoccerContract.FixtureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SoccerContract.LeagueTableEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SoccerContract.TeamsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SoccerContract.PlayersEntry.TABLE_NAME);
        onCreate(db);
    }
}
