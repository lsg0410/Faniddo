package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjkim.faniddo.service.MissionService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-22.
 */

public class MissionContentActivity extends Activity {
    TextView mstitle, mscontent;
    String mtitle, mcontent;
    Toolbar mtoolbar;
    int mnumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_mission_content);

        mstitle = (TextView) findViewById(R.id.mstitle);
        mscontent = (TextView) findViewById(R.id.mscontent);

        Intent intent = getIntent();
        mtitle = intent.getStringExtra("title");
        mcontent = intent.getStringExtra("content");
        mnumber = intent.getExtras().getInt("mno");


        mstitle.setText(mtitle);
        mscontent.setText(mcontent);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo My Mission");

        Button mission_complete = (Button) findViewById(R.id.mission_complete);
        mission_complete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                MissionService service = MissionService.retrofit.create(MissionService.class);
                Call<ResponseBody> call = service.missioncomplete(mnumber);
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
