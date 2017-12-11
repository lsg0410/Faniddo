package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.BoardVO;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by yjkim on 2017-11-22.
 */

public interface BoardService {

    @Multipart
    @POST("board/register")
    Call<ResponseBody> register(@Part MultipartBody.Part image, @Part("board")BoardVO vo);

    @POST("board/noimgregister")
    Call<ResponseBody> noimgregister(@Body BoardVO vo);

    @GET("board/show")
    Call<List<BoardVO>> showlist();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    /*192.168.43.11*/
}
