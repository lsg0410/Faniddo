package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.FriendVO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yjkim on 2017-11-29.
 */

public interface FriendService {

    @POST("friend/insert")
    Call<ResponseBody> friendadd(@Body FriendVO vo);

    public static final Retrofit retrofit = new Retrofit.Builder()
            //.baseUrl("http://96.97.75.87:8080/")
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
