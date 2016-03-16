package com.rajat.compmsys.mqtt;

/**
 * Created by Rajat on 29-02-2016.
 */

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.rajat.compmsys.R;
public class MQTTService extends Service {

    private static final String TAG = "MQTTService";
    private static boolean hasWifi = false;
    private static boolean hasMmobile = false;
    private Thread thread;
    private ConnectivityManager mConnMan;
    private volatile IMqttAsyncClient mqttClient;
    private String deviceId;



    public class MQTTBinder extends Binder {
        public MQTTService getService(){
            return MQTTService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate()");
        IntentFilter intentf = new IntentFilter();
        setClientID();
        intentf.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        mConnMan = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        registerReceiver(vc, intentf);

    }


    BroadcastReceiver vc=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, intent.hasExtra("FAILOVER_CONNECTION") + " 1:2 " + intent.hasExtra(ConnectivityManager.EXTRA_IS_FAILOVER) + " :3: " + intent.hasExtra(ConnectivityManager.EXTRA_NETWORK_INFO));
            boolean failure= intent.getBooleanExtra("FAILOVER_CONNECTION",false);
            boolean noConnect= intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
           Log.v(TAG, failure + " failure : noConnect " + noConnect);
        NetworkInfo nw = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            Log.v(TAG,nw.getTypeName()+": nw.getTypeName(): isconnected: "+ nw.isConnected());
            Log.v(TAG, "onRecieve()");
            IMqttToken token;
            boolean hasConnectivity = false;
            boolean hasChanged = false;
            //intent.ge
            NetworkInfo infos[] = mConnMan.getAllNetworkInfo();

            for (int i = 0; i < infos.length; i++){
                if (infos[i].getTypeName().equalsIgnoreCase("MOBILE")){
                    if((infos[i].isConnected() != hasMmobile)){
                        hasChanged = true;
                        hasMmobile = infos[i].isConnected();
                    }
                    Log.d(TAG, infos[i].getTypeName() + " is " + infos[i].isConnected());
                } else if ( infos[i].getTypeName().equalsIgnoreCase("WIFI") ){
                    if((infos[i].isConnected() != hasWifi)){
                        hasChanged = true;
                        hasWifi = infos[i].isConnected();
                    }
                    Log.d(TAG, infos[i].getTypeName() + " is " + infos[i].isConnected());
                }
            }

            hasConnectivity = hasMmobile || hasWifi;
            Log.v(TAG, "hasConn: " + hasConnectivity + " hasChange: " + hasChanged + " - "+(mqttClient == null || !mqttClient.isConnected()));
            if (!hasConnectivity && !hasChanged && (mqttClient == null || !mqttClient.isConnected())) {//hasConnectivity && hasChanged--as hotspot is used to connect
                doConnect();
            } else if (!hasConnectivity && mqttClient != null && mqttClient.isConnected()) {
                Log.d(TAG, "doDisconnect()");
                try {
                    token = mqttClient.disconnect();
                    token.waitForCompletion(1000);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }

    };
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");
        android.os.Debug.waitForDebugger();
        super.onConfigurationChanged(newConfig);

    }

    private void setClientID(){
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        deviceId = wInfo.getMacAddress();
        if(deviceId == null){
            deviceId = MqttAsyncClient.generateClientId();
        }
    }

    private void doConnect(){
        Log.d(TAG, "doConnect()");
        IMqttToken token ;//= new MqttDeliveryToken();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setKeepAliveInterval(20 * 60);

        try {
            mqttClient = new MqttAsyncClient("tcp://192.168.43.200:1883", deviceId, new MemoryPersistence());
            token = mqttClient.connect(options);
            //Log.i("rajat", "subscribe now");

            token.waitForCompletion(3500);
            mqttClient.setCallback(new MqttEventCallback());
            token = mqttClient.subscribe("presence", 1);
            token.waitForCompletion(5000);
        } catch (MqttSecurityException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            switch (e.getReasonCode()) {
                case MqttException.REASON_CODE_BROKER_UNAVAILABLE:
                case MqttException.REASON_CODE_CLIENT_TIMEOUT:
                case MqttException.REASON_CODE_CONNECTION_LOST:
                case MqttException.REASON_CODE_SERVER_CONNECT_ERROR:
                    Log.v(TAG, "c" +e.getMessage());
                    e.printStackTrace();
                    break;
                case MqttException.REASON_CODE_FAILED_AUTHENTICATION:
                    Intent i = new Intent("RAISEALLARM");
                    i.putExtra("ALLARM", e);
                    Log.e(TAG, "b"+ e.getMessage());
                    break;
                default:
                    Log.e(TAG, "a" + e.getMessage());
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand()");
        //onNetworkChange();
        return START_STICKY;
    }

    private class MqttEventCallback implements MqttCallback {
        int id =0;
        @Override
        public void connectionLost(Throwable arg0) {


        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        @SuppressLint("NewApi")
        public void messageArrived(String topic, final MqttMessage msg) throws Exception {
            Log.i(TAG, "Message arrived from topic" + topic);
            Handler h = new Handler(getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
                    //Intent launchA = new Intent(MQTTService.this, FullScreenActivityTest.class);
                    //launchA.putExtra("message", msg.getPayload());
                    //TODO write somethinkg that has some sense
                    if(Build.VERSION.SDK_INT >= 11){
                        //launchA.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    } /*else {
        		        launchA.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        		    }*/
                    //startActivity(launchA);
                    String message=new String(msg.getPayload());

                    sendNotification("Alert",message,"MQTT",id++);
                    Toast.makeText(getApplicationContext(), "MQTT Message:\n" + new String(msg.getPayload()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public String getThread(){
        return Long.valueOf(thread.getId()).toString();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind called");
        return null;
    }
    private void sendNotification(String alert,String message,String title,int id) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification noti= notificationBuilder.build();
        noti.tickerText=alert;
        notificationManager.notify(id, noti);
    }

}