package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;

public class InputPelajarActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;

    EditText etsekolah;
    EditText etjurusan;
    EditText etnilairatarataraporsmp;
    EditText etnilaisttbsmp;
    EditText etnilaiujianakhirsekolah;
    EditText etbahasaindonesia;
    EditText etbahasainggris;
    EditText etmatematika;
    EditText etipa;
    EditText etips;
    EditText etfisika;
    EditText etkimia;
    EditText etbiologi;
    EditText etgeografi;
    EditText etekonomi;
    EditText etsosiologi;
    EditText etteknologiinformasi;
    EditText etektrakulikuler1;
    EditText etektrakulikuler2;
    EditText etektrakulikuler3;
    EditText etektrakulikuler4;
    EditText etektrakulikuler5;

    Button btnsimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pelajar);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");


        etsekolah=(EditText) findViewById(R.id.etsekolah);
        etjurusan=(EditText) findViewById(R.id.etjurusan);
        etnilairatarataraporsmp=(EditText) findViewById(R.id.etnilairatarataraporsmp);
        etnilaisttbsmp=(EditText) findViewById(R.id.etnilaisttbsmp);
        etnilaiujianakhirsekolah=(EditText) findViewById(R.id.etnilaiujianakhirsekolah);
        etbahasaindonesia=(EditText) findViewById(R.id.etbahasaindonesia);
        etbahasainggris=(EditText) findViewById(R.id.etbahasainggris);

        etmatematika=(EditText) findViewById(R.id.etmatematika);
        etipa=(EditText) findViewById(R.id.etipa);
        etips=(EditText) findViewById(R.id.etips);
        etfisika=(EditText) findViewById(R.id.etfisika);
        etkimia=(EditText) findViewById(R.id.etkimia);
        etbiologi=(EditText) findViewById(R.id.etbiologi);
        etgeografi=(EditText) findViewById(R.id.etgeografi);
        etekonomi=(EditText) findViewById(R.id.etekonomi);
        etsosiologi=(EditText) findViewById(R.id.etsosiologi);
        etteknologiinformasi=(EditText) findViewById(R.id.etteknologiinformasi);
        etektrakulikuler1=(EditText) findViewById(R.id.etektrakulikuler1);
        etektrakulikuler2=(EditText) findViewById(R.id.etektrakulikuler2);
        etektrakulikuler3=(EditText) findViewById(R.id.etektrakulikuler3);
        etektrakulikuler4=(EditText) findViewById(R.id.etektrakulikuler4);
        etektrakulikuler5=(EditText) findViewById(R.id.etektrakulikuler5);



        btnsimpan=(Button) findViewById(R.id.btnsimpan);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanpelajar();
            }
        });
    }

    private void simpanpelajar(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpandatapelajar(

                stremail.toString(),
                strusername.toString(),
                etsekolah.getText().toString(),
                etjurusan.getText().toString(),
                etnilairatarataraporsmp.getText().toString(),
                etnilaisttbsmp.getText().toString(),
                etnilaiujianakhirsekolah.getText().toString(),
                etbahasaindonesia.getText().toString(),
                etbahasainggris.getText().toString(),
                etmatematika.getText().toString(),
                etipa.getText().toString(),
                etips.getText().toString(),
                etfisika.getText().toString(),
                etkimia.getText().toString(),
                etbiologi.getText().toString(),
                etgeografi.getText().toString(),
                etekonomi.getText().toString(),
                etsosiologi.getText().toString(),
                etteknologiinformasi.getText().toString(),
                etektrakulikuler1.getText().toString(),
                etektrakulikuler2.getText().toString(),
                etektrakulikuler3.getText().toString(),
                etektrakulikuler4.getText().toString(),
                etektrakulikuler5.getText().toString(),



                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getApplicationContext(), InputDataOrangtuaActivity.class);
                        intent.putExtra("kirimemail", stremail);
                        intent.putExtra("kirimusername", strusername);
                        intent.putExtra("kirimtipemember", strtipemember);
                        startActivity(intent);
                        finish();




                        Toast.makeText(getApplicationContext(), "Simpan Pribadi Berhasil ", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();
//                        btnsimpan.setEnabled(true);

                        Toast.makeText(getApplicationContext(), "Kesalahan Koneksi Data reg" + merror.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}