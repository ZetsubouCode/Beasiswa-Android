package com.android.binterbusih;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.android.binterbusih.ModulAPI;
import com.android.binterbusih.model.DataUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RegisterActivity extends AppCompatActivity {


    EditText etemail;
    EditText etpassword;
    EditText etusername;
    EditText etalamat;
    EditText etkota;
    EditText etnohp;
    Spinner sptipemember;


    ImageView image_ultah;
    Button btnsimpan;


    String stremail;
    String strpassword;
    String strusername;
     String strnohp;

     String strusernamecek;

    String strtipemember;


    Typeface facebold;
    Typeface facenormal;

    PowerManager.WakeLock wakeLock;
    Intent intentMyIntentService;

    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar c;
    SimpleDateFormat df2 ;

    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            final WindowInsetsController insetsController = getWindow().getInsetsController();
//            if (insetsController != null) {
//                insetsController.hide(WindowInsets.Type.statusBars());
//            }
//        } else {
//            getWindow().setFlags(
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN
//            );
//
//        }
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_register);


        auth = FirebaseAuth.getInstance();

        facebold= Typeface.createFromAsset(getAssets(),"fonts/Gotham-Bold.otf");
        facenormal= Typeface.createFromAsset(getAssets(),"fonts/Gotham-Medium.otf");


        etemail=(EditText) findViewById(R.id.etemail);
        etpassword=(EditText) findViewById(R.id.etpassword);
        etusername=(EditText) findViewById(R.id.etusername);
        etnohp=(EditText) findViewById(R.id.etnohp);

        btnsimpan=(Button) findViewById(R.id.btnsimpan);
        sptipemember=(Spinner) findViewById(R.id.sptipemember);

//        etemail.setText("coba@gmail.com");
//        etpassword.setText("12345678");
//        etusername.setText("coba");
//        etnohp.setText("11111");

        sptipemember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strtipemember=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnsimpan.setEnabled(false);

                stremail = etemail.getText().toString().trim();
                strpassword = etpassword.getText().toString().trim();
                strusername= etusername.getText().toString().trim();
                strnohp= etnohp.getText().toString().trim();




                if (stremail.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi Email ", Toast.LENGTH_LONG).show();
                    btnsimpan.setEnabled(true);
                    return;
                }

                if (strpassword.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi Password", Toast.LENGTH_LONG).show();
                    btnsimpan.setEnabled(true);
                    return;
                }

                if (strusername.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi Nama User ", Toast.LENGTH_LONG).show();
                    btnsimpan.setEnabled(true);
                    return;
                }


                if (strnohp.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi No HP", Toast.LENGTH_LONG).show();
                    btnsimpan.setEnabled(true);
                    return;
                }


                subceksudahdaftar();


            }
        });

    }


    @Override
    public void onBackPressed() {

            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), LoginUserActivity.class);
            startActivity(intent);
            finish();

    }


    private void subceksudahdaftar() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apicekuserada(
                stremail.toString(),

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataUser>() {
                    @Override
                    public void success(DataUser datauser, Response response) {

                        strusernamecek = datauser.getusername();


                        if (!(strusernamecek==null)) {
                            Toast.makeText(RegisterActivity.this, "User Sudah Terdaftar", Toast.LENGTH_LONG).show();

                        } else {


                            auth.createUserWithEmailAndPassword(stremail, strpassword)
                                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
//                                Toast.makeText(CabangActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                            // If sign in fails, display a message to the user. If sign in succeeds
                                            // the auth state listener will be notified and logic to handle the
                                            // signed in user can be handled in the listener.
                                            if (!task.isSuccessful()) {

                                                Toast.makeText(RegisterActivity.this, "Registration failed." + task.getException(),
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(RegisterActivity.this, "Registration success" + task.getException(),
                                                        Toast.LENGTH_SHORT).show();

                                                subregisteruser();


                                            }
                                        }
                                    });
//
                            Toast.makeText(RegisterActivity.this, "User Tidak Terdaftar", Toast.LENGTH_LONG).show();



                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(RegisterActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }


    private void subregisteruser(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

         api.apiregisteruser(

                stremail.toString(),
                strpassword.toString(),
                strusername.toString(),
                strnohp.toString(),
                strtipemember,


                //Creating an anonymous callback
                new Callback<Response>() {
                    @SuppressLint("InvalidWakeLockTag")
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

                        Intent intent = new Intent(getApplicationContext(), LoginUserActivity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(RegisterActivity.this, "Registrasi User Berhasil", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();
                        btnsimpan.setEnabled(true);

                        Toast.makeText(RegisterActivity.this, "Kesalahan Koneksi Data reg" + merror.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


}
