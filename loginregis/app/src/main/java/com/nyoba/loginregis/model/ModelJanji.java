package com.nyoba.loginregis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelJanji {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id_doc")
    @Expose
    private String idDoc;
    @SerializedName("namadokter")
    @Expose
    private String namadokter;
    @SerializedName("jam")
    @Expose
    private String jam;
    @SerializedName("spesialis")
    @Expose
    private String spesialis;
    @SerializedName("noantrian")
    @Expose
    private String noantrian;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getNamadokter() {
        return namadokter;
    }

    public void setNamadokter(String namadokter) {
        this.namadokter = namadokter;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getNoantrian() {
        return noantrian;
    }

    public void setNoantrian(String noantrian) {
        this.noantrian = noantrian;
    }

}
