package com.example.yjkim.faniddo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yjkim.faniddo.domain.FatherBoardVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FatherBoardAdapter extends RecyclerView.Adapter<FatherBoardHolder> implements OnListItemClickListener {
    Context context;
    List<FatherBoardVO> items = new ArrayList<>();
    String userdata;

    public void add(FatherBoardVO vo){
        items.add(vo);
        notifyDataSetChanged();
    }

    public FatherBoardAdapter(Context context, String userdata) {
        this.context = context;
        this.userdata = userdata;
    }

    @Override
    public FatherBoardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.father_board_item, parent, false);
        FatherBoardHolder holder = new FatherBoardHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(FatherBoardHolder holder, int position) {
        FatherBoardVO vo = items.get(position);
        holder.father_board_title.setText("  "+vo.getFbtitle());
        holder.father_board_writer.setText("  "+vo.getFbwriter());
        holder.father_board_regdate.setText(vo.getFbregdate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(context, FatherBoardContentActivity.class);
        intent.putExtra("title", items.get(position).getFbtitle());
        intent.putExtra("content", items.get(position).getFbcontent());
        intent.putExtra("fbno", items.get(position).getFbno());
        intent.putExtra("userdata", userdata);
        context.startActivity(intent);
    }
}
