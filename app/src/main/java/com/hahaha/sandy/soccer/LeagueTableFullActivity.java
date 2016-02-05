package com.hahaha.sandy.soccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class LeagueTableFullActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_table_full_activity);

        if(savedInstanceState == null)
        {

            getFragmentManager().beginTransaction()
                    .replace(R.id.table_detail,new LeagueTableFullFragment())
                    .commit();
        }
    }
}
