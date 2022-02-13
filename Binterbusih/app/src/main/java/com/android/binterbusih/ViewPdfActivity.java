package com.android.binterbusih;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ViewPdfActivity extends Activity {

    WebView webview;

    String strurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        Intent i=getIntent();
        strurl=  i.getStringExtra("kirimurl");

        Toast.makeText(ViewPdfActivity.this, "url " + strurl, Toast.LENGTH_LONG).show();



        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setJavaScriptEnabled(true);


        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                pDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                pDialog.dismiss();
            }
        });


//        final String strpdf="https://www.gerbangorganik.com/filepdf/Contohsaja.pdf";
        final String strpdf2;
//        strpdf2 = "https://firebasestorage.googleapis.com/v0/b/binterbusih.appspot.com/o/raports%2Fcoba2021Genap.pdf?alt=media&token=87d6bb22-afba-4a1e-9120-69b96d2d5615";
        strpdf2=strurl;

        String url="";
        try {
            url= URLEncoder.encode(strpdf2,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+url);



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainAdminUtamaActivity.class);
        startActivity(intent);
        finish();
    }

}
