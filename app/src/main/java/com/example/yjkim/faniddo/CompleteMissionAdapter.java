package com.example.yjkim.faniddo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.MissionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-23.
 */

public class CompleteMissionAdapter extends RecyclerView.Adapter<CompleteMissionAdapter.MissionViewHolder> {

    private List<MissionVO> itemList = new ArrayList<>();
    private Context context;

    public void add(MissionVO vo){
        itemList.add(vo);
        notifyDataSetChanged();
    }

    public CompleteMissionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CompleteMissionAdapter.MissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cmission_item, null);
        MissionViewHolder missionViewHolder = new MissionViewHolder(view);
        return missionViewHolder;
    }

    @Override
    public void onBindViewHolder(CompleteMissionAdapter.MissionViewHolder holder, int position) {
        MissionVO vo = itemList.get(position);
        Log.d("LOG", "-------------"+vo);
        holder.cmission_tvtitle.setText(vo.getMtitle());
        holder.cmission_tvcontent.setText(vo.getMcontent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MissionViewHolder extends RecyclerView.ViewHolder{
        TextView cmission_tvtitle, cmission_tvcontent;

        public MissionViewHolder(View itemView) {
            super(itemView);
            cmission_tvtitle = (TextView) itemView.findViewById(R.id.cmission_tvtitle);
            cmission_tvcontent = (TextView) itemView.findViewById(R.id.cmission_tvcontent);
        }
    }
}
