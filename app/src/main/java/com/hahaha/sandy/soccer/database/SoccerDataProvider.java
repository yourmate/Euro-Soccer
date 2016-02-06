package com.hahaha.sandy.soccer.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.util.Log;

import java.text.StringCharacterIterator;


public class SoccerDataProvider extends ContentProvider {
    
    //Helps us to build SQL queries
    public static SQLiteQueryBuilder mSqLiteQueryBuilder;

    //queryBuilder is intialized and table is set
    static {
        mSqLiteQueryBuilder = new SQLiteQueryBuilder();
        mSqLiteQueryBuilder.setTables(SoccerContract.LeagueTableEntry.TABLE_NAME);
    }

    //urimatcher to match uri's with constants
    UriMatcher sUriMatcher = buildUriMatcher();
    SoccerDbHelper soccerDbHelper;

    //constant to match uri
    public static final int TABLE_LEAGUE = 101;
    public static final int TABLE_FIXTURE = 102;
    public static final int TABLE_LEAGUE_POINTS = 103;
    public static final int TABLE_LEAGUE_DETAIL = 104;
    public static final int TABLE_TEAMS = 105;
    public static final int TABLE_PLAYERS = 106;
    
    @Override
    public boolean onCreate() {
        
        //intialized soccerDbHelper
        soccerDbHelper = new SoccerDbHelper(getContext());
        return true;
    }
    //leagues.teamName = ?
    private static String mSoccerDataWithTeamName = SoccerContract.LeagueTableEntry.TABLE_NAME + "." + SoccerContract.LeagueTableEntry.TEAM_NAME + " = ? ";
    
    private Cursor getSoccerDatafromName(Uri uri,String[] projection,String sortOrder)
    {
        String name = SoccerContract.LeagueTableEntry.getTeamName(uri);
        String[] selectionArgs = new String[]{name};

        return mSqLiteQueryBuilder.query(soccerDbHelper.getReadableDatabase(),
                projection,
                mSoccerDataWithTeamName,
                selectionArgs,
                null,
                null,
                null);
    }
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = soccerDbHelper.getReadableDatabase();
        switch (match)
        {
            case TABLE_LEAGUE:
                retCursor = db.query(SoccerContract.LeaguesEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE_FIXTURE:
                retCursor = db.query(SoccerContract.FixtureEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE_LEAGUE_POINTS:
                retCursor = db.query(SoccerContract.LeagueTableEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TABLE_LEAGUE_DETAIL:
                retCursor = getSoccerDatafromName(uri,projection,sortOrder);
                break;
            case TABLE_TEAMS:
                retCursor = db.query(SoccerContract.TeamsEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE_PLAYERS:
                retCursor = db.query(SoccerContract.PlayersEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Error: Unable to query database");
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match)
        {
            case TABLE_LEAGUE:
                return SoccerContract.LeaguesEntry.CONTENT_TYPE;
            case TABLE_FIXTURE:
                return SoccerContract.FixtureEntry.CONTENT_TYPE;
            case TABLE_LEAGUE_POINTS:
                return SoccerContract.LeagueTableEntry.CONTENT_TYPE;
            case TABLE_LEAGUE_DETAIL:
                return  SoccerContract.LeagueTableEntry.CONTENT_ITEM_TYPE;
            case TABLE_TEAMS:
                return SoccerContract.TeamsEntry.CONTENT_TYPE;
            case TABLE_PLAYERS:
                return SoccerContract.PlayersEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Error: Unidentified data " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = soccerDbHelper.getReadableDatabase();
        Uri returnUri;
        long id;
        switch (match)
        {
            case TABLE_LEAGUE: {
                id = db.insert(SoccerContract.LeaguesEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = SoccerContract.LeaguesEntry.buildLeagueUriFromId(id);
                } else {
                    throw new SQLException("Error: Unable to Insert row");
                }
            }
            break;
            case TABLE_FIXTURE:{
                id = db.insert(SoccerContract.FixtureEntry.TABLE_NAME,null,values);
                if(id > 0){
                    returnUri = SoccerContract.FixtureEntry.buildFixtureUriFromId(id);
                }
                else{
                    throw new SQLException("Error: Unable to Insert row");
                }
            }
            break;
            case TABLE_LEAGUE_POINTS: {
                id = db.insert(SoccerContract.LeagueTableEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = SoccerContract.LeagueTableEntry.buildLeagueTablePointsUri(id);
                } else {
                    throw new SQLException("Error: Unable to Insert row");
                }
            }
            break;
            case TABLE_TEAMS:{
                id = db.insert(SoccerContract.TeamsEntry.TABLE_NAME,null,values);
                if(id > 0)
                {
                    returnUri = SoccerContract.TeamsEntry.buildTeamsUriFromId(id);
                }else
                {
                    throw new SQLException("Error: Unable to Insert row");
                }
            }
            break;
            case TABLE_PLAYERS:{
                id= db.insert(SoccerContract.PlayersEntry.TABLE_NAME,null,values);
                if(id > 0)
                {
                    returnUri = SoccerContract.PlayersEntry.buildPlayerUriWithId(id);
                }
                else
                {
                    throw new SQLException("Error: Unable to Insert row");
                }
            }
            break;
            default:
                throw new UnsupportedOperationException("Error: Insert Operation");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = soccerDbHelper.getWritableDatabase();
        int rowsDeleted;
        if(selection == null)
        {
            selection = "1";
        }
        switch (match) {
            case TABLE_LEAGUE:
                rowsDeleted = db.delete(SoccerContract.LeaguesEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case TABLE_FIXTURE:
                rowsDeleted = db.delete(SoccerContract.FixtureEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case TABLE_LEAGUE_POINTS:
                rowsDeleted = db.delete(SoccerContract.LeagueTableEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case TABLE_TEAMS:
                rowsDeleted = db.delete(SoccerContract.TeamsEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case TABLE_PLAYERS:
                rowsDeleted = db.delete(SoccerContract.PlayersEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Error: Unable to delete data");
        }
        if(rowsDeleted != 0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = soccerDbHelper.getWritableDatabase();
        int rowsUpdated;
        switch (match)
        {
            case TABLE_LEAGUE:
                rowsUpdated = db.update(SoccerContract.LeaguesEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case TABLE_FIXTURE:
                rowsUpdated = db.update(SoccerContract.LeaguesEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case TABLE_LEAGUE_POINTS:
                rowsUpdated = db.update(SoccerContract.LeagueTableEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case TABLE_TEAMS:
                rowsUpdated = db.update(SoccerContract.TeamsEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case TABLE_PLAYERS:
                rowsUpdated = db.update(SoccerContract.PlayersEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Error: Unable to update row");
        }
        if(rowsUpdated != 0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }

    public static UriMatcher buildUriMatcher()
    {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = SoccerContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(authority, SoccerContract.PATH_LEAGUES,TABLE_LEAGUE);
        uriMatcher.addURI(authority,SoccerContract.PATH_FIXTURES,TABLE_FIXTURE);
        uriMatcher.addURI(authority,SoccerContract.PATH_LEAGUE_TABLE,TABLE_LEAGUE_POINTS);
        uriMatcher.addURI(authority,SoccerContract.PATH_LEAGUE_TABLE + "/*",TABLE_LEAGUE_DETAIL);
        uriMatcher.addURI(authority,SoccerContract.PATH_TEAMS,TABLE_TEAMS);
        uriMatcher.addURI(authority,SoccerContract.PATH_PLAYERS,TABLE_PLAYERS);
        return uriMatcher;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = soccerDbHelper.getWritableDatabase();
        int rowInserted;
        switch (match)
        {
            case TABLE_LEAGUE: {
                rowInserted = 0;
                long _id;
                try {
                    db.beginTransaction();
                    for (ContentValues value : values) {
                        _id = db.insert(SoccerContract.LeaguesEntry.TABLE_NAME, null, value);
                        if (_id > 0) {
                            rowInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return rowInserted;
            }
            case TABLE_FIXTURE: {
                rowInserted = 0;
                long _id;
                try {
                    db.beginTransaction();
                    for (ContentValues value : values) {
                        _id = db.insert(SoccerContract.FixtureEntry.TABLE_NAME, null, value);
                        if (_id > 0) {

                            rowInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return rowInserted;
            }
            case TABLE_LEAGUE_POINTS:{
                rowInserted = 0;
                long _id;
                try
                {
                    db.beginTransaction();
                    for(ContentValues value : values)
                    {
                        _id = db.insert(SoccerContract.LeagueTableEntry.TABLE_NAME,null,value);

                        if(_id > 0)
                        {
                            rowInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return rowInserted;
            }
            case TABLE_TEAMS:{
                rowInserted = 0;
                long _id;
                try
                {
                    db.beginTransaction();
                    for(ContentValues value : values)
                    {
                        _id = db.insert(SoccerContract.TeamsEntry.TABLE_NAME,null,value);

                        if(_id > 0)
                        {
                            rowInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return rowInserted;
            }
            case TABLE_PLAYERS:{
                rowInserted = 0;
                long _id;
                try
                {
                    db.beginTransaction();
                    for(ContentValues value : values)
                    {
                        _id = db.insert(SoccerContract.PlayersEntry.TABLE_NAME,null,value);
                        if(_id > 0)
                        {
                            rowInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return rowInserted;
            }

        }
        return super.bulkInsert(uri, values);
    }
}
