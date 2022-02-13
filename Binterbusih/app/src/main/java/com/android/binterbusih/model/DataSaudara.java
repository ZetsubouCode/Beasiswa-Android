package com.android.binterbusih.model;



public class DataSaudara {
    private String email;
    private String username;
    private String anakke;
    private String nama;
    private String umur;
    private String alamat;
    private String keterangan;

    public void setemail(String email){this.email = email;}
    public String getemail() {return email;}

    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }

    public void setanakke(String anakke) {
        this.anakke = anakke;
    }
    public String getanakke() {
        return anakke;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }
    public String getnama() {
        return nama;
    }

    public void setumur(String umur) {
        this.umur = umur;
    }
    public String getumur() {
        return umur;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }
    public String getalamat() {
        return alamat;
    }

    public void setketerangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public String getketerangan() {
        return keterangan;
    }


}
