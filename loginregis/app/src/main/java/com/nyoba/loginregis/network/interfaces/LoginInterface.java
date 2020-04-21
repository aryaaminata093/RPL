package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<BaseResponse> login(
            @Field("email") String email,
            @Field("password") String password);

}
