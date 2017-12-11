package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.SonBoardVO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by yjkim on 2017-12-03.
 */

public interface SonBoardService {

    @POST("sonboard/register")
    Call<ResponseBody> register(@Body SonBoardVO vo);

    @GET("sonboard/show")
    Call<List<SonBoardVO>> show();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
