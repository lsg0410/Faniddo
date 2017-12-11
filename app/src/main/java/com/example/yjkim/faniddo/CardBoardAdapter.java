package com.example.yjkim.faniddo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yjkim.faniddo.domain.BoardVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-22.
 */

public class CardBoardAdapter extends RecyclerView.Adapter<CardBoardAdapter.CardViewHolder> implements OnListItemClickListener{

    private List<BoardVO> itemList = new ArrayList<>();
    private Context context;

    public void add(BoardVO vo){
        itemList.add(vo);
        notifyDataSetChanged();
    }

    public CardBoardAdapter(Context context){this.context = context;}

    @Override
    public CardBoardAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, null);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        cardViewHolder.setOnListItemClickListener(this);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardBoardAdapter.CardViewHolder holder, int position) {
        BoardVO vo = itemList.get(position);

        if(vo.getBfilename() == null || vo.getBfilename().equals("null")){
            holder.ivimg.setImageResource(R.drawable.basic);
        }else {
            Glide.with(context).load("http://192.168.43.11:8080/board/display?fileName=" + vo.getBfilename()).into(holder.ivimg);
        }
        holder.tvtitle.setText(vo.getBtitle());
        holder.tvwriter.setText(vo.getBwriter());
        holder.tvregdate.setText(vo.getBregdate());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(context, GeneralActivity.class);
        intent.putExtra("title", itemList.get(position).getBtitle());
        intent.putExtra("content", itemList.get(position).getBcontent());
        intent.putExtra("writer", itemList.get(position).getBwriter());
        intent.putExtra("regdate", itemList.get(position).getBregdate());
        if(itemList.get(position).getBfilename() == null || itemList.get(position).getBfilename().equals("null")){
            String noimg = "null";
            intent.putExtra("filename", noimg);
        }else{
            intent.putExtra("filename", itemList.get(position).getBfilename());
        }
        context.startActivity(intent);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView ivimg;
        TextView tvtitle, tvwriter, tvregdate;

        public OnListItemClickListener mListener;

        public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
            mListener = onListItemClickListener;
        }

        public CardViewHolder(View itemView){
            super(itemView);
            ivimg = (ImageView) itemView.findViewById(R.id.ivimg);
            tvtitle = (TextView) itemView.findViewById(R.id.tvtitle);
            tvwriter = (TextView) itemView.findViewById(R.id.tvwriter);
            tvregdate = (TextView) itemView.findViewById(R.id.tvregdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("POSITION", "" + getAdapterPosition());
                    mListener.onListItemClick(getAdapterPosition());
                }
            });
        }
    }
}
