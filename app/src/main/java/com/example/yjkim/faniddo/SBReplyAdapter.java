package com.example.yjkim.faniddo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.SBReviewVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-12-04.
 */

public class SBReplyAdapter extends RecyclerView.Adapter<SBReplyAdapter.SBRViewHolder> {
    private List<SBReviewVO> itemList = new ArrayList<>();
    private Context context;

    public void add(SBReviewVO vo){
        itemList.add(vo);
        notifyDataSetChanged();
    }

    public SBReplyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SBRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.son_board_reply_item, null);
        SBRViewHolder holder = new SBRViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SBRViewHolder holder, int position) {
        SBReviewVO vo = itemList.get(position);
        holder.son_board_reply_id.setText("작성자: "+vo.getSbrwriter());
        holder.son_board_reply.setText(vo.getSbrcontent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class SBRViewHolder extends RecyclerView.ViewHolder{
        TextView son_board_reply_id, son_board_reply;

        public SBRViewHolder(View itemView) {
            super(itemView);
            son_board_reply_id = (TextView) itemView.findViewById(R.id.son_board_reply_id);
            son_board_reply = (TextView) itemView.findViewById(R.id.son_board_reply);
        }
    }
}
