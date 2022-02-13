package com.android.binterbusih.model;

public class DataUser {
    private int id;
    private String email;
    private String password;
    private String username;
    private String nohp;
    private String tipemember;
    private String statusproses;
    private String fcm_token;

    public void setid(int id){this.id = id;}
    public int getid() {return id;}

    public void setemail(String email){this.email = email;}
    public String getemail() {return email;}

    public void setpassword(String password) {
        this.password = password;
    }
    public String getpassword() {
        return password;
    }

    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }

    public void setnohp(String nohp) {
        this.nohp = nohp;
    }
    public String getnohp() {
        return nohp;
    }

    public void settipemember(String tipemember) {
        this.tipemember = tipemember;
    }
    public String gettipemember() {
        return tipemember;
    }

    public void setstatusproses(String statusproses) {
        this.statusproses = statusproses;
    }
    public String getstatusproses() {
        return statusproses;
    }
}
