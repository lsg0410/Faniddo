package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.SBReviewVO;
import com.example.yjkim.faniddo.domain.SonBoardVO;
import com.example.yjkim.faniddo.service.SBReviewService;
import com.example.yjkim.faniddo.service.SonBoardService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-12-04.
 */

public class SonBoardContentActivity extends Activity {
    Toolbar mtoolbar;
    String title, content, userdata;
    int sbno;
    TextView son_board_content_title, son_board_content_content;
    TextView son_board_content_reply;
    List<SBReviewVO> sblist;
    RecyclerView son_board_content_rv;
    SBReplyAdapter sbReplyAdapter;
    SwipeRefreshLayout swipe_sb_reply;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.son_board_content);

        son_board_content_title = (TextView) findViewById(R.id.son_board_content_title);
        son_board_content_content = (TextView) findViewById(R.id.son_board_content_content);
        son_board_content_reply = (TextView) findViewById(R.id.son_board_content_reply);

        swipe_sb_reply = (SwipeRefreshLayout) findViewById(R.id.swipe_sb_reply);
        swipe_sb_reply.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_sb_reply.setRefreshing(false);
                son_board_content_rv.setLayoutManager(new LinearLayoutManager(SonBoardContentActivity.this));
                sbReplyAdapter = new SBReplyAdapter(SonBoardContentActivity.this);
                son_board_content_rv.setAdapter(sbReplyAdapter);

                SBReviewService sbservice = SBReviewService.retrofit.create(SBReviewService.class);
                Call<List<SBReviewVO>> sbrcall = sbservice.showreview(sbno);
                sbrcall.enqueue(new Callback<List<SBReviewVO>>() {
                    @Override
                    public void onResponse(Call<List<SBReviewVO>> call, Response<List<SBReviewVO>> response) {
                        sblist = response.body();
                        for(int i = 0; i<sblist.size(); i++){
                            sbReplyAdapter.add(sblist.get(i));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SBReviewVO>> call, Throwable t) {

                    }
                });
            }
        });

        swipe_sb_reply.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        sbno = intent.getExtras().getInt("sbno");
        userdata = intent.getStringExtra("userdata");

        son_board_content_title.setText(title);
        son_board_content_content.setText(content);

        son_board_content_rv = (RecyclerView) findViewById(R.id.son_board_content_rv);
        son_board_content_rv.setLayoutManager(new LinearLayoutManager(SonBoardContentActivity.this));
        sbReplyAdapter = new SBReplyAdapter(SonBoardContentActivity.this);
        son_board_content_rv.setAdapter(sbReplyAdapter);

        SBReviewService sbservice = SBReviewService.retrofit.create(SBReviewService.class);
        Call<List<SBReviewVO>> sbrcall = sbservice.showreview(sbno);
        sbrcall.enqueue(new Callback<List<SBReviewVO>>() {
            @Override
            public void onResponse(Call<List<SBReviewVO>> call, Response<List<SBReviewVO>> response) {
                sblist = response.body();
                for(int i = 0; i<sblist.size(); i++){
                    sbReplyAdapter.add(sblist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<SBReviewVO>> call, Throwable t) {

            }
        });


        Button son_board_content_register = (Button) findViewById(R.id.son_board_content_register);
        son_board_content_register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                SBReviewVO vo = new SBReviewVO();
                vo.setSbno(sbno);
                vo.setSbrcontent(son_board_content_reply.getText().toString());
                vo.setSbrwriter(userdata);

                son_board_content_reply.setText("");

                SBReviewService sbReviewService = SBReviewService.retrofit.create(SBReviewService.class);
                Call<ResponseBody> sbcall = sbReviewService.register(vo);
                sbcall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        sbReplyAdapter = new SBReplyAdapter(SonBoardContentActivity.this);
                        son_board_content_rv.setAdapter(sbReplyAdapter);

                        SBReviewService sbservice = SBReviewService.retrofit.create(SBReviewService.class);
                        Call<List<SBReviewVO>> sbrcall = sbservice.showreview(sbno);
                        sbrcall.enqueue(new Callback<List<SBReviewVO>>() {
                            @Override
                            public void onResponse(Call<List<SBReviewVO>> call, Response<List<SBReviewVO>> response) {
                                sblist = response.body();
                                for(int i = 0; i<sblist.size(); i++){
                                    sbReplyAdapter.add(sblist.get(i));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<SBReviewVO>> call, Throwable t) {

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
