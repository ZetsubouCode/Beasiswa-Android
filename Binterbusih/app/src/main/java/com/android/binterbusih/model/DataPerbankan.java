package com.android.binterbusih.model;


public class DataPerbankan {
    private String email;
    private String username;
    private String norekening;
    private String cabang;
    private String atasnama;



    public void setemail(String email){this.email = email;}
    public String getemail() {return email;}

    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }


    public void setnorekening(String norekening) {
        this.norekening = norekening;
    }
    public String getnorekening() {
        return norekening;
    }

    public void settcabang(String cabang) {
        this.cabang = cabang;
    }
    public String getcabang() {
        return cabang;
    }


    public void setatasnama(String atasnama) {
        this.atasnama = atasnama;
    }
    public String getatasnama() {
        return atasnama;
    }


}
