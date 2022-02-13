package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InputDataPerbankanActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;


    EditText etnorekening;
    EditText etcabang;
    Spinner spcabang;
    EditText etatasnama;
    Button btnsimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_perbankan);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");


        etnorekening=(EditText) findViewById(R.id.etnorekening);
        etcabang=(EditText) findViewById(R.id.etcabang);
        spcabang=(Spinner) findViewById(R.id.spcabang);
        etatasnama=(EditText) findViewById(R.id.etatasnama);

        spcabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strpilihancabang;
                strpilihancabang=parent.getItemAtPosition(position).toString();

                if (strpilihancabang.equals("Lainnya")) {
                    etcabang.setText("");
                } else {
                    etcabang.setText(strpilihancabang);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnsimpan=(Button) findViewById(R.id.btnsimpan);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpandataperbankan();
            }
        });



    }


    private void simpandataperbankan(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpandataperbankan(

                stremail.toString(),
                strusername.toString(),
                etnorekening.getText().toString(),
                etcabang.getText().toString(),
                etatasnama.getText().toString(),


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



                        if (strtipemember.equals("Pelajar")){
                            Intent intent = new Intent(getApplicationContext(), InputPelajarActivity.class);
                            intent.putExtra("kirimemail", stremail);
                            intent.putExtra("kirimusername", strusername);
                            intent.putExtra("kirimtipemember", strtipemember);
                            startActivity(intent);
                            finish();
                        } else  if (strtipemember.equals("Mahasiswa")){
                            Intent intent = new Intent(getApplicationContext(), InputLembagastudiActivity.class);
                            intent.putExtra("kirimemail", stremail);
                            intent.putExtra("kirimusername", strusername);
                            intent.putExtra("kirimtipemember", strtipemember);
                            startActivity(intent);
                            finish();
                        }


                        Toast.makeText(getApplicationContext(), "Simpan Perbankan Berhasil ", Toast.LENGTH_LONG).show();

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

    @Override
    public void onBackPressed() {




    }
}