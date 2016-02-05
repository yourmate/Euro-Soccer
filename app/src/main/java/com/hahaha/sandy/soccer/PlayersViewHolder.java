package com.hahaha.sandy.soccer;

import android.view.View;
import android.widget.TextView;

public class PlayersViewHolder {
    TextView playerNameTextView;
    TextView playerPositionTextView;
    TextView playerJerseyTextView;
    TextView playerDOBTextView;
    TextView playerContractTextView;
    TextView playerMarketValue;
    TextView playerNationality;

    public PlayersViewHolder(View view) {
        playerNameTextView = (TextView)view.findViewById(R.id.player_name);
        playerPositionTextView = (TextView)view.findViewById(R.id.player_position);
        playerJerseyTextView = (TextView)view.findViewById(R.id.player_jersey);
        playerDOBTextView = (TextView)view.findViewById(R.id.player_dob);
        playerContractTextView = (TextView)view.findViewById(R.id.player_contract);
        playerMarketValue = (TextView)view.findViewById(R.id.player_market_value);
        playerNationality = (TextView)view.findViewById(R.id.player_nationality);

    }
}
