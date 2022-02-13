package com.android.binterbusih.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.ConstantVariable;
import com.android.binterbusih.DetailAkademikMahasiswaActivity;
import com.android.binterbusih.DetailAkademikPelajarActivity;
import com.android.binterbusih.DetailDataPelajarMahasiswaActivity;
import com.android.binterbusih.DetailLembagaStudiActivity;
import com.android.binterbusih.DetailOrangTuaActivity;
import com.android.binterbusih.DetailPerbankanActivity;
import com.android.binterbusih.ModulAPI;
import com.android.binterbusih.R;
import com.android.binterbusih.UtamaAdminActivity;
import com.android.binterbusih.ViewPdfActivity;
import com.android.binterbusih.model.DataAkademikSiswa;
import com.android.binterbusih.model.DataPribadi;
import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DataAkademikSiswaAdapterListview extends BaseAdapter implements View.OnClickListener {


    private Activity activity;



    private static LayoutInflater inflater=null;




    String stremail;
    String strusername;
    Integer posisi;


    List<DataAkademikSiswa> lstakademiksiswa;

    ConstantVariable CV = new ConstantVariable();
    public final String ROOT_URL = CV.getRootUrl();



    public DataAkademikSiswaAdapterListview(Activity a, List<DataAkademikSiswa> lstakademiksiswa) {
        this.activity = a;
        this.lstakademiksiswa=lstakademiksiswa;



        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    public int getCount() {
        return lstakademiksiswa.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/


    public View getView(final int i, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.tabdatalistview, null, true);
        posisi=i;

        TextView tvdata = (TextView) listViewItem.findViewById(R.id.tvdata);
        Button btnwebpdf= (Button) listViewItem.findViewById(R.id.btnwebpdf);

        tvdata.setText("Email : " + lstakademiksiswa.get(i).getemail() + "\n" +
                "Nama Siswa : " + lstakademiksiswa.get(i).getusername() + "\n" +
                "Sekolah : " + lstakademiksiswa.get(i).getsekolah() + "\n" +
                "Jurusan : " + lstakademiksiswa.get(i).getjurusan() + "\n" +
                "Semester : " + lstakademiksiswa.get(i).getsemester() + "\n" +
                "Tahun Ajaran : " + lstakademiksiswa.get(i).gettahunajaran() + "\n" +
                "Nilai Rata-Rata : " + lstakademiksiswa.get(i).getnilairatarata() + "");

        btnwebpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, "url " + lstakademiksiswa.get(posisi).geturlraport().toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(activity, ViewPdfActivity.class);
                intent.putExtra("kirimurl", lstakademiksiswa.get(posisi).geturlraport().toString());
                activity.startActivity(intent);
                activity.finish();
            }
        });


//
//
//
//        Glide.with(activity)
//                .load( "https://www.cerdasplayer.my.id/probinterbusih/raport/" + lstakademiksiswa.get(i).geturlraport().toString() )
//                .into(imgdata);



        return listViewItem;
    }




    @Override
    public void onClick(View v) {

    }
}
