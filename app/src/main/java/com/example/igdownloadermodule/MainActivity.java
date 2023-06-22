package com.example.igdownloadermodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pradum.instagramdownoader.InstagramDownloader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://www.instagram.com/p/CSErIQuKMmF/?utm_source=ig_web_copy_link";

        InstagramDownloader.setUrl(this,url);
    }
}