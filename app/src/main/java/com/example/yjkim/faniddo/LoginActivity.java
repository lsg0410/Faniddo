package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjkim.faniddo.domain.MemberVO;
import com.example.yjkim.faniddo.service.MemberService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-09.
 */

public class LoginActivity extends Activity {
    TextView login_id, login_password;

    @Override
    protected void onResume() {
        super.onResume();
        login_id.setText("");
        login_password.setText("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login_id = (TextView)findViewById(R.id.login_id);
        login_password = (TextView) findViewById(R.id.login_password);

        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login_id.getText().toString().length() == 0 || login_password.getText().toString().length() == 0){
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    MemberService service = MemberService.retrofit.create(MemberService.class);
                    Call<ResponseBody> call = service.login(login_id.getText().toString());
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String password = login_password.getText().toString();
                            try {
                              if(response.body().string().equals("NO")){
                                  Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
                              }else{

                                  MemberVO vo = new MemberVO();
                                  vo.setMid(login_id.getText().toString());
                                  vo.setMpwd(login_password.getText().toString());
                                  MemberService service = MemberService.retrofit.create(MemberService.class);
                                  Call<ResponseBody> check = service.pwdcheck(vo);
                                  check.enqueue(new Callback<ResponseBody>() {
                                      @Override
                                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                          try {
                                              if(response.body().string().equals("NO")){
                                                  Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
                                              }else{
                                                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                  intent.putExtra("userid", login_id.getText().toString());
                                                  startActivity(intent);
                                                  /*finish();*/
                                              }
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                      }

                                      @Override
                                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                                          t.printStackTrace();
                                      }
                                  });

                              }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

            }
        });

        Button signup_btn = (Button) findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
