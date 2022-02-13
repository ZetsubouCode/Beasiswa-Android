package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.model.DataPribadi;
import com.android.binterbusih.model.DataUser;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CekHasilActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;

    TextView tvemail;
    TextView tvusername;
    TextView tvbeasiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_hasil);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        tvemail=(TextView) findViewById(R.id.tvemail);
        tvusername=(TextView) findViewById(R.id.tvusername);
        tvbeasiswa=(TextView) findViewById(R.id.tvbeasiswa);

        tvemail.setText(stremail);
        tvusername.setText(strusername);
        subambilbeasiswa();
    }


    @Override
    public void onBackPressed() {

        if (strtipemember.equals("Pelajar")) {

            Intent intent = new Intent(CekHasilActivity.this, UtamaSiswaActivity.class);
            intent.putExtra("kirimemail", stremail);
            intent.putExtra("kirimusername", strusername);
            intent.putExtra("kirimtipemember", strtipemember);
            startActivity(intent);
            finish();
        } else  if (strtipemember.equals("Mahasiswa")) {

            Intent intent = new Intent(CekHasilActivity.this, UtamaMahasiswaActivity.class);
            intent.putExtra("kirimemail", stremail);
            intent.putExtra("kirimusername", strusername);
            intent.putExtra("kirimtipemember", strtipemember);
            startActivity(intent);
            finish();
        }

    }


    private void subambilbeasiswa() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatapribadilengkap(
                stremail.toString(),
//                "coba@gmail.com",
//                "1234678",
                new Callback<DataPribadi>() {
                    @Override
                    public void success(DataPribadi datapribadi, Response response) {

                        String strstatus = datapribadi.getstatus();

                        if (strstatus.equals("")) {
                            tvbeasiswa.setText("BEASISWA BELUM DIPUTUSKAN");
                        } else {
                            tvbeasiswa.setText("BEASISWA : " + strstatus);
                        }

                    }



                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(CekHasilActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }
}