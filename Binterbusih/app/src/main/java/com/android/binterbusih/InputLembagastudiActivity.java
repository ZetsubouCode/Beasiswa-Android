package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.binterbusih.model.DataOrangtua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;

public class InputLembagastudiActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;



    EditText etnamalembagastudi;
    EditText etstatusakreditasi;
    Spinner spjenis;
    EditText etnamafakultas;
    EditText etnamajurusan;
    Spinner spjenjangpendidikan;
    EditText etprestasikumulatif;
    EditText etprestasisemester;
    EditText ettanggalmasuk;
    EditText ettanggallulusdiharapkan;
    EditText etjenjangpendidikan;
    EditText etalamatlembaga;
    EditText etkabupatenkota;
    EditText ettelepon;
    EditText etrekeninglembaga;
    EditText etbank;
    Spinner spbank;

    Button btntglmasuk;
    Button btntgllulus;

    String strjenis;
    String strjenjangpenidikan;

    Button btnsimpan;

    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar c;
    SimpleDateFormat df2 ;
    SimpleDateFormat df ;
    String strtglmasuk;
    String strtgllulus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_lembagastudi);
        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        etnamalembagastudi=(EditText) findViewById(R.id.etnamalembagastudi);
        etstatusakreditasi=(EditText) findViewById(R.id.etstatusakreditasi);
        spjenis=(Spinner) findViewById(R.id.spjenis);
        etnamafakultas=(EditText) findViewById(R.id.etnamafakultas);
        etnamajurusan=(EditText) findViewById(R.id.etnamajurusan);
        etjenjangpendidikan=(EditText) findViewById(R.id.etjenjangpendidikan);
        spjenjangpendidikan=(Spinner) findViewById(R.id.spjenjangpendidikan);
        etprestasikumulatif=(EditText) findViewById(R.id.etprestasikumulatif);
        etprestasisemester=(EditText) findViewById(R.id.etprestasisemester);

        etalamatlembaga=(EditText) findViewById(R.id.etalamatlembaga);
        etkabupatenkota=(EditText) findViewById(R.id.etkabupatenkota);
        ettelepon=(EditText) findViewById(R.id.ettelepon);
        etrekeninglembaga=(EditText) findViewById(R.id.etrekeninglembaga);
        etbank=(EditText) findViewById(R.id.etbank);
        spbank=(Spinner) findViewById(R.id.spbank);

        btnsimpan=(Button) findViewById(R.id.btnsimpan);
        btntgllulus=(Button) findViewById(R.id.btntgllulus);
        btntglmasuk=(Button) findViewById(R.id.btntglmasuk);

        c = Calendar.getInstance();
        df2 = new SimpleDateFormat("yyyy-MM-dd");
        strtglmasuk=df2.format(c.getTime());
        btntglmasuk.setText(strtglmasuk);


        c = Calendar.getInstance();
        df2 = new SimpleDateFormat("yyyy-MM-dd");
        strtgllulus=df2.format(c.getTime());
        btntgllulus.setText(strtgllulus);


        btntglmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InputLembagastudiActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String strbulan;
                                String strtanggal;

                                if (monthOfYear<9){
                                    strbulan= "0" + String.valueOf(monthOfYear+1);
                                }
                                else {
                                    strbulan=  String.valueOf(monthOfYear+1);
                                }

                                if (dayOfMonth<10){
                                    strtanggal= "0" + String.valueOf(dayOfMonth);
                                }
                                else {
                                    strtanggal=  String.valueOf(dayOfMonth);
                                }
                                strtglmasuk= year + "-" + strbulan + "-" + strtanggal;
                                btntglmasuk.setText(strtglmasuk);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        btntgllulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InputLembagastudiActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String strbulan;
                                String strtanggal;

                                if (monthOfYear<9){
                                    strbulan= "0" + String.valueOf(monthOfYear+1);
                                }
                                else {
                                    strbulan=  String.valueOf(monthOfYear+1);
                                }

                                if (dayOfMonth<10){
                                    strtanggal= "0" + String.valueOf(dayOfMonth);
                                }
                                else {
                                    strtanggal=  String.valueOf(dayOfMonth);
                                }
                                strtgllulus= year + "-" + strbulan + "-" + strtanggal;
                                btntgllulus.setText(strtgllulus);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        spjenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strjenis=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spjenjangpendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strjenjangpenidikan=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanlembagastudi();
            }
        });


        spbank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strpilihancabang;
                strpilihancabang=parent.getItemAtPosition(position).toString();

                if (strpilihancabang.equals("Lainnya")) {
                    etbank.setText("");
                } else {
                    etbank.setText(strpilihancabang);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void simpanlembagastudi(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpandatalembagastudi(

                stremail.toString(),
                strusername.toString(),
                etnamalembagastudi.getText().toString(),
                etalamatlembaga.getText().toString(),

                etstatusakreditasi.getText().toString(),
                strjenis.toString(),
                etnamafakultas.getText().toString(),
                etnamajurusan.getText().toString(),
                etprestasikumulatif.getText().toString(),
                etprestasisemester.getText().toString(),
                strtglmasuk.toString(),
                strtgllulus.toString(),
                strjenjangpenidikan.toString(),
                etalamatlembaga.getText().toString(),
                etkabupatenkota.getText().toString(),
                ettelepon.getText().toString(),
                etrekeninglembaga.getText().toString(),
                etbank.getText().toString(),




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




                        Toast.makeText(getApplicationContext(), "Simpan Lembaga Studi Berhasil ", Toast.LENGTH_LONG).show();

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

        super.onBackPressed();


    }
}