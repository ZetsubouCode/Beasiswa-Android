package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.model.DataLembagastudi;
import com.android.binterbusih.model.DataPelajar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailLembagaStudiActivity extends AppCompatActivity {

    TextView tvpelajarmahasiswa;
    TextView tvdata;


    String stremail;
    String strusername;
    String strtipemember;
    String strdari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lembaga_studi);


        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");
        strdari= i.getStringExtra("kirimdari");


        tvdata=(TextView) findViewById(R.id.tvdata);

        subambildatalembagastudi();
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

    private void subambildatalembagastudi() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatalembagastudi(
                stremail.toString(),

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataLembagastudi>() {
                    @Override
                    public void success(DataLembagastudi datalembagastudi, Response response) {

                        strusername = datalembagastudi.getusername();

                        tvdata.setText(
                                "Email : " + datalembagastudi.getemail() + "\n" +
                                        "Nama Siswa : " + datalembagastudi.getusername() + "\n" +
                                        "Nama Lembaga Studi : " + datalembagastudi.getnamalembagastudi() + "\n" +
                                        "Status Akreditasi : " + datalembagastudi.getstatusakreditasi() + "\n" +
                                        "Jenis : " + datalembagastudi.getjenis() + "\n" +
                                        "Nama Fakultas : " + datalembagastudi.getnamafakultas() + "\n" +
                                        "Nama Jurusan : " + datalembagastudi.getnamajurusan() + "\n" +
                                        "Prestasi Kumulatif : " + datalembagastudi.getprestasikumulatif() + "\n" +
                                        "Prestasi Semeester : " + datalembagastudi.getprestasisemester() + "\n" +
                                        "Tanggal Masuk : " + datalembagastudi.gettanggalmasuk() + "\n" +
                                        "Tanggal Lulus Diharapkan : " + datalembagastudi.gettanggallulusdiharapkan() + "\n" +
                                        "Jenjang Studi : " + datalembagastudi.getjenjangstudi() + "\n" +
                                        "Alamat Lembaga : " + datalembagastudi.getalamatlembaga() + "\n" +
                                        "Kabupaten Kota : " + datalembagastudi.getkabupatenkota() + "\n" +
                                        "Telepon : " + datalembagastudi.gettelepon() + "\n" +
                                        "Rekening Lembaga : " + datalembagastudi.getrekeninglembaga() + "\n" +
                                        "Bank : " + datalembagastudi.getbank() + "");


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(DetailLembagaStudiActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }
}