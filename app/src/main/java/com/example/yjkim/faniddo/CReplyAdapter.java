package com.example.yjkim.faniddo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.CReviewVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjkim on 2017-11-23.
 */

public class CReplyAdapter extends RecyclerView.Adapter<CReplyAdapter.RViewHolder> {

    private List<CReviewVO> itemList = new ArrayList<>();
    private Context context;

    public void add(CReviewVO vo) {
        itemList.add(vo);
        notifyDataSetChanged();
    }

    public CReplyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CReplyAdapter.RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.worry_consult_item, null);
        RViewHolder rViewHolder = new RViewHolder(view);
        return rViewHolder;
    }

    @Override
    public void onBindViewHolder(CReplyAdapter.RViewHolder holder, int position) {
        CReviewVO vo = itemList.get(position);

        Log.d("LOGLOGLOG", ""+vo);
        holder.worry_reply_id.setText("작성자: "+vo.getCrwriter());
        holder.worry_reply.setText(vo.getCrcontent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class RViewHolder extends RecyclerView.ViewHolder {
        TextView worry_reply_id, worry_reply;

        public RViewHolder(View itemView) {
            super(itemView);
            worry_reply_id = (TextView) itemView.findViewById(R.id.worry_reply_id);
            worry_reply = (TextView) itemView.findViewById(R.id.worry_reply);
        }
    }
}
