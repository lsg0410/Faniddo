package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.MissionVO;

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
 * Created by yjkim on 2017-11-22.
 */

public interface MissionService {

    @POST("mission/register")
    Call<ResponseBody> missionregister(@Body MissionVO vo);

    @GET("mission/show/{mwriter}")
    Call<List<MissionVO>> showmission(@Path("mwriter") String mwirter);

    @GET("mission/cshow/{mwriter}")
    Call<List<MissionVO>> cshowmission(@Path("mwriter") String mwirter);

    @POST("mission/mcomplete")
    Call<ResponseBody> missioncomplete(@Body int mno);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
