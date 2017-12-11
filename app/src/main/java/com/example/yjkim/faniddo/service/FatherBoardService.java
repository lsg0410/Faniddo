package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.FatherBoardVO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by yjkim on 2017-11-29.
 */

public interface FatherBoardService {

    @POST("fatherboard/register")
    Call<ResponseBody> register(@Body FatherBoardVO vo);

    @GET("fatherboard/show")
    Call<List<FatherBoardVO>> show();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    /*192.168.43.243*/
}
