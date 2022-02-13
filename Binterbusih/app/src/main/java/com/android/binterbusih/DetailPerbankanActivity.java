package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.model.DataPelajar;
import com.android.binterbusih.model.DataPerbankan;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailPerbankanActivity extends AppCompatActivity {

    TextView tvpelajarmahasiswa;
    TextView tvdata;


    String stremail;
    String strusername;
    String strtipemember;
    String strdari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perbankan);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");
        strdari= i.getStringExtra("kirimdari");

        tvdata=(TextView) findViewById(R.id.tvdata);

        subambildataperbankan();
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

    private void subambildataperbankan() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildataperbankan(
                stremail.toString(),

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataPerbankan>() {
                    @Override
                    public void success(DataPerbankan dataperbankan, Response response) {

                        strusername = dataperbankan.getusername();

                        tvdata.setText(
                                "Email : " + dataperbankan.getemail() + "\n" +
                                        "Nama Siswa : " + dataperbankan.getusername() + "\n" +
                                        "No Rekening : " + dataperbankan.getnorekening() + "\n" +
                                        "Cabang : " + dataperbankan.getcabang() + "\n" +
                                        "Atas Nama : " + dataperbankan.getatasnama() + "");






                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(DetailPerbankanActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }
}