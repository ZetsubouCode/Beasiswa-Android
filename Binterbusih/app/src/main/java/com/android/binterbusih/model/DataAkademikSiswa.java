package com.android.binterbusih.model;




public class DataAkademikSiswa {
    private String email;
    private String username;
    private String sekolah;
    private String jurusan;
    private String semester;
    private String tahunajaran;
    private String nilairatarata;
    private String urlraport;

    public void setemail(String email){this.email = email;}
    public String getemail() {return email;}

    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }

    public void setpsekolah(String sekolah) {
        this.sekolah = sekolah;
    }
    public String getsekolah() {
        return sekolah;
    }

    public void setjurusan(String jurusan) {
        this.jurusan = jurusan;
    }
    public String getjurusan() {
        return jurusan;
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

    public void setnilairatarata(String nilairatarata) {
        this.nilairatarata = nilairatarata;
    }
    public String getnilairatarata() {
        return nilairatarata;
    }

    public void seturlraport(String urlraport) {
        this.urlraport = urlraport;
    }
    public String geturlraport() {
        return urlraport;
    }

}
