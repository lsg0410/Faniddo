package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjkim.faniddo.domain.CReviewVO;
import com.example.yjkim.faniddo.service.CReviewService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-23.
 */

public class ConsultContentActivity extends Activity {
    Toolbar mtoolbar;
    String ctitle, ccontent, userdata;  //userdata = 현재 로그인된 아이디
    int cnumber;
    TextView wctitle, wccontent, wcreply_write;
    List<CReviewVO> crlist;
    RecyclerView crv;
    CReplyAdapter cReplyAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_reply_content);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 고민 상담");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_letter_reply);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                cReplyAdapter = new CReplyAdapter(ConsultContentActivity.this);
                crv.setAdapter(cReplyAdapter);

                CReviewService crservice = CReviewService.retrofit.create(CReviewService.class);
                Call<List<CReviewVO>> crcall = crservice.showreview(cnumber);
                crcall.enqueue(new Callback<List<CReviewVO>>() {
                    @Override
                    public void onResponse(Call<List<CReviewVO>> call, Response<List<CReviewVO>> response) {
                        crlist = response.body();
                        for (int i = 0; i < crlist.size(); i++) {
                            cReplyAdapter.add(crlist.get(i));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CReviewVO>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        Intent intent = getIntent();
        ctitle = intent.getStringExtra("ctitle");
        ccontent = intent.getStringExtra("ccontent");
        cnumber = intent.getExtras().getInt("cno");
        userdata = intent.getStringExtra("userdata");


        wctitle = (TextView) findViewById(R.id.wctitle);
        wccontent = (TextView) findViewById(R.id.wccontent);
        wcreply_write = (TextView) findViewById(R.id.wcreply_write);

        wctitle.setText(ctitle);
        wccontent.setText(ccontent);

        crv = (RecyclerView) findViewById(R.id.wcreply);
        crv.setLayoutManager(new LinearLayoutManager(this));
        cReplyAdapter = new CReplyAdapter(this);
        crv.setAdapter(cReplyAdapter);

        CReviewService crservice = CReviewService.retrofit.create(CReviewService.class);
        Call<List<CReviewVO>> crcall = crservice.showreview(cnumber);
        crcall.enqueue(new Callback<List<CReviewVO>>() {
            @Override
            public void onResponse(Call<List<CReviewVO>> call, Response<List<CReviewVO>> response) {
                crlist = response.body();
                for (int i = 0; i < crlist.size(); i++) {
                    cReplyAdapter.add(crlist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<CReviewVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Button register_reply = (Button) findViewById(R.id.register_reply);
        register_reply.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                CReviewVO vo = new CReviewVO();
                vo.setCno(cnumber);
                vo.setCrcontent(wcreply_write.getText().toString());
                vo.setCrwriter(userdata);

                wcreply_write.setText("");

                CReviewService service = CReviewService.retrofit.create(CReviewService.class);
                Call<ResponseBody> call = service.register(vo);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        cReplyAdapter = new CReplyAdapter(ConsultContentActivity.this);
                        crv.setAdapter(cReplyAdapter);

                        CReviewService crservice = CReviewService.retrofit.create(CReviewService.class);
                        Call<List<CReviewVO>> crcall = crservice.showreview(cnumber);
                        crcall.enqueue(new Callback<List<CReviewVO>>() {
                            @Override
                            public void onResponse(Call<List<CReviewVO>> call, Response<List<CReviewVO>> response) {
                                crlist = response.body();
                                for (int i = 0; i < crlist.size(); i++) {
                                    cReplyAdapter.add(crlist.get(i));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<CReviewVO>> call, Throwable t) {
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
