package com.rajat.compmsys;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rajat.compmsys.Volley.CallVolley;
import com.rajat.compmsys.Volley.VolleySingleton;


public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor ;
    Button getImg,loginButton;
    Button sendImg;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_activity);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getImg=(Button)findViewById(R.id.getImg);
        sendImg=(Button)findViewById(R.id.sendImg);
        loginButton=(Button)findViewById(R.id.loginButton);
        iv=(ImageView)findViewById(R.id.imgView);

        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId="5";
                String complaintId="10";
                String url="http://192.168.43.200:3000/api/downloads/"+userId+"/"+complaintId;
                CallVolley.getBitmapFromUrl(url,MainActivity.this,iv);
            }
        });
        sendImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId="1";
                String complaintId="3";
                String url="http://192.168.43.200:3000/api/upload";//"+userId+"/"+complaintId;
                //CallVolley.setUploadImageCall(url,MainActivity.this,userId+"/"+complaintId);
                Bitmap b=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                //b=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/compMSys/1/3.png");
                iv.setImageBitmap(b);
                //Log.i("rajat",Environment.getExternalStorageDirectory().toString());
                CallVolley.uploadImageCall(url, MainActivity.this, b, "50", "13");
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
                String URL = "http://192.168.43.200:3000/api/login";
                String email="rajat.surana@gmail.com";
                String password = "RAJAT94606";
                CallVolley.makeLoginCall(URL, MainActivity.this, email, password);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
