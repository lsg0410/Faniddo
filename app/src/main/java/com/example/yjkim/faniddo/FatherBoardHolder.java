package com.example.yjkim.faniddo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FatherBoardHolder extends RecyclerView.ViewHolder {
    TextView father_board_title, father_board_writer, father_board_regdate;
    public OnListItemClickListener cListener;
    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
        cListener = onListItemClickListener;
    }

    public FatherBoardHolder(View itemView) {
        super(itemView);
        father_board_title = (TextView) itemView.findViewById(R.id.father_board_title);
        father_board_writer = (TextView) itemView.findViewById(R.id.father_board_writer);
        father_board_regdate = (TextView) itemView.findViewById(R.id.father_board_regdate);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cListener.onListItemClick(getAdapterPosition());
            }
        });
    }
}
