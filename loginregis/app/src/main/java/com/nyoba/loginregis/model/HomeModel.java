package com.nyoba.loginregis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModel {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("noantrian")
    @Expose
    private String noantrian;
    @SerializedName("jadwalid")
    @Expose
    private String jadwalid;

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

    public String getNoantrian() {
        return noantrian;
    }

    public void setNoantrian(String noantrian) {
        this.noantrian = noantrian;
    }

    public String getJadwalid() {
        return jadwalid;
    }

    public void setJadwalid(String jadwalid) {
        this.jadwalid = jadwalid;
    }
}
