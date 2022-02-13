package com.android.binterbusih;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import static com.android.binterbusih.ConstantVariable.ROOT_URL;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.binterbusih.model.DataLembagastudi;
import com.android.binterbusih.model.DataPelajar;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

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

public class InputAkademikPelajarActivity extends AppCompatActivity {

    String stremail;
    String strusername;
    String strtipemember;
    String strurlraport;


    EditText etsekolah;
    EditText etjurusan;
    Spinner spsemester;
    EditText ettahunajaran;
    EditText etnilairatarata;
    Button btnupload;
    Button btnsimpan;

    String strsemester;
    Uri uri;

    String[] mimeTypes =
            {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                    "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                    "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                    "text/plain",
                    "application/pdf",
                    "application/zip",
                    "file/*",
                    "file/*"};

    Calendar cal;
    SimpleDateFormat df1;
    SimpleDateFormat df2;
    String strwaktu;
    private int PICK_PDF_REQUEST = 1;


    public static final String PDF_UPLOAD_HTTP_URL = "https://wwww.cerdasplayer.my.id/probinterbusih/server_upload_pdf.php";
//    public static final String PDF_UPLOAD_HTTP_URL = "https://wwww.cerdasplayer.my.id/prouploadpdf/file_upload.php";


    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfPathHolder, PdfID;



    private Uri filePath;


    StorageReference storageReference;
    FirebaseStorage firebaseStorage;

    private FirebaseAuth auth;

    public static final String STORAGE_PATH_RAPORTS = "raports/";

    String strtahunajaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_akademik_pelajar);

        AllowRunTimePermission();

        Intent i=getIntent();
        stremail= i.getStringExtra("kirimemail");
        strusername= i.getStringExtra("kirimusername");
        strtipemember= i.getStringExtra("kirimtipemember");


        auth = FirebaseAuth.getInstance();


        storageReference = FirebaseStorage.getInstance().getReference();




        etsekolah=(EditText) findViewById(R.id.etsekolah);
        etjurusan=(EditText) findViewById(R.id.etjurusan);
        spsemester=(Spinner) findViewById(R.id.spsemester);
        ettahunajaran=(EditText) findViewById(R.id.ettahunajaran);
        etnilairatarata=(EditText) findViewById(R.id.etnilairatarata);
        btnupload=(Button) findViewById(R.id.btnupload);
        btnsimpan=(Button) findViewById(R.id.btnsimpan);

        etsekolah.setText("sekolahsaja");
        etjurusan.setText("jurusan");

        strurlraport="";

        cal = Calendar.getInstance();
        df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");


        spsemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsemester=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strtahunajaran=ettahunajaran.getText().toString();

                if (strtahunajaran.equals("")) {
                    Toast.makeText(InputAkademikPelajarActivity.this, "Tahun Ajaran Mesti di isi", Toast.LENGTH_LONG).show();
                    return;
                }

                String mimeTypesStr = "";
                for (String mimeType : mimeTypes) {
                    mimeTypesStr += mimeType + "|";
                }

                Intent intent = new Intent();
                // tipe file yang dipilih imeage
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);

//
//                Intent intent = new Intent();
//
//                intent.setType("application/pdf");
//
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

            }
        });


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                simpanakademikpelajar();
            }
        });
        subambilpelajar();
    }




//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            uri = data.getData();
//
//            Toast.makeText(InputAkademikPelajarActivity.this, "berhasil", Toast.LENGTH_LONG).show();
//
//            PdfUploadFunction();
//
//        }
//    }
////
//    public void PdfUploadFunction() {
//
//        PdfNameHolder = strusername.toString() + strsemester.toString();
//
//        strurlraport=strusername.toString() + strsemester.toString() +".pdf";
//
//        PdfPathHolder = FilePath2.getPath(this, uri);
//
//        if (PdfPathHolder == null) {
//
//            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();
//
//        } else {
//
//            try {
//
//                PdfID = UUID.randomUUID().toString();
//
//                new MultipartUploadRequest(this, PdfID, PDF_UPLOAD_HTTP_URL)
//                        .addFileToUpload(PdfPathHolder, "pdf")
//                        .addParameter("name", PdfNameHolder)
//                        .setNotificationConfig(new UploadNotificationConfig())
//                        .setMaxRetries(5)
//                        .startUpload();
//
//                Toast.makeText(this, "masuk", Toast.LENGTH_SHORT).show();
//
//            } catch (Exception exception) {
//
//                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    // bila sudah memilih akan balik ke sini untuk upload file sesuai fliePath yang dipilih filenya
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            uploadFile();
        }
    }


    // menadapatkan ekstensi file
    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            // module upload ke server firebase diletakan sesuai  Constants.STORAGE_PATH_UPLOADS + strchatkirim + "." + getFileExtension(filePath
            // dari file filePath
            final StorageReference sRef = storageReference.child(STORAGE_PATH_RAPORTS + strusername + strtahunajaran + strsemester + "."   + getFileExtension(filePath));

            UploadTask uploadTask = sRef.putFile(filePath);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return sRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    // kalau sukses upload baru simpan chatnya
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        progressDialog.dismiss();
//                        strurlfile= strchatkirim + "." + getFileExtension(filePath);
                        strurlraport= downloadUri.toString();

//                        strpdfnews=strurlfile;
//                        Picasso.with(InputAkademikPelajarActivity.this).load( strpdfnews ).into(imgnews);



                        Toast.makeText(InputAkademikPelajarActivity.this, strurlraport , Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(InputAkademikPelajarActivity.this, "Fail UPLOAD", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    progressDialog.setMessage("Uploaded: ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(InputAkademikPelajarActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        } else {
            //display an error if no file is selected
        }
    }



    public void AllowRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(InputAkademikPelajarActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(InputAkademikPelajarActivity.this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(InputAkademikPelajarActivity.this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(InputAkademikPelajarActivity.this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(InputAkademikPelajarActivity.this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    private void subambilpelajar() {
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

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataPelajar>() {
                    @Override
                    public void success(DataPelajar datapelajar, Response response) {

                        strusername = datapelajar.getusername();

                        etsekolah.setText( datapelajar.getsekolah().toString());
                        etsekolah.setEnabled(false);

                        etjurusan.setText( datapelajar.getjurusan().toString());
                        etjurusan.setEnabled(false);




                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(InputAkademikPelajarActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }



    private void subambildatapelajar() {
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

//                "coba@gmail.com",
//                "1234678",
                new Callback<DataPelajar>() {
                    @Override
                    public void success(DataPelajar datapelajar, Response response) {

                        strusername = datapelajar.getusername();

                        etsekolah.setText(datapelajar.getsekolah().toString());
                        etsekolah.setEnabled(false);

                        etjurusan.setText(datapelajar.getjurusan().toString());
                        etjurusan.setEnabled(false);


                    }

                    @Override
                    public void failure(RetrofitError error) {

                        String merror = "ini errornya " + error.getMessage();

                        Toast.makeText(InputAkademikPelajarActivity.this, "Kesalahan Koneksi Data"  + merror, Toast.LENGTH_LONG).show();

                    }
                }

        );

    }







    private void simpanakademikpelajar(){




        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ModulAPI api = adapter.create(ModulAPI.class);

        api.apisimpandataakademiksiswa(

                stremail.toString(),
                strusername.toString(),
                etsekolah.getText().toString(),
                etjurusan.getText().toString(),
                strsemester.toString(),
                ettahunajaran.getText().toString(),
                etnilairatarata.getText().toString(),
                strurlraport.toString(),



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

                        Intent intent = new Intent(getApplicationContext(), UtamaSiswaActivity.class);
                        intent.putExtra("kirimemail", stremail);
                        intent.putExtra("kirimusername", strusername);
                        intent.putExtra("kirimtipemember", strtipemember);
                        startActivity(intent);
                        finish();






                        Toast.makeText(getApplicationContext(), "Simpan Data Akademik Pelajar Berhasil ", Toast.LENGTH_LONG).show();

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