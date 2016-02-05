package com.hahaha.sandy.soccer;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class TeamAdapter extends CursorAdapter {
    public TeamAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.teams_list_item,parent,false);
        TeamsViewHolder teamsViewHolder = new TeamsViewHolder(view);
        view.setTag(teamsViewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TeamsViewHolder teamsViewHolder = (TeamsViewHolder)view.getTag();
        teamsViewHolder.teamImage.setImageResource(Utility.getTeamsImage(context,cursor.getString(TeamsFragment.COL_TEAM_LOGO)));
        teamsViewHolder.teamImage.setContentDescription(cursor.getString(TeamsFragment.COL_TEAM_LOGO));
        teamsViewHolder.teamText.setText(cursor.getString(TeamsFragment.COL_TEAM_NAME));
        teamsViewHolder.squadValue.setText(Utility.formatValue(context,cursor.getString(TeamsFragment.COL_TEAM_MARKET_VALUE)));
    }
}
