package com.android.binterbusih;


import com.android.binterbusih.model.CDataAkademikMahasiswa;
import com.android.binterbusih.model.CDataAkademikSiswa;
import com.android.binterbusih.model.CDataPribadi;
import com.android.binterbusih.model.CDataSaudara;
import com.android.binterbusih.model.CDataUser;
import com.android.binterbusih.model.DataAkademikMahasiswa;
import com.android.binterbusih.model.DataAkademikSiswa;
import com.android.binterbusih.model.DataLembagastudi;
import com.android.binterbusih.model.DataOrangtua;
import com.android.binterbusih.model.DataPelajar;
import com.android.binterbusih.model.DataPerbankan;
import com.android.binterbusih.model.DataPribadi;
import com.android.binterbusih.model.DataUser;
import com.android.binterbusih.model.DataWisuda;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface ModulAPI {


    @GET("/probinterbusih/loginuser.php")
    public void apiloginuser(
            @Query("vsemail") String vsemail,
            @Query("vspassword") String vspassword,
            Callback<DataUser> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/setFCMToken.php")
    public void  apisetFCM(
            @Field("vsemail") String vsemail,
            @Field("vstoken") String vstoken,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/FCMSendMessage.php")
    public void  apisendMessage(
            @Field("title") String title,
            @Field("message") String message,
            @Field("token") String token,
            Callback<Response> callback);

    @GET("/probinterbusih/cekuserada.php")
    public void apicekuserada(
            @Query("vsemail") String vsemail,
            Callback<DataUser> callback);

    @GET("/probinterbusih/ceksudahdaftar.php")
    public void apiceksudahdaftar(
            @Query("vsemail") String vsemail,
            Callback<DataUser> callback);

    @GET("/probinterbusih/ambildatapribadilengkap.php")
    public void apiambildatapribadilengkap(
            @Query("vsemail") String vsemail,
            Callback<DataPribadi> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/registeruser.php")
    public void  apiregisteruser(
            @Field("vsemail") String vsemail,
            @Field("vspassword") String vspassword,
            @Field("vsusername") String vsusername,
            @Field("vsnohp") String vsnohp,
            @Field("vstipemember") String vstipemember,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/updateFotoProfil.php")
    public void  apiupdateprofil(
            @Field("vsemail") String vsemail,
            @Field("vsurlphoto") String vsurlphoto,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpanwisuda.php")
    public void  apisimpanwisuda(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vsperguruantinggi") String vsperguruantinggi,
            @Field("vsprogramstudi") String vsprogramstudi,
            @Field("vsjenjangpendidikan") String vsjenjangpendidikan,
            @Field("vstanggalselesai") String vstanggalselesai,
            @Field("vsipk") String vsipk,
            @Field("vsurlijazah") String vsurlijazah,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpandatasaudara.php")
    public void  apisimpandatasaudara(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vsanakke") String vsanakke,
            @Field("vsnama") String vsnama,
            @Field("vsumur") String vsumur,
            @Field("vsalamat") String vsalamat,
            @Field("vsketerangan") String vsketerangan,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpandataperbankan.php")
    public void  apisimpandataperbankan(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vsnorekening") String vsnorekening,
            @Field("vscabang") String vscabang,
            @Field("vsatasnama") String vsatasnama,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/probinterbusih/simpandatapribadi.php")
    public void  apisimpandatapribadi(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vsnamakeluarga") String vsnamakeluarga,
            @Field("vsnamadepan") String vsnamadepan,
            @Field("vsnotelp") String vsnotelp,

            @Field("vsnoktp") String vsnoktp,
            @Field("vstempatlahir") String vstempatlahir,
            @Field("vstanggallahir") String vstanggallahir,
            @Field("vsjeniskelamin") String vsjeniskelamin,
            @Field("vsalamatlengkap") String vsalamatlengkap,
            @Field("vsurlphoto") String vsurlphoto,
            @Field("vsalamattetap") String vsalamattetap,
            @Field("vsalamatsementara") String vsalamatsementara,
            @Field("vsareaasal") String vsareaasal,
            @Field("vsagama") String vsagama,
            @Field("vssuku") String vssuku,
            @Field("vswilayahadat") String vswilayahadat,
            @Field("vstipemember") String vstipemember,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpandatapelajar.php")
    public void  apisimpandatapelajar(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vssekolah") String vssekolah,
            @Field("vsjurusan") String vsjurusan,
            @Field("vsnilairatarataraporsmp") String vsnilairatarataraporsmp,
            @Field("vsnilaisttbsmp") String vsnilaisttbsmp,
            @Field("vsnilaiujianakhirsekolah") String vsnilaiujianakhirsekolah,
            @Field("vsbahasaindonesia") String vsbahasaindonesia,
            @Field("vsbahasainggris") String vsbahasainggris,
            @Field("vsmatematika") String vsmatematika,
            @Field("vsipa") String vsipa,
            @Field("vsips") String vsips,
            @Field("vsfisika") String vsfisika,
            @Field("vskimia") String vskimia,
            @Field("vsbiologi") String vsbiologi,
            @Field("vsgeografi") String vsgeografi,
            @Field("vsekonomi") String vsekonomi,
            @Field("vssosiologi") String vssosiologi,
            @Field("vsteknologiinformasi") String vsteknologiinformasi,
            @Field("vsektrakulikuler1") String vsektrakulikuler1,
            @Field("vsektrakulikuler2") String vsektrakulikuler2,
            @Field("vsektrakulikuler3") String vsektrakulikuler3,
            @Field("vsektrakulikuler4") String vsektrakulikuler4,
            @Field("vsektrakulikuler5") String vsektrakulikuler5,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/probinterbusih/simpandataorangtua.php")
    public void  apisimpandataorangtua(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vsnamaayah") String vsnamaayah,
            @Field("vstanggallahir") String vstanggallahir,
            @Field("vsumur") String vsumur,
            @Field("vsmasihhidupayah") String vsmasihhidupayah,
            @Field("vssuku") String vssuku,
            @Field("vspekerjaanayah") String vspekerjaanayah,
            @Field("vsalamatlengkapayah") String vsalamatlengkapayah,
            @Field("vsnotelp") String vsnotelp,
            @Field("vsnamaibu") String vsnamaibu,
            @Field("vsmasihhidupibu") String vsmasihhidupibu,
            @Field("vspekerjaanibu") String vspekerjaanibu,
            @Field("vsalamatlengkapibu") String vsalamatlengkapibu,
            Callback<Response> callback);





    @FormUrlEncoded
    @POST("/probinterbusih/simpandatalembagastudi.php")
    public void  apisimpandatalembagastudi(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vsnamalembagastudi") String vsnamalembagastudi,
            @Field("vsstatusakreditasi") String vsstatusakreditasi,
            @Field("vsjenis") String vsjenis,
            @Field("vsnamafakultas") String vsnamafakultas,
            @Field("vsnamajurusan") String vsnamajurusan,
            @Field("vsjenjangpendidikan") String vsjenjangpendidikan,
            @Field("vsprestasikumulatif") String vsprestasikumulatif,
            @Field("vsprestasisemester") String vsprestasisemester,
            @Field("vstamggalmasuk") String vstamggalmasuk,
            @Field("vstanggallulusdiharapkan") String vstanggallulusdiharapkan,
            @Field("vsjenjangstudi") String vsjenjangstudi,
            @Field("vsalamatlembaga") String vsalamatlembaga,
            @Field("vskabupatenkota") String vskabupatenkota,
            @Field("vstelepon") String vstelepon,
            @Field("vsrekeninglembaga") String vsrekeninglembaga,
            @Field("vsbank") String vsbank,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/probinterbusih/simpandataakademiksiswa.php")
    public void  apisimpandataakademiksiswa(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vssekolah") String vssekolah,
            @Field("vsjurusan") String vsjurusan,
            @Field("vssemester") String vssemester,
            @Field("vstahunajaran") String vstahunajaran,
            @Field("vsnilairatarata") String vsnilairatarata,
            @Field("vsurlraport") String vsurlraport,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpandataakademikmahasiswa.php")
    public void  apisimpandataakademikmahasiswa(
            @Field("vsemail") String vsemail,
            @Field("vsusername") String vsusername,
            @Field("vspergururantinggi") String vspergururantinggi,
            @Field("vsprogramstudi") String vsprogramstudi,
            @Field("vssemester") String vssemester,
            @Field("vstahunajaran") String vstahunajaran,
            @Field("vsipk") String vsipk,
            @Field("vsurltranskrip") String vsurltranskrip,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpanterimabeasiswa.php")
    public void  apissimpanterimabeasiswa(
            @Field("vsemail") String vsemail,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/probinterbusih/simpantolakbeasiswa.php")
    public void  apisimpantolakbeasiswa(
            @Field("vsemail") String vsemail,
            Callback<Response> callback);

    @GET("/probinterbusih/ambildatasaudara.php")
    public void apiambildatausaudara(
            @Query("vsemail") String vsemail,
            Callback<CDataSaudara> callback);

    @GET("/probinterbusih/ambilsemuauser.php")
    public void apiambilsemuauser(
            Callback<CDataUser> callback);

    @GET("/probinterbusih/ambildataakademikmahassiswa.php")
    public void apiambildataakademikmahassiswa(
            @Query("vsemail") String vsemail,
            Callback<CDataAkademikMahasiswa> callback);

    @GET("/probinterbusih/ambildataakademikssiswa.php")
    public void apidataakademikssiswa(
            @Query("vsemail") String vsemail,
            Callback<CDataAkademikSiswa> callback);

    @GET("/probinterbusih/ambildataorangtua.php")
    public void apiambildataorangtua(
            @Query("vsemail") String vsemail,
            Callback<DataOrangtua> callback);

    @GET("/probinterbusih/ambildatalembagastudi.php")
    public void apiambildatalembagastudi(
            @Query("vsemail") String vsemail,
            Callback<DataLembagastudi> callback);

    @GET("/probinterbusih/ambildataperbankan.php")
    public void apiambildataperbankan(
            @Query("vsemail") String vsemail,
            Callback<DataPerbankan> callback);

    @GET("/probinterbusih/ambildatapelajar.php")
    public void apiambildatapelajar(
            @Query("vsemail") String vsemail,
            Callback<DataPelajar> callback);

    @GET("/probinterbusih/ambildatapribadi.php")
    public void apiambildatapribadi(
            Callback<CDataPribadi> callback);

    @GET("/probinterbusih/ambildatapribadicari.php")
    public void apiambildatapribadicari(
            @Query("vscari") String vscari,
            Callback<CDataPribadi> callback);

    @GET("/probinterbusih/ambildatawisuda.php")
    public void apidatawisuda(
            @Query("vsemail") String vsemail,
            Callback<DataWisuda> callback);
//
//    @GET("/prowarungkiko/ceksudahadatoko.php")
//    public void apiceksudahadatoko(
//            @Query("vsemail") String vsemail,
//            Callback<DataToko> callback);
//
//    @GET("/prowarungkiko/cekadanotifikasibaru.php")
//    public void apicekadanotifikasibaru(
//            @Query("vsemail") String vsemail,
//            Callback<DataNotifikasi> callback);
//
//
//
//    @GET("/prowarungkiko/ambildatakeranjangdetail.php")
//    public void apiambildatakeranjangdetail(
//            @Query("vskeranjangid") String vskeranjangid,
//            Callback<CDataKeranjangDetail> callback);
//



}