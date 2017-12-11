package com.example.yjkim.faniddo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yjkim.faniddo.domain.ConsultVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-23.
 */

public class ConsultAdapter extends RecyclerView.Adapter<ConsultHolder> implements OnListItemClickListener{
    Context context;
    List<ConsultVO> items = new ArrayList<>();
    String userdata;

    public void add(ConsultVO vo){
        items.add(vo);
        notifyDataSetChanged();
    }

    public ConsultAdapter(Context context, String userdata) {
        this.context = context;
        this.userdata = userdata;
    }

    @Override
    public ConsultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_item, parent, false);
        ConsultHolder holder = new ConsultHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConsultHolder holder, int position) {
        ConsultVO vo = items.get(position);
        holder.consult_main_title.setText(vo.getCtitle());
        holder.consult_side.setText("* 고민");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(context, ConsultContentActivity.class);
        intent.putExtra("ctitle", items.get(position).getCtitle());
        intent.putExtra("ccontent", items.get(position).getCcontent());
        intent.putExtra("cno", items.get(position).getCno());
        intent.putExtra("userdata", userdata);
        context.startActivity(intent);

    }
}
