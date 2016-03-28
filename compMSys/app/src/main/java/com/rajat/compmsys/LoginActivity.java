package com.rajat.compmsys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rajat.compmsys.Volley.VolleyClick;


public class LoginActivity extends AppCompatActivity {
    EditText user_id,passowrd;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id=(EditText) findViewById(R.id.emailId);
        //userid=user_id.getText().toString();
        passowrd=(EditText) findViewById(R.id.passwordId);
      //  pass=passowrd.getText().toString();
        signin = (Button) findViewById(R.id.email_sign_in_button);
        signin.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                Boolean error=false;
                if(user_id.getText().toString().trim().length()==0) {
                    user_id.setError("invalid input");
                    error=true;
                }
                if(passowrd.getText().toString().trim().length()==0){
                    passowrd.setError("invalid input");
                    error=true;
                }
                if(!error){
                  //  Toast.makeText(getApplicationContext(),"rajat"+"login" + user_id.getText().toString() + passowrd.getText().toString(),Toast.LENGTH_LONG).show();
                    VolleyClick.onLoginClick(user_id.getText().toString(), passowrd.getText().toString(), LoginActivity.this);
                   /* Intent openH = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(openH);*/}

            }
        });
    }

}

