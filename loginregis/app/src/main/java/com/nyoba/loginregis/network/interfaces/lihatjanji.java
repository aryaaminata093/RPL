package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.DokterModel;
import com.nyoba.loginregis.model.ModelJanji;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface lihatjanji {
    @FormUrlEncoded
    @POST("lihatjanji.php")
    Call<ModelJanji> lihat(
            @Field("pasid") String pid);
}
