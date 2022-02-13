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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class InputDataPribadiActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;
    String strnotelp;

    String strjeniskelamin;
    String strurlphoto;

    EditText etnotelp;
    EditText etnamakeluarga;
    EditText etnamadepan;
    EditText etnoktp;
    EditText ettempatlahir;
    Button btntgllahir;
    Spinner spjeniskelamin;
    EditText etalamatlengkap;
    Button btnupload;
    EditText etalamattetap;
    EditText etalamatsementara;
    EditText etareaasal;
    Spinner spagama;
    EditText etsuku;
    EditText etwilayahadat;

    String stragama;

    Button btnsimpan;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_GALLERY = 200;
    private static final int ALL_FILE_REQUEST = 102;
    private static final int PICK_PDF_REQUEST = 235;
    String file_pathurl=null;

    private Uri filePath;




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

    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar c;
    SimpleDateFormat df2 ;
    SimpleDateFormat df ;
    String strtgllahir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_pribadi);

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");




        etnamakeluarga=(EditText) findViewById(R.id.etnamakeluarga);
        etnamadepan=(EditText) findViewById(R.id.etnamadepan);
        etnotelp=(EditText) findViewById(R.id.etnotelp);
        etnoktp=(EditText) findViewById(R.id.etnoktp);

        ettempatlahir=(EditText) findViewById(R.id.ettempatlahir);
        btntgllahir=(Button) findViewById(R.id.btntgllahir);
        spjeniskelamin=(Spinner) findViewById(R.id.spjeniskelamin);
        etalamatlengkap=(EditText) findViewById(R.id.etalamatlengkap);
        btnupload=(Button) findViewById(R.id.btnupload);
        etalamattetap=(EditText) findViewById(R.id.etalamattetap);
        etalamatsementara=(EditText) findViewById(R.id.etalamatsementara);
        etareaasal=(EditText) findViewById(R.id.etareaasal);
        spagama=(Spinner) findViewById(R.id.spagama);
        etsuku=(EditText) findViewById(R.id.etsuku);
        etwilayahadat=(EditText) findViewById(R.id.etareaasal);

        btnsimpan=(Button) findViewById(R.id.btnsimpan);

        c = Calendar.getInstance();
        df2 = new SimpleDateFormat("yyyy-MM-dd");
        strtgllahir=df2.format(c.getTime());


        strurlphoto="";

        btntgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InputDataPribadiActivity.this,
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
                                strtgllahir= year + "-" + strbulan + "-" + strtanggal;
                                btntgllahir.setText(strtgllahir);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        spagama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stragama=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spjeniskelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strjeniskelamin=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanpribadi();
            }
        });

    }

    private void UploadFile() {
        UploadTask uploadTask=new UploadTask();
        uploadTask.execute(new String[]{file_pathurl});
    }




    private void filePicker(){

        //.Now Permission Working
        Toast.makeText(InputDataPribadiActivity.this, "File Picker Call", Toast.LENGTH_SHORT).show();
        //Let's Pick File
        Intent opengallery=new Intent(Intent.ACTION_PICK);
        opengallery.setType("image/*");
        startActivityForResult(opengallery,REQUEST_GALLERY);
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(InputDataPribadiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(InputDataPribadiActivity.this, "Please Give Permission to Upload File", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(InputDataPribadiActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(InputDataPribadiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
                    Toast.makeText(InputDataPribadiActivity.this, "Permission Successfull", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(InputDataPribadiActivity.this, "Permission Failed", Toast.LENGTH_SHORT).show();
                }
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_GALLERY && resultCode== Activity.RESULT_OK){
            // String filePath=getRealPathFromUri(data.getData(),InputDataPribadiActivity.this);

            file_pathurl=getRealPathFromUri(data.getData(),InputDataPribadiActivity.this);

            File file=new File(file_pathurl);

            Bitmap bitmap = BitmapFactory.decodeFile(file_pathurl);
//            imgphoto.setImageBitmap(bitmap);


            strurlphoto=file.getName();

            Toast.makeText(InputDataPribadiActivity.this, strurlphoto, Toast.LENGTH_LONG).show();

//            file_name.setText(file.getName());
            if(file_pathurl!=null){
                UploadFile();
            }
            else{
                Toast.makeText(InputDataPribadiActivity.this, "Please Select File First", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(InputDataPribadiActivity.this, "File uploaded", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(InputDataPribadiActivity.this, "Failed Upload", Toast.LENGTH_SHORT).show();
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
                        .url("https://www.cerdasplayer.my.id/probinterbusih/uploadphoto.php")
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
    
    
    private void simpanpribadi(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpandatapribadi(

                stremail.toString(),
                strusername.toString(),
                etnamakeluarga.getText().toString(),
                etnamadepan.getText().toString(),
                etnotelp.getText().toString(),
                etnoktp.getText().toString(),
                ettempatlahir.getText().toString(),
                strtgllahir.toString(),
                strjeniskelamin.toString(),
                etalamatlengkap.getText().toString(),
                strurlphoto.toString(),
                etalamattetap.getText().toString(),
                etalamatsementara.getText().toString(),
                etareaasal.getText().toString(),
                stragama.toString(),
                etsuku.getText().toString(),
                etwilayahadat.getText().toString(),
                strtipemember,


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

                        Intent intent = new Intent(getApplicationContext(), InputDataPerbankanActivity.class);
                        intent.putExtra("kirimemail", stremail);
                        intent.putExtra("kirimusername", strusername);
                        intent.putExtra("kirimtipemember", strtipemember);
                        startActivity(intent);
                        finish();




                        Toast.makeText(getApplicationContext(), "Simpan Pribadi Berhasil ", Toast.LENGTH_LONG).show();

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




    }
}