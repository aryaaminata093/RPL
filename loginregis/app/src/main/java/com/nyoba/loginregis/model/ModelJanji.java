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
    @SerializedName("id_pas")
    @Expose
    private String idPas;
    @SerializedName("namapasien")
    @Expose
    private String namapasien;
    @SerializedName("id_doc")
    @Expose
    private String idDoc;
    @SerializedName("namadokter")
    @Expose
    private String namadokter;
    @SerializedName("harijanji")
    @Expose
    private String harijanji;
    @SerializedName("spesialis")
    @Expose
    private String spesialis;

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

    public String getIdPas() {
        return idPas;
    }

    public void setIdPas(String idPas) {
        this.idPas = idPas;
    }

    public String getNamapasien() {
        return namapasien;
    }

    public void setNamapasien(String namapasien) {
        this.namapasien = namapasien;
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

    public String getHarijanji() {
        return harijanji;
    }

    public void setHarijanji(String harijanji) {
        this.harijanji = harijanji;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }
}
