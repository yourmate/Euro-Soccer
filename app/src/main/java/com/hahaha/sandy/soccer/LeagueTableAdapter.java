package com.hahaha.sandy.soccer;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeagueTableAdapter extends CursorAdapter {
    public LeagueTableAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.league_table_list_item,parent,false);
        LeagueTableViewHolder leagueTableViewHolder = new LeagueTableViewHolder(view);
        view.setTag(leagueTableViewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
            LeagueTableViewHolder viewHolder = (LeagueTableViewHolder)view.getTag();
        viewHolder.teamImageView.setImageResource(Utility.getTeamsImage(context, cursor.getString(LeagueTableFragment.COL_TEAM_ID)));


        viewHolder.teamNameTextView.setText(cursor.getString(LeagueTableFragment.COL_TEAM_NAME));


        viewHolder.teamPointsTextView.setText(Utility.formatPoint(context,cursor.getInt(LeagueTableFragment.COL_TEAM_POINTS)));


        viewHolder.teamPositionTextView.setText(Utility.formatPosition(context,cursor.getInt(LeagueTableFragment.COL_TEAM_POSITION)));


        viewHolder.teamMatchesPlayed.setText(Utility.formatMatches(context,cursor.getInt(LeagueTableFragment.COL_TEAM_MATCHES_PLAYED)));
    }
}
