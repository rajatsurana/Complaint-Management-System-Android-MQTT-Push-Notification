package com.rajat.compmsys.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import com.rajat.compmsys.MainActivity;
import com.rajat.compmsys.Tools.Tools;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CallVolley {
        //dialog box appears showing progress
        private static ProgressDialog pDialog;
        //TimeOut and maximum number of tries
        private static void setCustomRetryPolicy(StringRequest jsonObjReq) {
                Log.i("rajat", "setCustomRetryPolicy");
                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }

        // method that will send request  to server and get a response back
        public static void makeLoginCall(String url, final Context context,final String email,final String password)
        {
              //  pDialog=  Tools.showProgressBar(context);

                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                        {
                        // if a reponse is recieved after sending request
                                @Override
                                public void onResponse(String response)
                                {
                                        try
                                        {

                                                Log.i("rajat", " onResponseActive " + response);
                                                JSONParser.LoginApiJsonParser(response, context);
                                                //pDialog.dismiss();
                                        }
                                        catch (Exception localException)
                                        {
                                                Log.i("rajat"," onResponseException "+localException.getMessage());
                                                localException.printStackTrace();
                                        }
                                }
                        }
                                , new Response.ErrorListener()
                        {
                                //if error occurs
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {
                                        Log.i("rajat", "onErrorResponse" + error.toString());
                                           //pDialog.dismiss();


                                }
                        }){
                                @Override
                                public String getBodyContentType() {
                                        return "application/x-www-form-urlencoded; charset=UTF-8";
                                }
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("email",email);
                                        params.put("password",password);
                                        /*
                                        JSONArray productIdsArr=new JSONArray();
                                        JSONArray quantitiesArr= new JSONArray();
                                        for(int x=0;x<productIds.length;x++){
                                                productIdsArr.put(productIds[x]);
                                                quantitiesArr.put(quantities[x]);
                                        }
                                        params.put("customerId",customerId);
                                        params.put("productIds",productIdsArr.toString());
                                        params.put("quantityVals",quantitiesArr.toString());
                                        */
                                        return params;
                                }
                        };

                //how many times to try and for how much duration
                        setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                        VolleySingleton.getInstance(context).addToRequestQueue(request);
                }
                // method will give message to user depending on respons from server


        public static void makeLogoutCall(String url, final Context context)
        {
                //pDialog=  Tools.showProgressBar(context);

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                {
                        // if a reponse is recieved after sending request
                        @Override
                        public void onResponse(String response)
                        {
                                try
                                {
                                        Log.i("rajat", " onResponseActive " + response);
                                        //LoginApiJsonParser(response, context);
                                        MainActivity.editor = MainActivity.sharedpreferences.edit();
                                        MainActivity.editor.putString("token", "");
                                        MainActivity.editor.apply();

                                       // pDialog.dismiss();
                                        //Intent openH = new Intent (context, Login.class);
                                        //context.startActivity(openH);
                                }
                                catch (Exception localException)
                                {
                                        Log.i("rajat"," onResponseException "+localException.getMessage());
                                        localException.printStackTrace();
                                }
                        }
                }
                        , new Response.ErrorListener()
                {
                        //if error occurs
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                                Log.i("rajat", "onErrorResponse" + error.toString());
                                //pDialog.dismiss();


                        }
                });

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }

        public static void uploadImageCall(String url, final Context context,final Bitmap bitmap,final String userId,final String complaintId){
                //pDialog=  Tools.showProgressBar(context);
                final Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                {
                        // if a reponse is recieved after sending request
                        @Override
                        public void onResponse(String response)
                        {
                                try
                                {
                                        JSONObject resultJson = new JSONObject(response);
                                        if(resultJson.has("response")){
                                                Toast.makeText(context,""+resultJson.getString("response"),Toast.LENGTH_LONG).show();
                                        }

                                        Log.i("rajat", " onResponseActive " + response);

                                        //pDialog.dismiss();
                                }
                                catch (Exception localException)
                                {
                                        Log.i("rajat"," onResponseException "+localException.getMessage());
                                        localException.printStackTrace();
                                }
                        }
                }
                        , new Response.ErrorListener()
                {
                        //if error occurs
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                                error.printStackTrace();
                                Log.i("rajat", "onErrorResponse" + error.toString());
                                //pDialog.dismiss();
                                Tools.showAlertDialog(error.toString(), context);
                        }
                }
                ){

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                                //Log.i("size in getHeader: ",myHeaders.size()+"");
                                Map<String, String> mHeaders=new HashMap<String,String>();//myHeaders;
                                mHeaders.put("x-access-token", MainActivity.sharedpreferences.getString("token", ""));
                                //context.getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE).getString("token","")
                                return mHeaders;
                        }
                        @Override
                        public String getBodyContentType() {
                                return "application/x-www-form-urlencoded; charset=UTF-8";
                        }
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                //Bitmap b=null;
                                String imgStr= getStringImage(bitmap2);
                                params.put("imageFile",imgStr);
                                params.put("userId",userId);
                                params.put("complaintId",complaintId);
                                return params;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }


        public  static void getBitmapFromUrl(String url,final Context con,final ImageView iv){


                ImageRequest request = new ImageRequest(url,
                        new Response.Listener<Bitmap>() {

                                @Override
                                public void onResponse(Bitmap bitmap) {
                                        //mImageView.setImageBitmap(bitmap);
                                        //iv.setImageBitmap(bitmap);
                                        //byte[] img;

                                        if(bitmap!=null){
                                                Bitmap bit2 = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
                                                //ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                                //bit2.compress(Bitmap.CompressFormat.PNG, 100, bos);
                                                //img = bos.toByteArray();
                                                iv.setImageBitmap(bit2);

                                        }else{
                                                Toast.makeText(con,"Image not found",Toast.LENGTH_SHORT).show();
                                        }

                                }
                        }, 0, 0, null,
                        new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                        //Toast.makeText(con,"",Toast.LENGTH_SHORT).show();

                                        //Log.i("rajat", error.getLocalizedMessage());
                                        //mImageView.setImageResource(R.drawable.image_load_error);
                                }
                        }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> mHeaders=new HashMap<String,String>();//myHeaders;
                                mHeaders.put("x-access-token", MainActivity.sharedpreferences.getString("token", ""));
                                return mHeaders;
                        }
                };
                // Access the RequestQueue through your singleton class.
                VolleySingleton.getInstance(con).addToRequestQueue(request);


        }
        private static void SaveImage(Bitmap finalBitmap,String filename) {

                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/compMSys");
                myDir.mkdirs();
                //Credentials cred = new Credentials();
                //cred= databaseHelper.getCredentials();
                //Random generator = new Random();
                //int n = 10000;
                // n = generator.nextInt(n);
                String fname = filename;//cred.getParentID()+cred.getEmpID()+".png";
                File file = new File (myDir, fname);
                if (file.exists ()) {file.delete ();}
                try {
                        FileOutputStream out = new FileOutputStream(file);
                        finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();

                } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("rajat", e.getLocalizedMessage());
                }
        }

        private static String getStringImage(Bitmap bmp){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                return encodedImage;
        }
}