package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.LetterVO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yjkim on 2017-11-14.
 */

public interface LetterService {

    @POST("letter/send")
    Call<ResponseBody> send(@Body LetterVO vo);

    @GET("letter/list/{user}")
    Call<List<LetterVO>> show(@Path("user") String user);

    public static final Retrofit lretrofit = new Retrofit.Builder()
            //.baseUrl("http://96.97.75.87:8080/")
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
