package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.DokterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CariInterface {
    @FormUrlEncoded
    @POST("cari.php")
    Call<List<DokterModel>> getJson(
            @Field("cari") String cari);
}
