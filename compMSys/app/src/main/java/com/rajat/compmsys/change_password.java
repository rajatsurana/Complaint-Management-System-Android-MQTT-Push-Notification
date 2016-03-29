package com.rajat.compmsys;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rajat.compmsys.Volley.VolleyClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class change_password extends Fragment {

    View v;
    EditText oldp,newp,rnewp;
    String old_pass,new_pass, renew_pass;
    Button change;
    public change_password() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_change_password, container, false);
        oldp=(EditText) v.findViewById(R.id.editText_oldp);
        newp = (EditText) v.findViewById(R.id.editText_newp);
        rnewp = (EditText) v.findViewById(R.id.editText_rnewp);

        //Convert to strings

        old_pass = oldp.getText().toString();
        new_pass = newp.getText().toString();
        renew_pass = rnewp.getText().toString();

        // Button
        change = (Button) v.findViewById(R.id.button_change_pass);
        change.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Boolean error=false;
                if(oldp.getText().toString().trim().length()== 0) {
                    Toast.makeText(getContext(),"Old password is invalid",Toast.LENGTH_LONG).show();
                    error=true;
                }
                if((!newp.getText().toString().equals(rnewp.getText().toString()) )){
                    Toast.makeText(getContext(),"New passwords do not match",Toast.LENGTH_LONG).show();
                    error=true;
                }
                if(newp.getText().toString().equals(rnewp.getText().toString()) && newp.getText().toString().trim().length()==0) {
                    Toast.makeText(getContext(),"Please enter new password",Toast.LENGTH_LONG).show();
                    error = true;
                }
                if(!error){
                    //  Toast.makeText(getApplicationContext(),"rajat"+"login" + user_id.getText().toString() + passowrd.getText().toString(),Toast.LENGTH_LONG).show();
                   VolleyClick.changePasswordClick(MainActivity.sharedpreferences.getString("email",""),oldp.getText().toString(),newp.getText().toString(), getContext());
                   /* Intent openH = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(openH);*/}

            }
        });



        return v;
    }

}
