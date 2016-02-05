package com.hahaha.sandy.soccer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hahaha on 18/01/16.
 */
public class TeamsViewHolder {
    ImageView teamImage;
    TextView teamText;
    TextView squadValue;

    public TeamsViewHolder(View view) {
        teamImage = (ImageView)view.findViewById(R.id.teams_image);
        teamText = (TextView)view.findViewById(R.id.teams_name);
        squadValue = (TextView)view.findViewById(R.id.team_market_value);
    }
}
