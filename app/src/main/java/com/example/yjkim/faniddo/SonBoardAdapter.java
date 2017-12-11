package com.example.yjkim.faniddo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yjkim.faniddo.domain.SonBoardVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-12-03.
 */

public class SonBoardAdapter extends RecyclerView.Adapter<SonBoardHolder> implements OnListItemClickListener {
    Context context;
    List<SonBoardVO> items = new ArrayList<>();
    String userdata;

    public void add(SonBoardVO vo){
        items.add(vo);
        notifyDataSetChanged();
    }

    public SonBoardAdapter(Context context, String userdata) {
        this.context = context;
        this.userdata = userdata;
    }

    @Override
    public SonBoardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.son_board_item, parent, false);
        SonBoardHolder holder = new SonBoardHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(SonBoardHolder holder, int position) {
        SonBoardVO vo = items.get(position);
        holder.son_board_title.setText("  "+vo.getSbtitle());
        holder.son_board_writer.setText("  "+vo.getSbwriter());
        holder.son_board_regdate.setText(vo.getSbregdate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(context, SonBoardContentActivity.class);
        intent.putExtra("title", items.get(position).getSbtitle());
        intent.putExtra("content", items.get(position).getSbcontent());
        intent.putExtra("sbno", items.get(position).getSbno());
        intent.putExtra("userdata", userdata);
        context.startActivity(intent);
    }
}
