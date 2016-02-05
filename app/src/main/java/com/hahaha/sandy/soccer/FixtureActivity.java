package com.hahaha.sandy.soccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FixtureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        if(savedInstanceState == null)
        {
            Bundle args = new Bundle();
            args.putString(FixtureFragment.id, getIntent().getStringExtra(FixtureFragment.id));

            FixtureFragment fragment = new FixtureFragment();
            fragment.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fixture_container, fragment)
                    .commit();
        }
    }
}
