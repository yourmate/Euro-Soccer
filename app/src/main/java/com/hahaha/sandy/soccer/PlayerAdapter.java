package com.hahaha.sandy.soccer;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by hahaha on 18/01/16.
 */
public class PlayerAdapter extends CursorAdapter {
    public PlayerAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.players_list_item,parent,false);
        PlayersViewHolder playersViewHolder = new PlayersViewHolder(view);
        view.setTag(playersViewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PlayersViewHolder viewHolder = (PlayersViewHolder)view.getTag();

        viewHolder.playerNameTextView.setText(cursor.getString(PlayersFragment.COL_PLAYER_NAME));

        viewHolder.playerNationality.setText(Utility.formatNationality(context,cursor.getString(PlayersFragment.COL_PLAYER_NATIONALITY)));

        viewHolder.playerMarketValue.setText(Utility.formatMarketValue(context,cursor.getString(PlayersFragment.COL_PLAYER_MARKET_VALUE)));

        viewHolder.playerContractTextView.setText(Utility.formatContract(context,cursor.getString(PlayersFragment.COL_PLAYER_CONTRACT_UNTIL)));

        viewHolder.playerDOBTextView.setText(Utility.formatDOB(context,cursor.getString(PlayersFragment.COL_PLAYER_DATE_OF_BIRTH)));

        viewHolder.playerJerseyTextView.setText(Utility.formatJersey(context,cursor.getString(PlayersFragment.COL_PLAYER_JERSEY_NUMBER)));

        viewHolder.playerPositionTextView.setText(Utility.formatPlayerPosition(context, cursor.getString(PlayersFragment.COL_PLAYER_POSITION)));
    }
}
