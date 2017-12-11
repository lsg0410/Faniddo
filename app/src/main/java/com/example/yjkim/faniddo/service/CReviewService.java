package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.CReviewVO;

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
 * Created by yjkim on 2017-11-23.
 */

public interface CReviewService {

    @POST("creview/register")
    Call<ResponseBody> register(@Body CReviewVO vo);

    @GET("creview/show/{cno}")
    Call<List<CReviewVO>> showreview(@Path("cno") int cno);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
