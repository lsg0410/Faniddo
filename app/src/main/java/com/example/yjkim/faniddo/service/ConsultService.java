package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.ConsultVO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by yjkim on 2017-11-23.
 */

public interface ConsultService {

    @POST("consult/register")
    Call<ResponseBody> register(@Body ConsultVO vo);

    @GET("consult/show")
    Call<List<ConsultVO>> show();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
