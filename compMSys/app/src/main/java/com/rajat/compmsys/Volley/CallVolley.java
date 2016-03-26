package com.rajat.compmsys.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.rajat.compmsys.MainActivity;
import com.rajat.compmsys.Tools.Tools;

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
        public static void makeLoginCall(String url, final Context context)
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
                                        params.put("email","");
                                        params.put("password","");
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
                                        MainActivity.editor.putString("TOKEN", "");
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

        public static void afterLoginCall(String url, final Context context, final int API_NUMBER){
                //pDialog=  Tools.showProgressBar(context);

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                {
                        // if a reponse is recieved after sending request
                        @Override
                        public void onResponse(String response)
                        {
                                try
                                {
                                        switch (API_NUMBER){
                                                case 1:
                                                        //JSONParser.NotificationApiJsonParser(response, context);
                                                        break;

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
                                mHeaders.put("x-access-token", MainActivity.sharedpreferences.getString("token", ""));//MainActivity.sharedpreferences.getString("Set-Cookie",""));
                                return mHeaders;
                        }
                        @Override
                        public String getBodyContentType() {
                                return "application/x-www-form-urlencoded; charset=UTF-8";
                        }
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                //params.put("id",productId);
                                //params.put("discount",discount+"");
                                return params;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }

}