package com.pradum.instagramdownoader.Method;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.pradum.instagramdownoader.R;

public class Download {

    static ProgressDialog progressDialog;
    private static Context ctx;

    public static void download(String downloadPath,
                                String destinationPath, Context context,
                                String fileName, ProgressDialog progressDialog) {

        ctx = context;

        Download.progressDialog = progressDialog;
        Download.progressDialog.setMessage("Downloading...");
        Download.progressDialog.setCancelable(false);
        Download.progressDialog.show();

        Toast.makeText(context, "Downloading started...", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(fileName);
        request.setDescription("Downloading...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath + fileName);
        ((DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);

        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    static BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctx, Intent intent) {

            Toast.makeText(ctx, "Download Complete", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    };
}
