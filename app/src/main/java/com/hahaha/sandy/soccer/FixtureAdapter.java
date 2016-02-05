package com.hahaha.sandy.soccer;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class FixtureAdapter extends CursorAdapter {
    public FixtureAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.fixtures_list_item,parent,false);
        FixtureViewHolder fixtureViewHolder = new FixtureViewHolder(view);
        view.setTag(fixtureViewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        FixtureViewHolder viewHolder = (FixtureViewHolder)view.getTag();

        viewHolder.homeIcon.setImageResource(Utility.getTeamsImage(context,cursor.getString(FixtureFragment.COL_HOME_ID)));


        viewHolder.awayIcon.setImageResource(Utility.getTeamsImage(context, cursor.getString(FixtureFragment.COL_AWAY_ID)));


        viewHolder.scoreTextview.setText(Utility.scoreString(context, cursor.getInt(FixtureFragment.COL_HOME_GOAL), cursor.getInt(FixtureFragment.COL_AWAY_GOAL)));


        viewHolder.homeTeam.setText(cursor.getString(FixtureFragment.COL_HOME_TEAM));


        viewHolder.awayTeam.setText(cursor.getString(FixtureFragment.COL_AWAY_TEAM));


        viewHolder.statusTextview.setText(cursor.getString(FixtureFragment.COL_STATUS));


        viewHolder.dateTextView.setText(Utility.time_date(context, cursor.getString(FixtureFragment.COL_DATE),cursor.getString(FixtureFragment.COL_TIME)));
    }
}
