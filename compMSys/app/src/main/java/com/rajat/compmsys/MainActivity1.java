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
import com.rajat.compmsys.Volley.VolleyClick;
import com.rajat.compmsys.Volley.VolleySingleton;


public class MainActivity1 extends AppCompatActivity {
    //public static final String MyPREFERENCES = "MyPrefs" ;
    //public static SharedPreferences sharedpreferences;
    //public static SharedPreferences.Editor editor ;
    Button getImg,loginButton;
    Button sendImg;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_activity);
        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getImg=(Button)findViewById(R.id.getImg);
        sendImg=(Button)findViewById(R.id.sendImg);
        loginButton=(Button)findViewById(R.id.loginButton);
        iv=(ImageView)findViewById(R.id.imgView);

        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId="5";
                String complaintId="10";
                VolleyClick.getBitmapClick(userId, complaintId, iv,MainActivity1.this);
            }
        });
        sendImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId="53";
                String complaintId="32";
                Bitmap b=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                //b=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/compMSys/1/3.png");
                iv.setImageBitmap(b);
                //Log.i("rajat",Environment.getExternalStorageDirectory().toString());
                VolleyClick.uploadImageClick(b, userId, complaintId, MainActivity1.this);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email="tt1130905";
                String password = "Arpit";
                VolleyClick.onLoginClick(email, password, MainActivity1.this);
            }
        });
    }


}
