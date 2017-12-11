package com.example.yjkim.faniddo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yjkim on 2017-11-23.
 */

public class ConsultHolder extends RecyclerView.ViewHolder {
    TextView consult_main_title, consult_side;
    public OnListItemClickListener cListener;
    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
        cListener = onListItemClickListener;
    }

    public ConsultHolder(View itemView) {
        super(itemView);
        consult_main_title = (TextView) itemView.findViewById(R.id.consult_main_title);
        consult_side = (TextView) itemView.findViewById(R.id.consult_side);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cListener.onListItemClick(getAdapterPosition());
            }
        });
    }
}
