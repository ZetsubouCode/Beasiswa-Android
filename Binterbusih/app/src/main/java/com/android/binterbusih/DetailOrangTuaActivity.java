package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.model.DataOrangtua;
import com.android.binterbusih.model.DataPelajar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailOrangTuaActivity extends AppCompatActivity {

    TextView tvpelajarmahasiswa;
    TextView tvdata;


    String stremail;
    String strusername;
    String strtipemember;
    String strdari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_orang_tua);


        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");
        strdari= i.getStringExtra("kirimdari");

        tvdata=(TextView) findViewById(R.id.tvdata);

        subambildataorangtua();
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

    private void subambildataorangtua() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildataorangtua(
                stremail.toString(),

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataOrangtua>() {
                    @Override
                    public void success(DataOrangtua dataorangtua, Response response) {

                        strusername = dataorangtua.getusername();

                        tvdata.setText(
                                "Email : " + dataorangtua.getemail() + "\n" +
                                        "Nama Siswa : " + dataorangtua.getusername() + "\n" +
                                        ": " + dataorangtua.getnamaayah() + "\n" +
                                        "Tanggal Lahir : " + dataorangtua.gettanggallahir() + "\n" +
                                        "Umur : " + dataorangtua.getumur() + "\n" +
                                        "masih Hidup : " + dataorangtua.getmasihhidupayah() + "\n" +
                                        "Suku : " + dataorangtua.getsuku() + "\n" +
                                        "Pekerjaan Ayah : " + dataorangtua.getpekerjaanayah() + "\n" +
                                        "Alamat Lengkap Ayah : " + dataorangtua.getalamatlengkapayah() + "\n" +
                                        "No Telp : " + dataorangtua.getnotelp() + "\n" +
                                        "Nama Ibu : " + dataorangtua.getnamaibu() + "\n" +
                                        "Masih Hidup : " + dataorangtua.getmasihhidupibu() + "\n" +
                                        "Pekerjaan Ibu : " + dataorangtua.getpekerjaanibu() + "\n" +
                                        "Alamat Lengkap Ibu : " + dataorangtua.getalamatlengkapibu() + "");





                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(DetailOrangTuaActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }
}