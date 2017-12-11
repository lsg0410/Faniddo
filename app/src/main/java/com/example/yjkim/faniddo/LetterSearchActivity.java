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
import android.util.Log;

import com.example.yjkim.faniddo.domain.LetterVO;
import com.example.yjkim.faniddo.service.LetterService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-14.
 */

public class LetterSearchActivity extends Activity {

    Toolbar mtoolbar;
    String lwriter;

    RecyclerView lRecyclerView;
    LetterAdapter lAdaper;
    RecyclerView.LayoutManager lLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_letter);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 한줄편지보기");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_letter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        Intent intent = getIntent();
        lwriter = intent.getStringExtra("lwriter");

        lAdaper = new LetterAdapter(LetterSearchActivity.this);
        lRecyclerView = (RecyclerView) findViewById(R.id.letter_recycler);
        lLayoutManager = new LinearLayoutManager(this);
        lRecyclerView.setLayoutManager(lLayoutManager);
        lRecyclerView.setAdapter(lAdaper);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
        lRecyclerView.addItemDecoration(dividerItemDecoration);

        LetterService service = LetterService.lretrofit.create(LetterService.class);
        Call<List<LetterVO>> call = service.show(lwriter);
        call.enqueue(new Callback<List<LetterVO>>() {
            @Override
            public void onResponse(Call<List<LetterVO>> call, Response<List<LetterVO>> response) {
                List<LetterVO> list = response.body();

                for (int i = 0; i < list.size(); i++) {
                    lAdaper.add(list.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<LetterVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
