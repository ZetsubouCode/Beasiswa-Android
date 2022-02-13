package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UtamaSiswaActivity extends AppCompatActivity {

    Button btninputsemester;
    Button btneditprofil;
//    Button btninputwisuda;

    String stremail;
    String strusername;
    String strtipemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_siswa);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        btneditprofil=(Button) findViewById(R.id.btneditprofil);
        btninputsemester=(Button) findViewById(R.id.btninputsemester);
        btninputsemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InputAkademikPelajarActivity.class);
                intent.putExtra("kirimemail", stremail);
                intent.putExtra("kirimusername", strusername);
                intent.putExtra("kirimtipemember", strtipemember);
                startActivity(intent);
                finish();
            }
        });
        btneditprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfilPelajar.class);
                intent.putExtra("kirimemail", stremail);
                intent.putExtra("kirimusername", strusername);
                intent.putExtra("kirimtipemember", strtipemember);
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