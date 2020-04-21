package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.BaseResponse;
import com.nyoba.loginregis.network.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrasiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<BaseResponse> registrasi(
            @Field("email") String email,
            @Field("nama") String nama,
            @Field("password") String password);

}
