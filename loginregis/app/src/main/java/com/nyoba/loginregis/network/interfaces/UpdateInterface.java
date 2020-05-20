package com.nyoba.loginregis.network.interfaces;

import com.nyoba.loginregis.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UpdateInterface {
    @FormUrlEncoded
    @POST("update.php")
    Call<BaseResponse> update(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("tempatlahir") String tempatlahir,
            @Field("tanggallahir") String tanggallahir,
            @Field("jeniskelamin") String jeniskelamin,
            @Field("golongandarah") String golongandarah
    );
}
