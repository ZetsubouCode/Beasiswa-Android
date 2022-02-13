package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAdminUtamaActivity extends AppCompatActivity {

    Button btnrequestpendaftaran;
    Button btncari;

    String stremail;
    String strusername;
    String strtipemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_utama);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        btnrequestpendaftaran=(Button) findViewById(R.id.btnrequestpendaftaran);
        btnrequestpendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminUtamaActivity.this, UtamaAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btncari=(Button) findViewById(R.id.btncari);
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminUtamaActivity.this, UtamaAdminCari.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginUserActivity.class);
        startActivity(intent);
        finish();
    }
}