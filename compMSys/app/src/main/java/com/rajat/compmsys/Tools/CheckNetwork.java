package com.rajat.compmsys.Tools;

/**
 * Created by Rajat on 14-01-2016.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork {

    Context context;

    public CheckNetwork(Context c)
    {
        this.context=c;
    }

    // check whether wifi of cell data is available or not
    //requires access_network permissions during installation
    public  boolean checkNetwork() {
        boolean wifiDataAvailable = false;
        boolean mobileDataAvailable = false;
        try {	ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
            for (NetworkInfo netInfo : networkInfo) {
                if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                    if (netInfo.isConnected())
                        wifiDataAvailable = true;
                if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (netInfo.isConnected())
                        mobileDataAvailable = true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wifiDataAvailable || mobileDataAvailable;
    }
}
