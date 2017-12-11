package com.example.yjkim.faniddo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yjkim.faniddo.domain.SFriendVO;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.CardViewHolder> {

    private List<SFriendVO> itemList = new ArrayList<>();
    private Context context;

    public FriendListAdapter(Context context) {
        this.context = context;
    }

    public void add(SFriendVO vo){
        itemList.add(vo);
        notifyDataSetChanged();
    }

    @Override
    public FriendListAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_list_item, null);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FriendListAdapter.CardViewHolder holder, int position) {
        SFriendVO vo = itemList.get(position);

        if(vo.getMprofile() == null || vo.getMprofile().equals("null")){
            holder.flistimg.setImageResource(R.drawable.profile);
        }else {
            Glide.with(context).load("http://192.168.43.11:8080/member/display?fileName=" + vo.getMprofile()).into(holder.flistimg);
        }
        holder.flistmid.setText("     "+vo.getMid());
        holder.flistmname.setText("     "+vo.getMname());
        holder.flistemail.setText("     "+vo.getMemail());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        CircleImageView flistimg;
        TextView flistmid, flistmname, flistemail;

        public CardViewHolder(View itemView) {
            super(itemView);
            flistimg = (CircleImageView) itemView.findViewById(R.id.flistimg);
            flistmid = (TextView) itemView.findViewById(R.id.flistmid);
            flistmname = (TextView) itemView.findViewById(R.id.flistmname);
            flistemail = (TextView) itemView.findViewById(R.id.flistemail);
        }
    }
}