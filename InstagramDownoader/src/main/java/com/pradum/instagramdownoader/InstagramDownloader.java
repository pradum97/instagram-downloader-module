package com.pradum.instagramdownoader;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.pradum.instagramdownoader.Method.Check_Post_Type;

public class InstagramDownloader {

    public static ProgressDialog progressD;

    public static void setUrl(Context context , String url){
        
        progressD = new ProgressDialog(context,R.style.Theme_MyDialog);
        progressD.setCancelable(false);
        progressD.setMessage("Progress...");
        
        if (TextUtils.isEmpty(url)){

            Toast.makeText(context, "Please Enter Text", Toast.LENGTH_SHORT).show();
            return;
        }
        
        progressD.show();
        
        Check_Post_Type.post_link(context, url,progressD);
    }
}
