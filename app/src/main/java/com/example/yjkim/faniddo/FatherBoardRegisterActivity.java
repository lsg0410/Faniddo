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

import com.example.yjkim.faniddo.domain.FatherBoardVO;
import com.example.yjkim.faniddo.service.FatherBoardService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FatherBoardRegisterActivity extends Activity {
    Toolbar mtoolbar;
    String userid;
    TextView father_board_title, father_board_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.father_board_register);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 아빠 게시판 작성");

        Intent intent = getIntent();
        userid = intent.getStringExtra("writer");

        father_board_title = (TextView) findViewById(R.id.father_board_title);
        father_board_content = (TextView) findViewById(R.id.father_board_content);

        Button father_board_send = (Button) findViewById(R.id.father_board_send);
        father_board_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(father_board_title.getText().toString().length() == 0 || father_board_content.getText().toString().length() == 0){
                    Toast.makeText(FatherBoardRegisterActivity.this, "제목과 내용을 전부 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    FatherBoardVO vo = new FatherBoardVO();
                    vo.setFbtitle(father_board_title.getText().toString());
                    vo.setFbcontent(father_board_content.getText().toString());
                    vo.setFbwriter(userid);

                    FatherBoardService fatherBoardService = FatherBoardService.retrofit.create(FatherBoardService.class);
                    Call<ResponseBody> fatherBoardCall = fatherBoardService.register(vo);
                    fatherBoardCall.enqueue(new Callback<ResponseBody>() {
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
