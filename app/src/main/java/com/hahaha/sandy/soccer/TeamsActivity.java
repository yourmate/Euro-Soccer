package com.hahaha.sandy.soccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TeamsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        if(savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.teams_container,new TeamsFragment())
                    .commit();
        }
    }
}
