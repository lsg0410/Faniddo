package com.example.yjkim.faniddo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yjkim.faniddo.domain.LetterVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-14.
 */

public class LetterAdapter extends RecyclerView.Adapter<LetterHolder> implements letterItemClickListener {
    Context mContext;

    public LetterAdapter(Context context) {
        mContext = context;
    }
    List<LetterVO> items = new ArrayList<>();
    public void add(LetterVO vo){
        items.add(vo);
        notifyDataSetChanged();
    }

    @Override
    public LetterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_item, parent, false);
        LetterHolder holder = new LetterHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(LetterHolder holder, int position) {
        LetterVO vo = items.get(position);
        holder.letter_search_title.setText(vo.getLtitle());
        holder.letter_search_regdate.setText(vo.getLregdate());
        holder.letter_search_writer.setText(vo.getLwriter());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(mContext, LetterContentActivity.class);
        intent.putExtra("writer", items.get(position).getLwriter());
        intent.putExtra("title", items.get(position).getLtitle());
        intent.putExtra("content", items.get(position).getLcontent());
        intent.putExtra("regdate", items.get(position).getLregdate());
        mContext.startActivity(intent);
    }
}
