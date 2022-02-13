package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.binterbusih.adapter.DataAkademikMahaiswaAdapterListview;
import com.android.binterbusih.adapter.DataPribadiAdapterListview;
import com.android.binterbusih.model.CDataAkademikMahasiswa;
import com.android.binterbusih.model.CDataAkademikSiswa;
import com.android.binterbusih.model.CDataPribadi;
import com.android.binterbusih.model.DataAkademikMahasiswa;
import com.android.binterbusih.model.DataAkademikSiswa;
import com.android.binterbusih.model.DataPribadi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailAkademikMahasiswaActivity extends AppCompatActivity {

    ListView lvpribadi;

    String stremail;
    String strusername;
    String strtipemember;
    String strdari;

    private List<DataAkademikMahasiswa> lstdataakademikmahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akademik_mahasiswa);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");
        strdari= i.getStringExtra("kirimdari");

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

        if (strdari.equals("Request")) {
            Intent intent = new Intent(getApplicationContext(), UtamaAdminActivity.class);
            startActivity(intent);
            finish();
        } else if (strdari.equals("Cari")) {
            Intent intent = new Intent(getApplicationContext(), UtamaAdminCari.class);
            startActivity(intent);
            finish();
        }


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
        api.apiambildataakademikmahassiswa(
                stremail,


                new Callback<CDataAkademikMahasiswa>()
                {
                    @Override
                    public void success(CDataAkademikMahasiswa cdataakademikmahasiswa, Response response) {
                        lstdataakademikmahasiswa = new ArrayList<DataAkademikMahasiswa>();
                        lstdataakademikmahasiswa = cdataakademikmahasiswa.getDataAkademikMahasiswa();

                        Toast.makeText(getApplicationContext(), String.valueOf(lstdataakademikmahasiswa.size()),Toast.LENGTH_LONG).show();

                        DataAkademikMahaiswaAdapterListview vadapter = new DataAkademikMahaiswaAdapterListview(DetailAkademikMahasiswaActivity.this, lstdataakademikmahasiswa);
                        lvpribadi.setAdapter(vadapter);




                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(DetailAkademikMahasiswaActivity.this,  " Terjadi Kesalahan Kooneksi product" + merror, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}