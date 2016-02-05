package com.hahaha.sandy.soccer;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class mAdapter extends CursorAdapter {
    public mAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.leagues_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
    return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder)view.getTag();
            viewHolder.iconView.setImageResource(Utility.getImage(context, cursor.getInt(LeagueFragment.COL_LEAGUE_ID)));
            viewHolder.leagueTextView.setText(cursor.getString(LeagueFragment.COL_LEAGUE_NAME));
            viewHolder.matchTextView.setText(Utility.getMatchString(context,cursor.getInt(LeagueFragment.COL_LEAGUE_MATCH_DAY)));
            viewHolder.totalTeams.setText(Utility.getTotalTeams(context, cursor.getInt(LeagueFragment.COL_TOTAL_TEAMS)));
            viewHolder.totalGames.setText(Utility.getTotalGames(context,cursor.getInt(LeagueFragment.COL_TOTAL_GAMES)));
    }
}
