package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.model.DataPelajar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailDataPelajarMahasiswaActivity extends AppCompatActivity {

    TextView tvpelajarmahasiswa;
    TextView tvdata;

    String stremail;
    String strusername;
    String strtipemember;

    String strdari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_pelajar);


        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");
        strdari= i.getStringExtra("kirimdari");


        tvdata=(TextView) findViewById(R.id.tvdata);

        subambildatapelajar();

    }

    @Override
    public void onBackPressed() {

        if (strdari.equals("Request")) {
            Intent intent = new Intent(getApplicationContext(), UtamaAdminActivity.class);
            startActivity(intent);
            finish();
        } else if (strdari.equals("Cari")) {
            Intent intent = new Intent(getApplicationContext(), UtamaAdminCari.class);
            startActivity(intent);
            finish();
        }


    }

    private void subambildatapelajar() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatapelajar(
                stremail.toString(),

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataPelajar>() {
                    @Override
                    public void success(DataPelajar datapelajar, Response response) {

                        strusername = datapelajar.getusername();

                        tvdata.setText(
                                "Email " + datapelajar.getemail() + "\n" +
                                "Nama Siswa : " + datapelajar.getusername() + "\n" +
                                        "Nama Sekolah : " + datapelajar.getsekolah() + "\n" +
                                        "Jurusan : " + datapelajar.getjurusan() + "\n" +
                                "Nilai Rata Rata : " + datapelajar.getnilairatarataraporsmp() + "\n" +
                                "Nilai STTB SMP : " + datapelajar.getnilaisttbsmp() + "\n" +
                                "Nilai Ujian Akhir : " + datapelajar.getnilaiujianakhirsekolah() + "\n" +
                                "Bahasa Indonesia : " + datapelajar.getbahasaindonesia() + "\n" +
                                "Bahasa Inggris : " + datapelajar.getbahasainggris() + "\n" +
                                "Matematika : " + datapelajar.getmatematika() + "\n" +
                                "IPA : " + datapelajar.getipa() + "\n" +
                                "IPS : " + datapelajar.getips() + "\n" +
                                        "Fisika : " + datapelajar.getfisika() + "\n" +
                                        "Kimia : " + datapelajar.getkimia() + "\n" +
                                        "Biologi : " + datapelajar.getbiologi() + "\n" +
                                        "Geografi : " + datapelajar.getgeografi() + "\n" +
                                        "Ekonomi : " + datapelajar.getekonomi() + "\n" +
                                        "Sosiologi : " + datapelajar.getsosiologi() + "\n" +
                                        "Teknologi Informatika: " + datapelajar.getteknologiinformasi() + "\n" +
                                        "Ekstrakurikuler 1 : " + datapelajar.getektrakulikuler1() + "\n" +
                                        "Ekstrakurikuler 2 : " + datapelajar.getektrakulikuler2() + "\n" +
                                        "Ekstrakurikuler 3 : " + datapelajar.getektrakulikuler3() + "\n" +
                                        "Ekstrakurikuler 4 : " + datapelajar.getektrakulikuler4() + "\n" +
                                        "Ekstrakurikuler 5 :  " + datapelajar.getektrakulikuler5() + "");


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(DetailDataPelajarMahasiswaActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }
}