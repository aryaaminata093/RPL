package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.HomeModel;
import com.nyoba.loginregis.model.ModelJanji;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface noantrian {
    @FormUrlEncoded
    @POST("lihatnoantrian.php")
    Call<HomeModel> lihatno(
            @Field("idpassien") String idpassien,
            @Field("tanggal") String tanggal);
}
