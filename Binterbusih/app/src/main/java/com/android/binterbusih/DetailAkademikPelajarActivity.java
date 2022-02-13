package com.android.binterbusih;

import androidx.appcompat.app.AppCompatActivity;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.binterbusih.adapter.DataAkademikSiswaAdapterListview;
import com.android.binterbusih.adapter.DataPribadiAdapterListview;
import com.android.binterbusih.model.CDataAkademikSiswa;
import com.android.binterbusih.model.CDataPribadi;
import com.android.binterbusih.model.DataAkademikSiswa;
import com.android.binterbusih.model.DataPribadi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailAkademikPelajarActivity extends AppCompatActivity {

    ListView lvakademiksiswa;

    String stremail;
    String strusername;
    String strtipemember;
    String strdari;

    private List<DataAkademikSiswa> lstdatakademiksiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akademik_pelajar);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");
        strdari= i.getStringExtra("kirimdari");

        lvakademiksiswa=(ListView) findViewById(R.id.lvakademiksiswa);


        lvakademiksiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });
        subambildataakademiksiswa();
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

    private void subambildataakademiksiswa() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apidataakademikssiswa(
                stremail,


                new Callback<CDataAkademikSiswa>()
                {
                    @Override
                    public void success(CDataAkademikSiswa cdataakademiksiswa, Response response) {
                        lstdatakademiksiswa = new ArrayList<DataAkademikSiswa>();
                        lstdatakademiksiswa = cdataakademiksiswa.getDataAkademikSiswa();

                        Toast.makeText(getApplicationContext(), String.valueOf(lstdatakademiksiswa.size()),Toast.LENGTH_LONG).show();

                        DataAkademikSiswaAdapterListview vadapter = new DataAkademikSiswaAdapterListview(DetailAkademikPelajarActivity.this, lstdatakademiksiswa);
                        lvakademiksiswa.setAdapter(vadapter);




                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = error.getMessage();

                        Toast.makeText(DetailAkademikPelajarActivity.this,  " Terjadi Kesalahan Kooneksi product" + merror, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

}