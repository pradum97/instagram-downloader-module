package com.pradum.instagramdownoader.Method;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Instagram_Image {

    public static void downloadImage(Context context, String imageUrl, ProgressDialog progressDialog) {

        String image;

        String[] finalImageUrl = new String[1];

        finalImageUrl[0] = null;

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);

        if (TextUtils.isEmpty(imageUrl)) {
            Toast.makeText(context, "Please enter post URL.", Toast.LENGTH_SHORT).show();
            Log.e("InstaImagesError", "provided string is empty");
        } else {

            if (imageUrl.contains("?utm_source=ig_web_copy_link")) {

                String partToRemove = "?utm_source=ig_web_copy_link";

                image = imageUrl.replace(partToRemove, "");
            } else if (imageUrl.contains("?utm_source=ig_web_button_share_sheet")) {
                String partToRemove = "?utm_source=ig_web_button_share_sheet";
                image = imageUrl.replace(partToRemove, "");
            } else if (imageUrl.contains("?utm_medium=share_sheet")) {
                String partToRemove = "?utm_medium=share_sheet";
                image = imageUrl.replace(partToRemove, "");
            } else if (imageUrl.contains("?utm_medium=copy_link")) {
                String partToRemove = "?utm_medium=copy_link";
                image = imageUrl.replace(partToRemove, "");
            }
            else {
                image = imageUrl;
            }


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    image + "?__a=1", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    JSONObject Obj1 = null;
                    try {
                        Obj1 = response.getJSONObject("graphql");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject Obj2 = null;
                    try {
                        Obj2 = Obj1.getJSONObject("shortcode_media");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finalImageUrl[0] = null;
                    try {

                        finalImageUrl[0] = Obj2.getString("display_url");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("finalURL", finalImageUrl[0]);


                    // Folder
                    String RootDirectoryInstagram = "/Instagram Downloader/instagram Images/";

                    Random random = new Random();

                    int i = random.nextInt(1000000);
                    int i2 = random.nextInt(22345678);

                    String s = String.valueOf(i + i2);

                    Download.download(imageUrl,RootDirectoryInstagram, context, "image"+s+".png",progressDialog);

                }


            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e("InstaImagesError", "Something went wrong" + error);

                    Toast.makeText(context, "Something went wrong : "+error, Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();

                    System.out.println("====="+error.getMessage());

                }
            });

            requestQueue.add(jsonObjectRequest);



        }
    }
}
