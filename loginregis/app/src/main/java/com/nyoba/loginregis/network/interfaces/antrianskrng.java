package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.HomeModel;
import com.nyoba.loginregis.model.ModelJanji;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface antrianskrng {
    @FormUrlEncoded
    @POST("lihatantrianskrng.php")
    Call<HomeModel> lihatno(
            @Field("idjadwal") String idjadwal,
            @Field("tanggal") String tanggal);
}
