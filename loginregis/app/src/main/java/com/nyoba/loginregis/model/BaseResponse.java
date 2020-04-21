package com.nyoba.loginregis.model;

import java.util.Date;

public class BaseResponse {

    private int success;
    private String message;
    private int id;
    private String email;
    private String nama;
    private String tmptlhr;
    private Date tgllhr;
    private String alamat;
    private String jk;
    private String goldar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTmptlhr() {
        return tmptlhr;
    }

    public void setTmptlhr(String tmptlhr) {
        this.tmptlhr = tmptlhr;
    }

    public Date getTgllhr() {
        return tgllhr;
    }

    public void setTgllhr(Date tgllhr) {
        this.tgllhr = tgllhr;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getGoldar() {
        return goldar;
    }

    public void setGoldar(String goldar) {
        this.goldar = goldar;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
