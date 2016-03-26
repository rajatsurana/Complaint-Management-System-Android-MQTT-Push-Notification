package com.rajat.compmsys.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

//import com.rajat.moodle.Objects.CourseThreadObject;
import com.rajat.compmsys.Tools.CheckNetwork;
import com.rajat.compmsys.Tools.Tools;

/**
 * Created by Lenovo on 2/21/2016.
 */
public class VolleyClick {

    public static void onLoginClick(String email,String password,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200:3000/api/login";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.makeLoginCall(URL, context,email,password);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void uploadImageClick(Bitmap bitmap,String userId,String complaintId,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200:3000/api/upload";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.uploadImageCall(URL, context, bitmap, userId, complaintId);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void getBitmapClick(String userId,String complaintId,ImageView iv,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200:3000/api/downloads/"+userId+"/"+complaintId;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.getBitmapFromUrl(URL, context, iv);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
}
