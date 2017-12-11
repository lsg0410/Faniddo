package com.example.yjkim.faniddo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjkim.faniddo.domain.MissionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-22.
 */

public class MissionAdaper extends RecyclerView.Adapter<MissionAdaper.CardViewHolder> implements MissionClickListener {

    private List<MissionVO> itemList = new ArrayList<>();
    private Context context;

    public void add(MissionVO vo){
        Log.d("LOG", ""+vo);

        itemList.add(vo);
        notifyDataSetChanged();
    }

    public MissionAdaper(Context context) {
        this.context = context;
    }

    @Override
    public MissionAdaper.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mission_item, null);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        cardViewHolder.setOnListItemClickListener(this);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(MissionAdaper.CardViewHolder holder, int position) {
        MissionVO vo = itemList.get(position);
        holder.mission_tvtitle.setText(vo.getMtitle());
        holder.mission_tvcontent.setText(vo.getMcontent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(context, MissionContentActivity.class);
        intent.putExtra("title", itemList.get(position).getMtitle());
        intent.putExtra("content", itemList.get(position).getMcontent());
        intent.putExtra("mno", itemList.get(position).getMno());
        context.startActivity(intent);

    }


    public static class CardViewHolder extends RecyclerView.ViewHolder{
        TextView mission_tvtitle, mission_tvcontent;
        public MissionClickListener Listener;

        public void setOnListItemClickListener(MissionClickListener onListItemClickListener) {
            Listener = onListItemClickListener;
        }

        public CardViewHolder(View itemView) {
            super(itemView);
            mission_tvtitle = (TextView) itemView.findViewById(R.id.mission_tvtitle);
            mission_tvcontent = (TextView) itemView.findViewById(R.id.mission_tvcontent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("POSITION", "" + getAdapterPosition());
                    Listener.onListItemClick(getAdapterPosition());
                }
            });
        }
    }
}
