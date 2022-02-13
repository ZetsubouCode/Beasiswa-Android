package com.android.binterbusih.adapter;

import static com.android.binterbusih.ConstantVariable.ROOT_URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.android.binterbusih.RegisterActivity;
import com.android.binterbusih.UtamaAdminActivity;
import com.android.binterbusih.model.DataPribadi;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DataPribadiAdapterListview extends BaseAdapter implements View.OnClickListener {


    private Activity activity;



    private static LayoutInflater inflater=null;




    String stremail;
    String strusername;
    String strdari;


    List<DataPribadi> lstdatapribadi;
    ConstantVariable CV = new ConstantVariable();
    public final String ROOT_URL = CV.getRootUrl();



    public DataPribadiAdapterListview(Activity a, List<DataPribadi> lstdatapribadi, String strdari) {
        this.activity = a;
        this.lstdatapribadi=lstdatapribadi;
        this.strdari=strdari;



        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    public int getCount() {
        return lstdatapribadi.size();
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
        View listViewItem = inflater.inflate(R.layout.tabdatapribadi, null, true);



        TextView tvemail = (TextView) listViewItem.findViewById(R.id.tvemail);
        TextView tvusername = (TextView) listViewItem.findViewById(R.id.tvusername);

         TextView tvnamakeluarga=(TextView) listViewItem.findViewById(R.id.tvnamakeluarga);
         TextView tvnamadepan=(TextView) listViewItem.findViewById(R.id.tvnamadepan);
         TextView tvnoktp=(TextView) listViewItem.findViewById(R.id.tvnoktp);

         TextView tvtempatlahir=(TextView) listViewItem.findViewById(R.id.tvtempatlahir);
         TextView tvtanggallahir=(TextView) listViewItem.findViewById(R.id.tvtanggallahir);
         TextView tvjeniskelamin=(TextView) listViewItem.findViewById(R.id.tvjeniskelamin);
         TextView tvalamatlengkap=(TextView) listViewItem.findViewById(R.id.tvalamatlengkap);
//         ImageView imgphoto=(ImageView) listViewItem.findViewById(R.id.imgphoto);
         TextView tvalamattetap=(TextView) listViewItem.findViewById(R.id.tvalamattetap);
         TextView tvalamatsementara=(TextView) listViewItem.findViewById(R.id.tvalamatsementara);
         TextView tvareaasal=(TextView) listViewItem.findViewById(R.id.tvareaasal);
         TextView tvagama=(TextView) listViewItem.findViewById(R.id.tvagama);
         TextView tvsuku=(TextView) listViewItem.findViewById(R.id.tvsuku);
         TextView tvwilayahadat=(TextView) listViewItem.findViewById(R.id.tvwilayahadat);


        Button btnpelajarmahasiswa = (Button) listViewItem.findViewById(R.id.btnpelajarmahasiswa);
        Button btndataorangtua = (Button) listViewItem.findViewById(R.id.btndataorangtua);
        Button btndataakademik = (Button) listViewItem.findViewById(R.id.btndataakademik);
        Button btndataperbankan = (Button) listViewItem.findViewById(R.id.btndataperbankan);

        Button btnterima = (Button) listViewItem.findViewById(R.id.btnterima);
        Button btntolak = (Button) listViewItem.findViewById(R.id.btntolak);
        ImageView imgdata= (ImageView) listViewItem.findViewById(R.id.imgdata);

        tvemail.setText("Email : " + lstdatapribadi.get(i).getemail());
        tvusername.setText("User Name : " + lstdatapribadi.get(i).getusername());
        tvnamakeluarga.setText("Nama Keluarga : " + lstdatapribadi.get(i).getnamakeluarga());
        tvnamadepan.setText("Nama Depan : " + lstdatapribadi.get(i).getnamadepan());
        tvnoktp.setText("No KTP : " + lstdatapribadi.get(i).getnoktp());


        tvtempatlahir.setText("Tempat lahir : " + lstdatapribadi.get(i).gettempatlahir());
        tvtanggallahir.setText("Tanggal Lahir : " + lstdatapribadi.get(i).gettanggallahir());
        tvjeniskelamin.setText("Jenis kelamin : " +lstdatapribadi.get(i).getjeniskelamin());
        tvalamatlengkap.setText("Alamat lengkap : " +lstdatapribadi.get(i).getalamatlengkap());
        tvalamattetap.setText("Alamat Tetap : " + lstdatapribadi.get(i).getalamattetap());


        tvalamatsementara.setText("Alamat Sementara : " +lstdatapribadi.get(i).getalamatsementara());
        tvareaasal.setText("Area Asal : " +lstdatapribadi.get(i).getareaasal());
        tvagama.setText("Agama : " +lstdatapribadi.get(i).getagama());
        tvsuku.setText("Suku : " +lstdatapribadi.get(i).getsuku());
        tvwilayahadat.setText("Wilayah Adat : " + lstdatapribadi.get(i).getwilayahadat());

        Picasso.with(imgdata.getContext()).load(ROOT_URL + "/probinterbusih/photo/" + lstdatapribadi.get(i).geturlphoto().toString()).
                fit().into(imgdata);

        btnpelajarmahasiswa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(lstdatapribadi.get(i).gettipemember().toString().equals("Pelajar") ){
                    Intent intent = new Intent(activity, DetailDataPelajarMahasiswaActivity.class);
                    intent.putExtra("kirimemail", lstdatapribadi.get(i).getemail());
                    intent.putExtra("kirimusername", lstdatapribadi.get(i).getusername());
                    intent.putExtra("kirimtipemember", lstdatapribadi.get(i).gettipemember());
                    intent.putExtra("kirimdari", strdari);

                    activity.startActivity(intent);
                    activity.finish();
                } else   if(lstdatapribadi.get(i).gettipemember().toString().equals("Mahasiswa") ){
                    Intent intent = new Intent(activity, DetailLembagaStudiActivity.class);
                    intent.putExtra("kirimemail", lstdatapribadi.get(i).getemail());
                    intent.putExtra("kirimusername", lstdatapribadi.get(i).getusername());
                    intent.putExtra("kirimtipemember", lstdatapribadi.get(i).gettipemember());
                    intent.putExtra("kirimdari", strdari);
                    activity.startActivity(intent);
                    activity.finish();
                }



            }
        });


        String strstatus;

        strstatus=lstdatapribadi.get(i).getstatus().toString();

        if (strstatus.equals("")){
            btnterima.setVisibility(View.VISIBLE);
            btntolak.setVisibility(View.VISIBLE);
            btndataakademik.setVisibility(View.GONE);

        } else   if (strstatus.equals("Terima")){

            btnterima.setVisibility(View.GONE);
            btntolak.setVisibility(View.GONE);
            btndataakademik.setVisibility(View.VISIBLE);

        }

        btndataperbankan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(activity, DetailPerbankanActivity.class);
                intent.putExtra("kirimemail", lstdatapribadi.get(i).getemail());
                intent.putExtra("kirimusername", lstdatapribadi.get(i).getusername());
                intent.putExtra("kirimtipemember", lstdatapribadi.get(i).gettipemember());
                intent.putExtra("kirimdari", strdari);
                activity.startActivity(intent);
                activity.finish();

            }
        });


        btndataorangtua.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(activity, DetailOrangTuaActivity.class);
                intent.putExtra("kirimemail", lstdatapribadi.get(i).getemail());
                intent.putExtra("kirimusername", lstdatapribadi.get(i).getusername());
                intent.putExtra("kirimtipemember", lstdatapribadi.get(i).gettipemember());
                intent.putExtra("kirimdari", strdari);
                activity.startActivity(intent);
                activity.finish();

            }
        });



        btndataakademik.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(lstdatapribadi.get(i).gettipemember().toString().equals("Pelajar") ){
                    Intent intent = new Intent(activity, DetailAkademikPelajarActivity.class);
                    intent.putExtra("kirimemail", lstdatapribadi.get(i).getemail());
                    intent.putExtra("kirimusername", lstdatapribadi.get(i).getusername());
                    intent.putExtra("kirimtipemember", lstdatapribadi.get(i).gettipemember());
                    intent.putExtra("kirimdari", strdari);
                    activity.startActivity(intent);
                    activity.finish();
                } else   if(lstdatapribadi.get(i).gettipemember().toString().equals("Mahasiswa") ){
                    Intent intent = new Intent(activity, DetailAkademikMahasiswaActivity.class);
                    intent.putExtra("kirimemail", lstdatapribadi.get(i).getemail());
                    intent.putExtra("kirimusername", lstdatapribadi.get(i).getusername());
                    intent.putExtra("kirimtipemember", lstdatapribadi.get(i).gettipemember());
                    intent.putExtra("kirimdari", strdari);
                    activity.startActivity(intent);
                    activity.finish();
                }



            }
        });




        btnterima.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                stremail=lstdatapribadi.get(i).getemail();
                String token=lstdatapribadi.get(i).gettoken();
                String title = "Pemberitahuan";
                String message = "Beasiswa anda telah di terima";
                simpanterimabeasiswa();
                Log.d("cek button",stremail+"sukses terima"+token);
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(ROOT_URL) //Setting the Root URL
                        .build(); //Finally building the adapter

                //Creating object for our interface
                ModulAPI api = adapter.create(ModulAPI.class);
                api.apisendMessage(
                        title,
                        message,
                        token,
                        new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                Log.d("DataPribadiAdapter","Sukses kirim notif");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                String merror = "ini errornya " + error.getMessage();
                                Log.e("DataPribadiAdapter","Message = "+merror);


                            }
                        }
                );
            }
        });


        btntolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stremail=lstdatapribadi.get(i).getemail();
                simpantolakbeasiswa();
            }
        });


        return listViewItem;
    }



    private void simpantolakbeasiswa(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpantolakbeasiswa(

                stremail.toString(),


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(activity, UtamaAdminActivity.class);
                        activity.startActivity(intent);
                        activity.finish();


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();
//                        btnsimpan.setEnabled(true);

                        Toast.makeText(activity, "Kesalahan Koneksi Data reg" + merror.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    private void simpanterimabeasiswa(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apissimpanterimabeasiswa(

                stremail.toString(),


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(activity, UtamaAdminActivity.class);
                        activity.startActivity(intent);
                        activity.finish();


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();
//                        btnsimpan.setEnabled(true);

                        Toast.makeText(activity, "Kesalahan Koneksi Data reg" + merror.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {

    }
}
