package com.android.binterbusih;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.binterbusih.model.DataLembagastudi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import retrofit.http.Field;

public class InputWisudaActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;

    String strurlijazah;


    EditText etperguruantinggi;
    EditText etprogramstudi;
    EditText etjenjangpendidikan;
    Button btntanggalselesai;
    EditText etipk;
    Button btnupload;

    String strtanggalselesai;

    Button btnsimpan;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_GALLERY = 200;
    private static final int ALL_FILE_REQUEST = 102;
    private static final int PICK_PDF_REQUEST = 235;
    String file_pathurl=null;

    private Uri filePath;


    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar c;
    SimpleDateFormat df2 ;
    SimpleDateFormat df ;
    String strtgllahir;

    //Pdf request code


    String[] mimeTypes =
            {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                    "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                    "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                    "text/plain",
                    "application/pdf",
                    "application/zip",
                    "file/*",
                    "file/*"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_wisuda);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");

        etperguruantinggi=(EditText) findViewById(R.id.etperguruantinggi);
        etprogramstudi=(EditText) findViewById(R.id.etprogramstudi);
        etjenjangpendidikan=(EditText) findViewById(R.id.etjenjangpendidikan);
        btntanggalselesai=(Button) findViewById(R.id.btntanggalselesai);

        etipk=(EditText) findViewById(R.id.etipk);
        btnupload=(Button) findViewById(R.id.btnupload);


        btnsimpan=(Button) findViewById(R.id.btnsimpan);


        c = Calendar.getInstance();
        df2 = new SimpleDateFormat("yyyy-MM-dd");
        strtanggalselesai=df2.format(c.getTime());


        btntanggalselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InputWisudaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String strbulan;
                                String strtanggal;

                                if (monthOfYear<9){
                                    strbulan= "0" + String.valueOf(monthOfYear+1);
                                }
                                else {
                                    strbulan=  String.valueOf(monthOfYear+1);
                                }

                                if (dayOfMonth<10){
                                    strtanggal= "0" + String.valueOf(dayOfMonth);
                                }
                                else {
                                    strtanggal=  String.valueOf(dayOfMonth);
                                }
                                strtanggalselesai= year + "-" + strbulan + "-" + strtanggal;
                                btntanggalselesai.setText(strtanggalselesai);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

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


        strurlijazah="";

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                simpanwisuda();
            }
        });



        subambillembagastudi();
    }


    private void subambillembagastudi() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);


        //Calling method to get whether report
        api.apiambildatalembagastudi(
                stremail.toString(),

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataLembagastudi>() {
                    @Override
                    public void success(DataLembagastudi datalembagastudi, Response response) {

                        strusername = datalembagastudi.getusername();



                        etperguruantinggi.setText( datalembagastudi.getnamalembagastudi().toString());
                        etperguruantinggi.setEnabled(false);

                        etprogramstudi.setText( datalembagastudi.getnamajurusan().toString());
                        etprogramstudi.setEnabled(false);

                        etjenjangpendidikan.setText( datalembagastudi.getjenjangpendidikan().toString());
                        etjenjangpendidikan.setEnabled(false);




                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(InputWisudaActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }


    private void UploadFile() {
        InputWisudaActivity.UploadTask uploadTask=new InputWisudaActivity.UploadTask();
        uploadTask.execute(new String[]{file_pathurl});
    }




    private void filePicker(){

        //.Now Permission Working
        Toast.makeText(InputWisudaActivity.this, "File Picker Call", Toast.LENGTH_SHORT).show();
        //Let's Pick File
        Intent opengallery=new Intent(Intent.ACTION_PICK);
        opengallery.setType("image/*");
        startActivityForResult(opengallery,REQUEST_GALLERY);
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(InputWisudaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(InputWisudaActivity.this, "Please Give Permission to Upload File", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(InputWisudaActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(InputWisudaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
                    Toast.makeText(InputWisudaActivity.this, "Permission Successfull", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(InputWisudaActivity.this, "Permission Failed", Toast.LENGTH_SHORT).show();
                }
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_GALLERY && resultCode== Activity.RESULT_OK){
            // String filePath=getRealPathFromUri(data.getData(),InputWisudaActivity.this);

            file_pathurl=getRealPathFromUri(data.getData(),InputWisudaActivity.this);

            File file=new File(file_pathurl);

            Bitmap bitmap = BitmapFactory.decodeFile(file_pathurl);
//            imgphoto.setImageBitmap(bitmap);


            strurlijazah=file.getName();

            Toast.makeText(InputWisudaActivity.this, strurlijazah, Toast.LENGTH_LONG).show();

//            file_name.setText(file.getName());
            if(file_pathurl!=null){
                UploadFile();
            }
            else{
                Toast.makeText(InputWisudaActivity.this, "Please Select File First", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(InputWisudaActivity.this, "File uploaded", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(InputWisudaActivity.this, "Failed Upload", Toast.LENGTH_SHORT).show();
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
//                        .url("http://192.168.0.2/project/upload.php")
                        .url("https://www.cerdasplayer.my.id/probinterbusih/uploadijazah.php")
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

    private void simpanwisuda(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpanwisuda(

                stremail.toString(),
                strusername.toString(),
                etperguruantinggi.getText().toString(),
                etprogramstudi.getText().toString(),
                etjenjangpendidikan.getText().toString(),
                strtanggalselesai.toString(),
                etipk.getText().toString(),
                strurlijazah,


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

                        Intent intent = new Intent(getApplicationContext(), UtamaMahasiswaActivity.class);
                        intent.putExtra("kirimemail", stremail);
                        intent.putExtra("kirimusername", strusername);
                        intent.putExtra("kirimtipemember", strtipemember);
                        startActivity(intent);
                        finish();

                        Toast.makeText(getApplicationContext(), "Simpan Wisuda Berhasil ", Toast.LENGTH_LONG).show();

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

        Intent intent = new Intent(getApplicationContext(), UtamaMahasiswaActivity.class);
        intent.putExtra("kirimemail", stremail);
        intent.putExtra("kirimusername", strusername);
        intent.putExtra("kirimtipemember", strtipemember);
        startActivity(intent);
        finish();


    }
}