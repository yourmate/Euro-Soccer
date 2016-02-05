package com.hahaha.sandy.soccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PlayersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        if(savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.player_container,new PlayersFragment())
                    .commit();
        }
    }
}
