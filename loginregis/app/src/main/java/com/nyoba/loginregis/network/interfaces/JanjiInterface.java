package com.nyoba.loginregis.network.interfaces;
import com.nyoba.loginregis.model.BaseResponse;
import com.nyoba.loginregis.model.ModelJanji;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JanjiInterface {

    @FormUrlEncoded
    @POST("buatjanji.php")
    Call<ModelJanji> buatjanji(
            @Field("idpasien") String idpassien,
            @Field("iddokter") String iddokter,
            @Field("harijanji") String harijanji);

}