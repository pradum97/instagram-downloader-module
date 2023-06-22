package com.pradum.instagramdownoader.Method;

import android.app.ProgressDialog;
import android.content.Context;

public class Check_Post_Type {

    public static  void post_link(Context context, String link, ProgressDialog progressDialog){

        for(int pos = 24 ; pos < 27;pos++){

            switch (link.charAt(pos)) {

                case 'p': {  // photo

                    Instagram_Image.downloadImage(context,link,progressDialog);

                }
                break;

                case 't': {  // video

                    Instagram_Video.downloadVideo(context,link,"Video",progressDialog);

                }
                break;

                case 'r': {
                    // reels
                    Instagram_Video.downloadVideo(context,link,"Reel",progressDialog);

                }

                break;

            }

        }


    }
}
