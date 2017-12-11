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

import com.example.yjkim.faniddo.domain.SonBoardVO;
import com.example.yjkim.faniddo.service.SonBoardService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-12-02.
 */

public class SonBoardRegisterActivity extends Activity {
    Toolbar mtoolbar;
    String userid;
    TextView son_board_title, son_board_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.son_board_register);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 자녀 게시판 작성");

        Intent intent = getIntent();
        userid = intent.getStringExtra("writer");

        son_board_title = (TextView) findViewById(R.id.son_board_title);
        son_board_content = (TextView) findViewById(R.id.son_board_content);

        Button son_board_send = (Button) findViewById(R.id.son_board_send);
        son_board_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(son_board_title.getText().toString().length() == 0 || son_board_content.getText().toString().length() == 0){
                    Toast.makeText(SonBoardRegisterActivity.this, "제목과 내용을 전부 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    SonBoardVO vo = new SonBoardVO();
                    vo.setSbtitle(son_board_title.getText().toString());
                    vo.setSbcontent(son_board_content.getText().toString());
                    vo.setSbwriter(userid);

                    SonBoardService sonBoardService = SonBoardService.retrofit.create(SonBoardService.class);
                    Call<ResponseBody> sbcall = sonBoardService.register(vo);
                    sbcall.enqueue(new Callback<ResponseBody>() {
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
