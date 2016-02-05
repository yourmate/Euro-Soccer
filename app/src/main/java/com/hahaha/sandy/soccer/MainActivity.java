package com.hahaha.sandy.soccer;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements LeagueFragment.Callback {
    boolean mTwopane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.fixture_container) != null)
        {
            mTwopane = true;
            getFragmentManager().beginTransaction()
                    .add(R.id.fixture_container,new FixtureFragment())
                    .commit();
        }
    }

    @Override
    public void onItemClicked(int position) {
        if (mTwopane) {
            Bundle args = new Bundle();
            args.putString(FixtureFragment.id, Integer.toString(position));

            FixtureFragment ff = new FixtureFragment();
            ff.setArguments(args);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fixture_container,ff)
                    .commit();
        }
        else
        {
            Intent intent = new Intent(this,FixtureActivity.class).putExtra(FixtureFragment.id,Integer.toString(position));
            startActivity(intent);


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            FragmentManager fm = getFragmentManager();
            DialogFragmentExit dialogFragmentExit = new DialogFragmentExit();
            dialogFragmentExit.show(fm, "EXIT_DIALOG");
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }
}
