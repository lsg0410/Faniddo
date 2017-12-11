package com.example.yjkim.faniddo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yjkim.faniddo.domain.SFatherVO;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FatherListAdapter extends RecyclerView.Adapter<FatherListAdapter.CardViewHolder> {
    private List<SFatherVO> itemList = new ArrayList<>();
    private Context context;

    public FatherListAdapter(Context context) {
        this.context = context;
    }

    public void add(SFatherVO vo){
        itemList.add(vo);
        notifyDataSetChanged();
    }

    @Override
    public FatherListAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.father_list_item, null);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FatherListAdapter.CardViewHolder holder, int position) {
        SFatherVO vo = itemList.get(position);

        if(vo.getMprofile() == null || vo.getMprofile().equals("null")){
            holder.falistimg.setImageResource(R.drawable.profile);
        }else{
            Glide.with(context).load("http://192.168.43.11:8080/member/display?fileName=" + vo.getMprofile()).into(holder.falistimg);
        }
        holder.falistmid.setText("     "+vo.getMid());
        holder.falistname.setText("     "+vo.getMname());
        holder.falistemail.setText("     "+vo.getMemail());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CircleImageView falistimg;
        TextView falistmid, falistname, falistemail;

        public CardViewHolder(View itemView) {
            super(itemView);
            falistimg = (CircleImageView) itemView.findViewById(R.id.falistimg);
            falistmid = (TextView) itemView.findViewById(R.id.falistmid);
            falistname = (TextView) itemView.findViewById(R.id.falistname);
            falistemail = (TextView) itemView.findViewById(R.id.falistemail);
        }
    }
}
