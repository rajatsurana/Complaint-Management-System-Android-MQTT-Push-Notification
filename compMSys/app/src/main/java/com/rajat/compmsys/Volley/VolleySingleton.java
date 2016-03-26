package com.rajat.compmsys.Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Rajat on 6/16/2015.
 */
public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

//constructor
    public VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }
// get instance of volleysingleton
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }
//get request queue to pass request into
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            Log.i("rajat", "getRequestQueue");
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
//request is added in request queue
    public <T> void addToRequestQueue(Request<T> req) {
        Log.i("rajat", "addToRequestQueue");
        getRequestQueue().add(req);
    }
}
