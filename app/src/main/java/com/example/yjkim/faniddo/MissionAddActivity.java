package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjkim.faniddo.domain.MissionVO;
import com.example.yjkim.faniddo.service.MissionService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-22.
 */

public class MissionAddActivity extends Activity {
    Toolbar mtoolbar;
    String userid;
    TextView mission_title, mission_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_register);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 미션생성하기");

        Intent intent = getIntent();
        userid = intent.getStringExtra("writer");

        mission_title = (TextView) findViewById(R.id.mission_title);
        mission_content = (TextView) findViewById(R.id.mission_content);

        Button mission_send = (Button) findViewById(R.id.mission_send);
        mission_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mission_title.getText().toString().length() == 0 || mission_content.getText().toString().length() == 0){
                    Toast.makeText(MissionAddActivity.this, "미션제목과 내용을 모두 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    MissionVO vo = new MissionVO();
                    vo.setMtitle(mission_title.getText().toString());
                    vo.setMcontent(mission_content.getText().toString());
                    vo.setMwriter(userid);

                    MissionService service = MissionService.retrofit.create(MissionService.class);
                    Call<ResponseBody> call = service.missionregister(vo);
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
            }
        });

    }
}
