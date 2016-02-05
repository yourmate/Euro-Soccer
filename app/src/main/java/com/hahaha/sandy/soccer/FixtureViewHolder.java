package com.hahaha.sandy.soccer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hahaha on 15/01/16.
 */
public class FixtureViewHolder {
    ImageView homeIcon;
    ImageView awayIcon;
    TextView scoreTextview;
    TextView homeTeam;
    TextView awayTeam;
    TextView statusTextview;
    TextView dateTextView;
    public FixtureViewHolder(View view) {
        homeIcon = (ImageView)view.findViewById(R.id.home_team_icon);
        awayIcon = (ImageView)view.findViewById(R.id.away_team_icon);
        scoreTextview = (TextView)view.findViewById(R.id.score);
        homeTeam = (TextView)view.findViewById(R.id.home_team);
        awayTeam = (TextView)view.findViewById(R.id.away_team);
        statusTextview = (TextView)view.findViewById(R.id.status);
        dateTextView = (TextView)view.findViewById(R.id.date_string);
    }
}
