package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.binterbusih.adapter.DataPribadiAdapterListview;
import com.android.binterbusih.model.CDataPribadi;
import com.android.binterbusih.model.DataPribadi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UtamaAdminCari extends AppCompatActivity {

    EditText etcari;
    Button btncari;

    ListView lvpribadi;

    String stremail;
    String strusername;
    String strtipemember;

    String strcari;

    private List<DataPribadi> lstdatapribadi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_admin_cari);

        etcari=(EditText) findViewById(R.id.etcari);
        btncari=(Button) findViewById(R.id.btncari);


        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strcari=etcari.getText().toString();

                subambildataprojectcari();
            }
        });


        lvpribadi=(ListView) findViewById(R.id.lvpribadi);


        lvpribadi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });
        subambildataprojectcari();

    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainAdminUtamaActivity.class);
        startActivity(intent);
        finish();
    }

    private void subambildataprojectcari() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatapribadicari(
                strcari,


                new Callback<CDataPribadi>()
                {
                    @Override
                    public void success(CDataPribadi cdatapribadi, Response response) {
                        lstdatapribadi = new ArrayList<DataPribadi>();
                        lstdatapribadi = cdatapribadi.getDataPribadi();

//                        Toast.makeText(getApplicationContext(), String.valueOf(lstdatapribadi.size()),Toast.LENGTH_LONG).show();

                        DataPribadiAdapterListview vadapter = new DataPribadiAdapterListview(UtamaAdminCari.this, lstdatapribadi, "Cari");
                        lvpribadi.setAdapter(vadapter);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(UtamaAdminCari.this,  " Terjadi Kesalahan Kooneksi product" + merror, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}