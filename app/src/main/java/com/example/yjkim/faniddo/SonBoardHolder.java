package com.example.yjkim.faniddo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yjkim on 2017-12-03.
 */

public class SonBoardHolder extends RecyclerView.ViewHolder {
    TextView son_board_title, son_board_writer, son_board_regdate;

    public OnListItemClickListener sListener;
    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
        sListener = onListItemClickListener;
    }

    public SonBoardHolder(View itemView) {
        super(itemView);
        son_board_title = (TextView) itemView.findViewById(R.id.son_board_title);
        son_board_writer = (TextView) itemView.findViewById(R.id.son_board_writer);
        son_board_regdate = (TextView) itemView.findViewById(R.id.son_board_regdate);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sListener.onListItemClick(getAdapterPosition());
            }
        });
    }
}
