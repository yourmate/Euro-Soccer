package com.hahaha.sandy.soccer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hahaha on 15/01/16.
 */
public class ViewHolder {
    ImageView iconView;
    TextView leagueTextView;
    TextView matchTextView;
    TextView totalTeams;
    TextView totalGames;

    public ViewHolder(View view) {
        iconView = (ImageView) view.findViewById(R.id.league_icon);
        leagueTextView = (TextView) view.findViewById(R.id.league_name);
        matchTextView = (TextView) view.findViewById(R.id.match_day);
        totalTeams = (TextView) view.findViewById(R.id.total_teams);
        totalGames = (TextView) view.findViewById(R.id.total_games);
    }
}
