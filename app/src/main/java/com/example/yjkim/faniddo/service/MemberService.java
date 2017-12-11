package com.example.yjkim.faniddo.service;

import com.example.yjkim.faniddo.domain.MemberVO;

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
import retrofit2.http.Path;

/**
 * Created by yjkim on 2017-11-10.
 */

public interface MemberService {

    @Multipart
    @POST("member/signup")
    Call<ResponseBody> signup(@Part MultipartBody.Part image, @Part("member") MemberVO vo);

    @Multipart
    @POST("profile/updateimg")
    Call<ResponseBody> updateimg(@Part MultipartBody.Part image, @Part("member") MemberVO vo);

    @POST("member/idcheck")
    Call<ResponseBody> idcheck(@Body String mid);

    @POST("member/showmno")
    Call<ResponseBody> showmno(@Body String mid);

    @POST("member/noimgsignup")
    Call<ResponseBody> noimgsignup(@Body MemberVO vo);

    @POST("member/login")
    Call<ResponseBody> login(@Body String mid);

    @POST("member/password")
    Call<ResponseBody> pwdcheck(@Body MemberVO vo);

    @GET("member/showfile/{mid}")
    Call<ResponseBody> showfile(@Path("mid") String mid);

    @GET("member/showinfo/{mid}")
    Call<MemberVO> showinfo(@Path("mid") String mid);

    public static final Retrofit retrofit = new Retrofit.Builder()
            /*.baseUrl("http://96.97.75.87:8080/")*/
            .baseUrl("http://192.168.43.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /*96.97.75.87*/
    /*192.168.43.185*/
}
