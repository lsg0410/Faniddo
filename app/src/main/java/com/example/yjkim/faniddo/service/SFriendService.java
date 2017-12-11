package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.SFriendVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yjkim on 2017-11-29.
 */

public interface SFriendService {

    @GET("sfriend/show/{mno}")
    Call<List<SFriendVO>> showfriend(@Path("mno") int mno);

    public static final Retrofit retrofit = new Retrofit.Builder()
            //.baseUrl("http://96.97.75.87:8080/")
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
