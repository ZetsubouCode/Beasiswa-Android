package com.android.binterbusih;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE_STORAGE = 113;
    Handler handler;

    PowerManager.WakeLock wakeLock;
    Intent intentMyIntentService;

    @SuppressLint("InvalidWakeLockTag")
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        int permission = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//
//                builder.setMessage("Permission Write Storage")
//                        .setTitle("Permission required");
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        makeRequest1();
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            } else {
//                makeRequest1();
//            }
//        }



        setContentView(R.layout.activity_splash);

//        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/mathlock2");
//        if (!dir.exists()){
//
//            intentMyIntentService = new Intent(SplashActivity.this, MyIntentService.class);
//            startService(intentMyIntentService);
//
//            String strwakelock="1234234";
//            PowerManager mgr = (PowerManager)getApplicationContext().getSystemService(Context.POWER_SERVICE);
//            wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
//            wakeLock.acquire();
//
//            intentMyIntentService = new Intent(SplashActivity.this, MyIntentService.class);
//
//            startService(intentMyIntentService);
//
//
//            try{
//                if(dir.mkdir()) {
//
//                } else {
//
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, LoginUserActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }



    @Override
    protected void onStart() {
        super.onStart();
//        String path = Environment.getExternalStorageDirectory().toString()+"/mathlock";
//
//        File directory = new File(path);
//        files = directory.listFiles();
//        strnamafolder=files[0].getName();
//
////        Toast.makeText(SplashActivity.this,"folder" + strnamafolder,Toast.LENGTH_LONG).show();
//
//        if (files[0].isDirectory()) {
//
//            String path2 = Environment.getExternalStorageDirectory().toString()+"/mathlock/"+strnamafolder  ;
//
//
//
//        }

    }

    protected void makeRequest1() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }


}