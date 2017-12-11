package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by yjkim on 2017-11-22.
 */

public class GeneralActivity extends Activity {
    TextView showtitle, showcontent, showwriter, showdate;
    String itemtitle, itemcontent, itemwriter, itemregdate, filename , itembno;
    ImageView ivitemshow;
    Toolbar mtoolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_content);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 일반게시글 보기");

        ivitemshow = (ImageView) findViewById(R.id.ivitemshow);
        showtitle = (TextView) findViewById(R.id.itemtitle);
        showcontent = (TextView) findViewById(R.id.itemcontent);
        showwriter = (TextView) findViewById(R.id.itemwriter);
        showdate = (TextView) findViewById(R.id.itemregdate);

        Intent intent = getIntent();
        itemtitle = intent.getStringExtra("title");
        itemcontent = intent.getStringExtra("content");
        itemwriter = intent.getStringExtra("writer");
        itemregdate = intent.getStringExtra("regdate");
        filename = intent.getStringExtra("filename");



        showtitle.setText(itemtitle);
        showcontent.setText(itemcontent);
        showwriter.setText(itemwriter);
        showdate.setText(itemregdate);

        if(filename.equals("null")){

        }else{
            Glide.with(this).load("http://192.168.43.11:8080/board/display?fileName="+filename).into(ivitemshow);
        }
    }
}
