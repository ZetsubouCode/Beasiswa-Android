package com.android.binterbusih.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.ConstantVariable;
import com.android.binterbusih.R;
import com.android.binterbusih.model.DataAkademikMahasiswa;
import com.android.binterbusih.model.DataAkademikSiswa;
import com.bumptech.glide.Glide;

import java.util.List;




public class DataAkademikMahaiswaAdapterListview extends BaseAdapter implements View.OnClickListener {


    private Activity activity;



    private static LayoutInflater inflater=null;




    String stremail;
    String strusername;


    List<DataAkademikMahasiswa> lstdataakademikmahasiswa;

    ConstantVariable CV = new ConstantVariable();
    public final String ROOT_URL = CV.getRootUrl();



    public DataAkademikMahaiswaAdapterListview(Activity a, List<DataAkademikMahasiswa> lstdataakademikmahasiswa) {
        this.activity = a;
        this.lstdataakademikmahasiswa=lstdataakademikmahasiswa;



        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    public int getCount() {
        return lstdataakademikmahasiswa.size();
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
        View listViewItem = inflater.inflate(R.layout.tabdatalistview2, null, true);

        TextView tvdata = (TextView) listViewItem.findViewById(R.id.tvdata);
        ImageView imgdata= (ImageView) listViewItem.findViewById(R.id.imgdata);

        tvdata.setText("Email : " + lstdataakademikmahasiswa.get(i).getemail() + "\n" +
                "Nama Siswa : " + lstdataakademikmahasiswa.get(i).getusername() + "\n" +
                "Perguruan Tinggi : " + lstdataakademikmahasiswa.get(i).getperguruantinggi() + "\n" +
                "Program Studi : " + lstdataakademikmahasiswa.get(i).getprogramstudi() + "\n" +
                "Semester : " + lstdataakademikmahasiswa.get(i).getsemester() + "\n" +
                "Tahun Ajaran : " + lstdataakademikmahasiswa.get(i).gettahunajaran() + "\n" +
                "IPK : " + lstdataakademikmahasiswa.get(i).getipk() + "");


        Toast.makeText(activity, lstdataakademikmahasiswa.get(i).geturltranskrip(), Toast.LENGTH_LONG).show();

        Glide.with(activity)
                .load( ROOT_URL+"/probinterbusih/transkrip/" + lstdataakademikmahasiswa.get(i).geturltranskrip().toString() )
                .into(imgdata);




        return listViewItem;
    }




    @Override
    public void onClick(View v) {

    }
}
