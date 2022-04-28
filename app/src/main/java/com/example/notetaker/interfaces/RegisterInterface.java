package com.example.notetaker.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {
    String url = "http://192.168.0.7/android_connect/";

    @FormUrlEncoded
    @POST("register.php")
    Call<String> getUserRegi(
            @Field("username") String uname,
            @Field("password") String password
    );
}
