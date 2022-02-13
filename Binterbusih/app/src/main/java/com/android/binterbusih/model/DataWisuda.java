package com.android.binterbusih.model;


public class DataWisuda {
    private String email;
    private String username;
    private String perguruantinggi;
    private String programstudi;
    private String jenjangpendidikan;
    private String tanggalselesai;
    private String ipk;
    private String urlijazah;

    public void setemail(String email){this.email = email;}
    public String getemail() {return email;}

    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }

    public void setperguruantinggi(String perguruantinggi) {
        this.perguruantinggi = perguruantinggi;
    }
    public String getperguruantinggi() {
        return perguruantinggi;
    }

    public void settanggalselesai(String tanggalselesai) {
        this.tanggalselesai = tanggalselesai;
    }
    public String gettanggalselesai() {
        return tanggalselesai;
    }

    public void setjenjangpendidikan(String jenjangpendidikan) {
        this.jenjangpendidikan = jenjangpendidikan;
    }
    public String getjenjangpendidikan() {
        return jenjangpendidikan;
    }

    public void setipk(String ipk) {
        this.ipk = ipk;
    }
    public String getipk() {
        return ipk;
    }

    public void seturlijazah(String urlijazah) {
        this.urlijazah = urlijazah;
    }
    public String geturlijazah() {
        return urlijazah;
    }

}
