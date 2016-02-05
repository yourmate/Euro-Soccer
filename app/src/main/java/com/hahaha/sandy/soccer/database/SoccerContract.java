package com.hahaha.sandy.soccer.database;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;
import java.net.URL;

public class SoccerContract {
    public static String CONTENT_AUTHORITY = "com.hahaha.sandy.soccer";

    public static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static String PATH_LEAGUES = "leagues";

    public static String PATH_FIXTURES = "fixtures";

    public static String PATH_LEAGUE_TABLE = "league_table";
    public static String PATH_TEAMS = "teams";
    public static String PATH_PLAYERS = "players";
    public static final class LeaguesEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LEAGUES).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUES;
        public static final String TABLE_NAME = "leagues";
        public static final String LEAGUE_NAME = "league_name";
        public static final String LEAGUE_MATCH_DAY = "match_day";
        public static final String TOTAL_TEAMS = "total_teams";
        public static final String TOTAL_GAMES = "total_games";
        public static final String LEAGUE_ID = "league_id";
        public static Uri buildLeagueUriFromId(long _id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,_id);
        }
    }

    public static final class FixtureEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FIXTURES).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FIXTURES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_FIXTURES;
        public static final String TABLE_NAME = "fixtures";
        public static final String HOME_TEAM_NAME = "home_team";
        public static final String AWAY_TEAM_NAME = "away_team";
        public static final String GOAL_HOME_TEAM = "goal_home";
        public static final String GOAL_AWAY_TEAM = "goal_away";
        public static final String GAME_DATE = "date";
        public static final String GAME_TIME = "time";
        public static final String GAME_STATUS = "result";
        public static final String HOME_TEAM_ID = "home_team_id";
        public static final String AWAY_TEAM_ID = "away_team_id";
        public static Uri buildFixtureUriFromId(long _id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,_id);
        }
    }
    public static final class LeagueTableEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LEAGUE_TABLE).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUE_TABLE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUE_TABLE;
        public static final String TEAM_ID = "team_id";
        public static final String TABLE_NAME = "league_table";
        public static final String TEAM_NAME = "teamName";
        public static final String TEAM_POSITION = "position";
        public static final String TEAM_GAMES_PLAYED = "gamesPlayed";
        public static final String TEAM_POINTS = "points";
        public static final String TEAM_GOALS = "goals";
        public static final String TEAM_GOALS_AGAINST = "goalsAgainst";
        public static final String TEAM_GOAL_DIFFERENCE = "goalDifference";
        public static final String TEAM_WINS = "wins";
        public static final String TEAM_LOSSES = "losses";
        public static final String TEAM_DRAWS = "draws";
        public static final String TEAM_HOME_GOALS = "homeGoals";
        public static final String TEAM_HOME_GOAL_AGAINST = "homeGoalAgainst";
        public static final String TEAM_HOME_WINS = "homeWins";
        public static final String TEAM_HOME_LOSSES = "homeLosses";
        public static final String TEAM_HOME_DRAWS = "homeDraws";
        public static final String TEAM_AWAY_GOALS = "awayGoals";
        public static final String TEAM_AWAY_GOAL_AGAINST = "awayGoalAgainst";
        public static final String TEAM_AWAY_WINS = "awayWins";
        public static final String TEAM_AWAY_LOSSES = "awayLosses";
        public static final String TEAM_AWAY_DRAWS = "awayDraws";
        public static Uri buildLeagueTablePointsUri(long _id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,_id);
        }
        public static Uri buildUriWithTeamName(String name)
        {
            return CONTENT_URI.buildUpon().appendPath(name).build();
        }
        public static String getTeamName(Uri uri)
        {
            return uri.getPathSegments().get(1);
        }
    }
    public static class TeamsEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEAMS).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TEAMS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TEAMS;
        public static final String TABLE_NAME = "teams";
        public static final String LOGO_ID = "logo_id";
        public static final String TEAM_NAME = "teamName";
        public static final String TEAM_MARKET_VALUE = "squadValue";
        public static Uri buildTeamsUriFromId(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }
    public static class PlayersEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYERS).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLAYERS;
        public static final String CONTENT_ITEM_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLAYERS;
        public static final String TABLE_NAME = "players";
        public static final String PLAYER_NAME = "playerName";
        public static final String PLAYER_POSITION = "playerPosition";
        public static final String PLAYER_JERSEY_NUMBER = "jerseyNumber";
        public static final String PLAYER_DATE_OF_BIRTH = "dateOfBirth";
        public static final String PLAYER_NATIONALITY = "nationality";
        public static final String PLAYER_CONTRACT_UNTIL = "contractUntil";
        public static final String PLAYER_MARKET_VALUE = "marketValue";
        public static Uri buildPlayerUriWithId(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }
}
