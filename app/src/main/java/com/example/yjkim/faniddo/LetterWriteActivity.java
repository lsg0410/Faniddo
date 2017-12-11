package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.LetterVO;
import com.example.yjkim.faniddo.service.LetterService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-14.
 */

public class LetterWriteActivity extends Activity {
    Toolbar mtoolbar;
    String lwriter;
    TextView letter_sendid, letter_title,letter_content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_letter);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 한줄편지쓰기");

        letter_sendid = (TextView) findViewById(R.id.letter_sendid);
        letter_title = (TextView) findViewById(R.id.letter_title);
        letter_content = (TextView) findViewById(R.id.letter_content);

        Intent intent = getIntent();
        lwriter = intent.getStringExtra("lwriter");

        Button letter_send = (Button) findViewById(R.id.letter_send);
        letter_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                LetterVO vo = new LetterVO();
                vo.setLtitle(letter_title.getText().toString());
                vo.setLcontent(letter_content.getText().toString());
                vo.setLwriter(lwriter);
                vo.setLsender(letter_sendid.getText().toString());

                LetterService service = LetterService.lretrofit.create(LetterService.class);
                Call<ResponseBody> call = service.send(vo);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}
