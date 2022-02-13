package com.android.binterbusih;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import com.android.binterbusih.model.DataUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginUserActivity extends AppCompatActivity {

    EditText etemail;
    EditText etpassword;
    Button btnlogin;
    TextView tvregister;

    String stremail;
    String strpassword;
    String strusername;
    String strnohp;
    String strtipemember;
    String strstatusproses;

    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_COURSE_LOCATION = 112;
    private static final int REQUEST_WRITE_STORAGE = 113;
    private static final int REQUEST_CAMERA = 114;

    Typeface facebold;
    Typeface facenormal;
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
//
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login_user);



        int permission3 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if (permission3 != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);


                builder.setMessage("Permission Camera")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        makeRequest3();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest3();
            }
        }


        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);


                builder.setMessage("Permission Write Storage")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        makeRequest1();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest1();
            }
        }



        int permission2 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permission2 != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);


                builder.setMessage("Permission location")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        makeRequest2();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest2();
            }
        }




        etemail=(EditText) findViewById(R.id.etemail);
        etpassword=(EditText) findViewById(R.id.etpassword);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        tvregister=(TextView) findViewById(R.id.tvregister);

//        etemail.setText( "coba@gmail.com");
//        etpassword.setText("123456");
//
        etemail.setText( "mahasiswa@gmail.com");
        etpassword.setText("mahasiswa123");
//
//        etemail.setText( "admin@gmail.com");
//        etpassword.setText("admin123");


        auth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(LoginUserActivity.this, "tombol login diklik", Toast.LENGTH_LONG).show();

//                btnlogin.setEnabled(false);
                stremail=etemail.getText().toString();
                strpassword=etpassword.getText().toString();
                strtipemember="";


                if (stremail.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi email ", Toast.LENGTH_LONG).show();
                    btnlogin.setEnabled(true);
                    return;
                }

                if (strpassword.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Isi Password ", Toast.LENGTH_LONG).show();
                    btnlogin.setEnabled(true);
                    return;
                }

                auth.signInWithEmailAndPassword(stremail,strpassword)
                        .addOnCompleteListener(LoginUserActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.d("Login Auth","Ada masalah di firebase");
                                    try {
                                        throw task.getException();
                                    }  catch(Exception e) {
                                        Log.e("Login error", e.getMessage());
                                    }
                                } else {
                                    subloginuser();
                                }

                            }
                        });



            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(LoginUserActivity.this, "tombol register diklik", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);
                finish();

            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }




    protected void makeRequest1() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }


    protected void makeRequest2() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_COURSE_LOCATION);
    }

    protected void makeRequest3() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA);
    }

    private void subloginuser() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        ConstantVariable CV = new ConstantVariable();
        String fcm = CV.getFCMToken();
        Toast.makeText(LoginUserActivity.this, "FCM token"+fcm, Toast.LENGTH_LONG).show();
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        //Calling method to get whether report
        api.apiloginuser(
                stremail.toString(),
                strpassword.toString(),

//                "coba@gmail.com",
//                "123456",
                new Callback<DataUser>() {
                        @Override
                    public void success(DataUser datauser, Response response) {

                    Log.d("cek data",datauser.getemail());
                            api.apisetFCM(
                                    stremail.toString(),
                                    fcm,
                                    new Callback<Response>() {
                                        @Override
                                        public void success(Response response, Response response2) {
                                            Log.d("Update fCM","Sukses update FCM//"+response);
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Log.d("Update fCM","Gagal update FCM//"+error);
                                        }
                                    }
                            );

                        strusername = datauser.getusername();

                        Log.d("Tes Masuk","Sukses di awal proses login");

                        if (strusername == null) {

                            btnlogin.setEnabled(true);
//                            Toast.makeText(LoginUserActivity.this, "User Tidak Terdaftar", Toast.LENGTH_LONG).show();

                        } else {

                            strnohp = datauser.getnohp();
                            strtipemember = datauser.gettipemember();
                            strstatusproses = datauser.getstatusproses();

                            if (strstatusproses.equals("")) {

                                if (strtipemember.equals("Admin")){

                                    Intent intent = new Intent(LoginUserActivity.this, MainAdminUtamaActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Intent intent = new Intent(LoginUserActivity.this, InputDataPribadiActivity.class);
                                    intent.putExtra("kirimemail", stremail);
                                    intent.putExtra("kirimusername", strusername);
                                    intent.putExtra("kirimtipemember", strtipemember);
                                    startActivity(intent);
                                    finish();
                                }


                            } else if (strstatusproses.equals("Proses")) {

                                Log.d("abcde","gagal");
                                Intent intent = new Intent(LoginUserActivity.this, SedangProsesActivity.class);
                                intent.putExtra("kirimemail", stremail);
                                intent.putExtra("kirimusername", strusername);
                                intent.putExtra("kirimtipemember", strtipemember);
                                startActivity(intent);
                                finish();



                            } else if (strstatusproses.equals("Tolak")) {

                                Log.d("abc","gagal");
                                Intent intent = new Intent(LoginUserActivity.this, DitolakActivity.class);
                                intent.putExtra("kirimemail", stremail);
                                intent.putExtra("kirimusername", strusername);
                                intent.putExtra("kirimtipemember", strtipemember);
                                startActivity(intent);
                                finish();


                            } else if (strstatusproses.equals("Terima")) {
                                Log.d("abczz","gagal");
                                if (strtipemember.equals("Pelajar")){

                                    Intent intent = new Intent(LoginUserActivity.this, UtamaSiswaActivity.class);
                                    intent.putExtra("kirimemail", stremail);
                                    intent.putExtra("kirimusername", strusername);
                                    intent.putExtra("kirimtipemember", strtipemember);
                                    startActivity(intent);
                                    finish();

                                } else if (strtipemember.equals("Mahasiswa")){
//                                session.createLoginSession(strusername, stremail, strstatus);

                                    Intent intent = new Intent(LoginUserActivity.this, UtamaMahasiswaActivity.class);
                                    intent.putExtra("kirimemail", stremail);
                                    intent.putExtra("kirimusername", strusername);
                                    intent.putExtra("kirimtipemember", strtipemember);

                                    startActivity(intent);
                                    finish();

                                }
                            }



                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();
                        Log.d("login",error.getMessage());
                        btnlogin.setEnabled(true);
                        Toast.makeText(LoginUserActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }

}
