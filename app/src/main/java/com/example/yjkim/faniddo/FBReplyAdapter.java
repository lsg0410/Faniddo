package com.example.yjkim.faniddo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.FBReviewVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-12-01.
 */

public class FBReplyAdapter extends RecyclerView.Adapter<FBReplyAdapter.FBRViewHolder> {
    private List<FBReviewVO> itemList = new ArrayList<>();
    private Context context;

    public void add(FBReviewVO vo){
        itemList.add(vo);
        notifyDataSetChanged();
    }

    public FBReplyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FBReplyAdapter.FBRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.father_board_reply_item, null);
        FBRViewHolder holder = new FBRViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FBReplyAdapter.FBRViewHolder holder, int position) {
        FBReviewVO vo = itemList.get(position);
        holder.father_board_reply_id.setText("작성자: "+vo.getFbrwriter());
        holder.father_board_reply.setText(vo.getFbrcontent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class FBRViewHolder extends RecyclerView.ViewHolder{
        TextView father_board_reply_id, father_board_reply;

        public FBRViewHolder(View itemView) {
            super(itemView);
            father_board_reply_id = (TextView) itemView.findViewById(R.id.father_board_reply_id);
            father_board_reply = (TextView) itemView.findViewById(R.id.father_board_reply);
        }
    }
}
