package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjkim.faniddo.R;
import com.example.yjkim.faniddo.domain.BoardVO;
import com.example.yjkim.faniddo.domain.FBReviewVO;
import com.example.yjkim.faniddo.service.BoardService;
import com.example.yjkim.faniddo.service.FBReviewService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-12-01.
 */

public class FatherBoardContentActivity extends Activity {
    Toolbar mtoolbar;
    String title, content, userdata;
    int fbno;
    TextView father_board_content_title, father_board_content_content, father_board_content_reply;
    TextView father_board_reply_id, father_board_reply;
    List<FBReviewVO> fbrlist;
    RecyclerView fbrrv;
    FBReplyAdapter fbReplyAdapter;
    SwipeRefreshLayout swipe_fb_reply;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.father_board_content);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 아빠들의 게시판");

        father_board_content_title = (TextView) findViewById(R.id.father_board_content_title);
        father_board_content_content = (TextView) findViewById(R.id.father_board_content_content);
        father_board_content_reply = (TextView) findViewById(R.id.father_board_content_reply);


        swipe_fb_reply = (SwipeRefreshLayout) findViewById(R.id.swipe_fb_reply);
        swipe_fb_reply.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_fb_reply.setRefreshing(false);
                fbrrv.setLayoutManager(new LinearLayoutManager(FatherBoardContentActivity.this));
                fbReplyAdapter = new FBReplyAdapter(FatherBoardContentActivity.this);
                fbrrv.setAdapter(fbReplyAdapter);

                FBReviewService fbReviewService = FBReviewService.retrofit.create(FBReviewService.class);
                Call<List<FBReviewVO>> fbcall = fbReviewService.showreview(fbno);
                fbcall.enqueue(new Callback<List<FBReviewVO>>() {
                    @Override
                    public void onResponse(Call<List<FBReviewVO>> call, Response<List<FBReviewVO>> response) {
                        fbrlist = response.body();
                        for (int i = 0; i < fbrlist.size(); i++) {
                            fbReplyAdapter.add(fbrlist.get(i));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FBReviewVO>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }
        });

        swipe_fb_reply.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        fbno = intent.getExtras().getInt("fbno");
        userdata = intent.getStringExtra("userdata");

        father_board_content_title.setText(title);
        father_board_content_content.setText(content);

        father_board_reply_id = (TextView) findViewById(R.id.father_board_reply_id);
        father_board_reply = (TextView) findViewById(R.id.father_board_reply);

        fbrrv = (RecyclerView) findViewById(R.id.father_board_content_rv);
        fbrrv.setLayoutManager(new LinearLayoutManager(this));
        fbReplyAdapter = new FBReplyAdapter(this);
        fbrrv.setAdapter(fbReplyAdapter);

        FBReviewService fbReviewService = FBReviewService.retrofit.create(FBReviewService.class);
        Call<List<FBReviewVO>> fbcall = fbReviewService.showreview(fbno);
        fbcall.enqueue(new Callback<List<FBReviewVO>>() {
            @Override
            public void onResponse(Call<List<FBReviewVO>> call, Response<List<FBReviewVO>> response) {
                fbrlist = response.body();
                for (int i = 0; i < fbrlist.size(); i++) {
                    fbReplyAdapter.add(fbrlist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<FBReviewVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Button father_board_content_register = (Button) findViewById(R.id.father_board_content_register);
        father_board_content_register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBReviewVO vo = new FBReviewVO();
                vo.setFbno(fbno);
                vo.setFbrcontent(father_board_content_reply.getText().toString());
                vo.setFbrwriter(userdata);

                father_board_content_reply.setText("");

                FBReviewService fbReviewService = FBReviewService.retrofit.create(FBReviewService.class);
                Call<ResponseBody> call = fbReviewService.register(vo);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        fbReplyAdapter = new FBReplyAdapter(FatherBoardContentActivity.this);
                        fbrrv.setAdapter(fbReplyAdapter);

                        FBReviewService fbReviewService = FBReviewService.retrofit.create(FBReviewService.class);
                        Call<List<FBReviewVO>> fbcall = fbReviewService.showreview(fbno);
                        fbcall.enqueue(new Callback<List<FBReviewVO>>() {
                            @Override
                            public void onResponse(Call<List<FBReviewVO>> call, Response<List<FBReviewVO>> response) {
                                fbrlist = response.body();
                                for (int i = 0; i < fbrlist.size(); i++) {
                                    fbReplyAdapter.add(fbrlist.get(i));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<FBReviewVO>> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
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
