package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class UtamaAdminActivity extends AppCompatActivity {

    ListView lvpribadi;
    Button terima;
    String stremail;
    String strusername;
    String strtipemember;

    private List<DataPribadi> lstdatapribadi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_admin);

        terima = (Button) findViewById(R.id.btnterima);

        lvpribadi=(ListView) findViewById(R.id.lvpribadi);


        lvpribadi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });
        subambildataprojectproses();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainAdminUtamaActivity.class);
        startActivity(intent);
        finish();
    }

    private void subambildataprojectproses() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatapribadi(


                new Callback<CDataPribadi>()
                {
                    @Override
                    public void success(CDataPribadi cdatapribadi, Response response) {
                        lstdatapribadi = new ArrayList<DataPribadi>();
                        lstdatapribadi = cdatapribadi.getDataPribadi();

                        Toast.makeText(getApplicationContext(), String.valueOf(lstdatapribadi.size()),Toast.LENGTH_LONG).show();

                        DataPribadiAdapterListview vadapter = new DataPribadiAdapterListview(UtamaAdminActivity.this, lstdatapribadi, "Request");
                        lvpribadi.setAdapter(vadapter);




                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(UtamaAdminActivity.this,  " Terjadi Kesalahan Kooneksi product" + merror, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }



}