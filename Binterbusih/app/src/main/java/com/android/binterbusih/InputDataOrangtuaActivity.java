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

public class InputDataOrangtuaActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;



    EditText etnamaayah;
    EditText etnamapertama;
    EditText etnamaakhir;
    EditText etnamalengkap;
    Button btntanggallahir;
    EditText etumur;
    Spinner spmasihhidupayah;
    EditText etsuku;
    EditText etpekerjaanayah;
    EditText etalamatlengkapayah;
    EditText etnotelp;
    EditText etnamaibu;
    Spinner spmasihhidupibu;
    EditText etpekerjaanibu;
    EditText etalamatlengkapibu;
    Button btnsimpan;

    String strtanggallahir;
    String strmasihhidupayah;
    String strmasihhidupibu;

    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar c;
    SimpleDateFormat df2 ;
    SimpleDateFormat df ;
    String strtgllahir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_orangtua);


        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        etnamaayah=(EditText) findViewById(R.id.etnamaayah);

        btntanggallahir=(Button) findViewById(R.id.btntanggallahir);
        etumur=(EditText) findViewById(R.id.etumur);

        spmasihhidupayah=(Spinner) findViewById(R.id.spmasihhidupayah);
        etsuku=(EditText) findViewById(R.id.etsuku);
        etpekerjaanayah=(EditText) findViewById(R.id.etpekerjaanayah);
        etalamatlengkapayah=(EditText) findViewById(R.id.etalamatlengkapayah);
        etnotelp=(EditText) findViewById(R.id.etnotelp);
        etnamaibu=(EditText) findViewById(R.id.etnamaibu);
        spmasihhidupibu=(Spinner) findViewById(R.id.spmasihhidupibu);
        etpekerjaanibu=(EditText) findViewById(R.id.etpekerjaanibu);
        etalamatlengkapibu=(EditText) findViewById(R.id.etalamatlengkapibu);

        btnsimpan=(Button) findViewById(R.id.btnsimpan);

        c = Calendar.getInstance();
        df2 = new SimpleDateFormat("yyyy-MM-dd");
        strtanggallahir=df2.format(c.getTime());


        spmasihhidupayah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strmasihhidupayah=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spmasihhidupibu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strmasihhidupibu=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btntanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InputDataOrangtuaActivity.this,
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
                                strtanggallahir= year + "-" + strbulan + "-" + strtanggal;
                                btntanggallahir.setText(strtanggallahir);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpanorangtua();
            }
        });
    }


    private void simpanorangtua(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpandataorangtua(

                stremail.toString(),
                strusername.toString(),
                etnamaayah.getText().toString(),
                strtanggallahir.toString(),
                etumur.getText().toString(),
                strmasihhidupayah.toString(),
                etsuku.getText().toString(),
                etpekerjaanayah.getText().toString(),
                etalamatlengkapayah.getText().toString(),
                etnotelp.getText().toString(),
                etnamaibu.getText().toString(),
                strmasihhidupibu.toString(),
                etpekerjaanibu.getText().toString(),
                etalamatlengkapibu.getText().toString(),


//                stremail.toString(),
//                strusername.toString(),
//                etnamaayah.getText().toString(),
//                strtanggallahir.toString(),
//                etumur.getText().toString(),
//                strmasihhidupayah.toString(),
//                etsuku.getText().toString(),
//                "coba",
//                "coba",
//                "coba",
//                "coba",
//                "coba",
//                "coba",
//                "coba",

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

                        Toast.makeText(getApplicationContext(),"masuk", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(getApplicationContext(), LoginUserActivity.class);
                        startActivity(intent);
                        finish();




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