package com.android.binterbusih;

import static com.android.binterbusih.ConstantVariable.ROOT_URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.binterbusih.model.DataPelajar;
import com.android.binterbusih.model.DataPribadi;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EditProfilPelajar extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;
    String foto_profil;
    String strsekolah;
    String urlphoto;

    TextView email;
    TextView username;
    TextView sekolah;

    Button btnupload;
    Button btnsimpan;

    ImageView profil;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_GALLERY = 200;
    String file_pathurl=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        email = (TextView) findViewById(R.id.email);
        username = (TextView) findViewById(R.id.username);
        sekolah = (TextView) findViewById(R.id.sekolah);
        profil = (ImageView) findViewById(R.id.imageView);
        btnupload=(Button) findViewById(R.id.Edit);

        btnsimpan=(Button) findViewById(R.id.Simpan);

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission()){
                        filePicker();
                    }
                    else{
                        requestPermission();
                    }
                }
                else{
                    filePicker();
                }
            }
        });


        foto_profil="";

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                simpanprofil();
            }
        });
        getSiswaData();
    }


    private void getSiswaData() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatapelajar(
                stremail.toString(),

                new Callback<DataPelajar>() {
                    @Override
                    public void success(DataPelajar data, Response response) {
                        stremail = data.getemail();
                        strusername = data.getusername();
                        strsekolah = data.getsekolah();
                        email.setText(stremail);
                        username.setText(strusername);
                        sekolah.setText(strsekolah);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(EditProfilPelajar.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );
        api.apiambildatapribadilengkap(
                stremail.toString(),

                new Callback<DataPribadi>() {
                    @Override
                    public void success(DataPribadi data, Response response) {
                        urlphoto = data.geturlphoto();
                        Log.d("check","url photo = "+urlphoto);
                        Picasso.with(profil.getContext()).load(ROOT_URL + "/probinterbusih/photo/" + urlphoto).
                                fit().into(profil);
                    }
                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(EditProfilPelajar.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }


    private void UploadFile() {
        EditProfilPelajar.UploadTask uploadTask=new EditProfilPelajar.UploadTask();
        uploadTask.execute(new String[]{file_pathurl});
    }


    private void filePicker(){

        //.Now Permission Working
        Toast.makeText(EditProfilPelajar.this, "File Picker Call", Toast.LENGTH_SHORT).show();
        //Let's Pick File
        Intent opengallery=new Intent(Intent.ACTION_PICK);
        opengallery.setType("image/*");
        startActivityForResult(opengallery,REQUEST_GALLERY);
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(EditProfilPelajar.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(EditProfilPelajar.this, "Please Give Permission to Upload File", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(EditProfilPelajar.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(EditProfilPelajar.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(EditProfilPelajar.this, "Permission Successfull", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfilPelajar.this, "Permission Failed", Toast.LENGTH_SHORT).show();
                }
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_GALLERY && resultCode== Activity.RESULT_OK){
            // String filePath=getRealPathFromUri(data.getData(),InputWisudaActivity.this);

            file_pathurl=getRealPathFromUri(data.getData(), EditProfilPelajar.this);

            File file=new File(file_pathurl);

            Bitmap bitmap = BitmapFactory.decodeFile(file_pathurl);
            profil.setImageBitmap(bitmap);


            foto_profil=file.getName();
            Log.d("ZZZZZZZZZZZZZ","Foto profil = "+foto_profil);
            Toast.makeText(EditProfilPelajar.this, foto_profil, Toast.LENGTH_LONG).show();

//            file_name.setText(file.getName());
            if(file_pathurl!=null){
                UploadFile();
            }
            else{
                Toast.makeText(EditProfilPelajar.this, "Please Select File First", Toast.LENGTH_SHORT).show();
            }
        }
    }




    public String getRealPathFromUri(Uri uri, Activity activity){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor=activity.getContentResolver().query(uri,proj,null,null,null);
        if(cursor==null){
            return uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int id=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(id);
        }
    }


    public class UploadTask extends AsyncTask<String,String,String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.equalsIgnoreCase("true")){
                Toast.makeText(EditProfilPelajar.this, "File uploaded", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(EditProfilPelajar.this, "Failed Upload", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            if(uploadFile(strings[0])){
                return "true";
            }
            else{
                return "failed";
            }
        }

        private boolean uploadFile(String path){
            File file=new File(path);
            try{
                RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("files",file.getName(),RequestBody.create(MediaType.parse("*/*"),file))
                        .addFormDataPart("some_key","some_value")
                        .addFormDataPart("submit","submit")
                        .build();

                Request request=new Request.Builder()
                        .url(ROOT_URL+"/probinterbusih/uploadphoto.php")
                        .post(requestBody)
                        .build();

                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {

                    }
                });
                return true;
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    private void simpanprofil(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apiupdateprofil(

                stremail.toString(),
                foto_profil,

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

                        Intent intent = new Intent(getApplicationContext(), EditProfilPelajar.class);
                        intent.putExtra("kirimemail", stremail);
                        intent.putExtra("kirimusername", strusername);
                        intent.putExtra("kirimtipemember", strtipemember);
                        startActivity(intent);
                        finish();

                        Toast.makeText(getApplicationContext(), "Edit Foto Berhasil ", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();
//                        btnsimpan.setEnabled(true);

                        Toast.makeText(getApplicationContext(), "Kesalahan Koneksi Data reg" + merror.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), UtamaSiswaActivity.class);
        intent.putExtra("kirimemail", stremail);
        intent.putExtra("kirimusername", strusername);
        intent.putExtra("kirimtipemember", strtipemember);
        startActivity(intent);
        finish();


    }
}