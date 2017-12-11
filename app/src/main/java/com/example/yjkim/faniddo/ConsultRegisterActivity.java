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

import com.example.yjkim.faniddo.domain.ConsultVO;
import com.example.yjkim.faniddo.service.ConsultService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-23.
 */

public class ConsultRegisterActivity extends Activity {

    Toolbar mtoolbar;
    String userid;
    TextView consult_title, consult_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_register);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 고민상담작성");

        Intent intent = getIntent();
        userid = intent.getStringExtra("writer");

        consult_title = (TextView) findViewById(R.id.consult_title);
        consult_content = (TextView) findViewById(R.id.consult_content);

        Button consult_send = (Button) findViewById(R.id.consult_send);
        consult_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(consult_title.getText().toString().length() == 0 || consult_content.getText().toString().length() == 0){
                    Toast.makeText(ConsultRegisterActivity.this, "고민상담 제목과 내용을 모두 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    ConsultVO vo = new ConsultVO();
                    vo.setCtitle(consult_title.getText().toString());
                    vo.setCcontent(consult_content.getText().toString());
                    vo.setCwriter(userid);
                    ConsultService service = ConsultService.retrofit.create(ConsultService.class);
                    Call<ResponseBody> call = service.register(vo);
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
