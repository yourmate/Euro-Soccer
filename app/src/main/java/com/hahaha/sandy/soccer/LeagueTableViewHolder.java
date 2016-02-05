package com.hahaha.sandy.soccer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hahaha on 18/01/16.
 */
public class LeagueTableViewHolder {
    ImageView teamImageView;
    TextView teamNameTextView;
    TextView teamPointsTextView;
    TextView teamPositionTextView;
    TextView teamMatchesPlayed;

    public LeagueTableViewHolder(View view) {
        teamImageView = (ImageView)view.findViewById(R.id.team_icon);
        teamNameTextView = (TextView)view.findViewById(R.id.team_name);
        teamPointsTextView = (TextView)view.findViewById(R.id.team_points);
        teamPositionTextView = (TextView)view.findViewById(R.id.team_position);
        teamMatchesPlayed = (TextView)view.findViewById(R.id.team_matches_played);

    }
}
