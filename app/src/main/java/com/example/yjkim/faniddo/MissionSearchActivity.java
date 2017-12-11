package com.example.yjkim.faniddo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.yjkim.faniddo.domain.MissionVO;
import com.example.yjkim.faniddo.service.MissionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yjkim on 2017-11-23.
 */

public class MissionSearchActivity extends Activity {
    Toolbar mtoolbar;
    String userid;
    RecyclerView mrv;
    CompleteMissionAdapter completeMissionAdapter;
    List<MissionVO> cmlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_search);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar1);
        mtoolbar.setTitle("Faniddo 수행한 미션");

        Intent intent = getIntent();
        userid = intent.getStringExtra("writer");

        mrv = (RecyclerView) findViewById(R.id.rvcmission);
        mrv.setLayoutManager(new LinearLayoutManager(this));
        completeMissionAdapter = new CompleteMissionAdapter(this);
        mrv.setAdapter(completeMissionAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MissionSearchActivity.this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
        mrv.addItemDecoration(dividerItemDecoration);

        MissionService service = MissionService.retrofit.create(MissionService.class);
        Call<List<MissionVO>> call = service.cshowmission(userid);
        call.enqueue(new Callback<List<MissionVO>>() {
            @Override
            public void onResponse(Call<List<MissionVO>> call, Response<List<MissionVO>> response) {
                cmlist = response.body();
                for(int i = 0; i<cmlist.size(); i++){
                    completeMissionAdapter.add(cmlist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<MissionVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
