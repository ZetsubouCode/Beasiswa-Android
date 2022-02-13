package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SedangProsesActivity extends AppCompatActivity {

    TextView tvhasil;

    String stremail;
    String strusername;
    String strtipemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedang_proses);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        tvhasil=(TextView) findViewById(R.id.tvhasil);

        tvhasil.setText("Pengajuan Beasiswa Anda\n" +
                "Sedang Kami Proses\n");

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginUserActivity.class);
        startActivity(intent);
        finish();
    }
}