package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by yjkim on 2017-11-14.
 */

public class LetterContentActivity extends Activity {

    Toolbar mtoolbar;
    TextView content_sender,content_title, content_content, content_regdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter_content);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 한줄편지내용");

        content_sender = (TextView) findViewById(R.id.content_sender);
        content_title = (TextView) findViewById(R.id.content_title);
        content_content = (TextView) findViewById(R.id.content_content);
        content_regdate = (TextView) findViewById(R.id.content_regdate);

        Intent intent = getIntent();
        String datawriter = intent.getStringExtra("writer");
        String datatitle = intent.getStringExtra("title");
        String datacontent = intent.getStringExtra("content");
        String dataregdate = intent.getStringExtra("regdate");

        content_sender.setText(datawriter);
        content_title.setText(datatitle);
        content_content.setText(datacontent);
        content_regdate.setText(dataregdate);
    }
}
