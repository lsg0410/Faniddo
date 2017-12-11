package com.example.yjkim.faniddo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yjkim on 2017-11-14.
 */

public class LetterHolder extends RecyclerView.ViewHolder {
    public TextView letter_search_writer, letter_search_title, letter_search_regdate;

    public letterItemClickListener mListener;
    public void setOnListItemClickListener(letterItemClickListener onListItemClickListener){
        mListener = onListItemClickListener;
    }

    public LetterHolder(View itemView) {
        super(itemView);
        letter_search_writer = (TextView) itemView.findViewById(R.id.letter_search_writer);
        letter_search_title = (TextView) itemView.findViewById(R.id.letter_search_title);
        letter_search_regdate = (TextView) itemView.findViewById(R.id.letter_search_regdate);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListItemClick(getAdapterPosition());
            }
        });
    }
}
