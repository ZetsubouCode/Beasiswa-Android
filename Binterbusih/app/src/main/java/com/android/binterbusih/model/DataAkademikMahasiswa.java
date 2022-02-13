package com.android.binterbusih.model;


public class DataAkademikMahasiswa {
    private String email;
    private String username;
    private String perguruantinggi;
    private String programstudi;
    private String semester;
    private String tahunajaran;
    private String ipk;
    private String urltranskrip;

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

    public void setprogramstudi(String programstudi) {
        this.programstudi = programstudi;
    }
    public String getprogramstudi() {
        return programstudi;
    }


    public void setsemester(String semester) {
        this.semester = semester;
    }
    public String getsemester() {
        return semester;
    }

    public void settahunajaran(String tahunajaran) {
        this.tahunajaran = tahunajaran;
    }
    public String gettahunajaran() {
        return tahunajaran;
    }

    public void setipk(String ipk) {
        this.ipk = ipk;
    }
    public String getipk() {
        return ipk;
    }

    public void seturltranskrip(String urltranskrip) {
        this.urltranskrip = urltranskrip;
    }
    public String geturltranskrip() {
        return urltranskrip;
    }






}
