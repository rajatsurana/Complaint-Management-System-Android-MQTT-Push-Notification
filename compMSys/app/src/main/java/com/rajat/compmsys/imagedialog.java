package com.rajat.compmsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.Volley.VolleyClick;

/**
 * Created by Lenovo on 3/26/2016.
 */
public class imagedialog extends Activity {

    private ImageView mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogboxlayout);
        Intent i=getIntent();
        ComplaintObject complaint=i.getParcelableExtra("complaint");



        mDialog = (ImageView)findViewById(R.id.your_image);
        VolleyClick.getBitmapClick(complaint.getUser_id(),complaint.getComplaint_id(),mDialog,getApplicationContext());
        //mDialog.setImageDrawable();
        mDialog.setClickable(true);


        //finish the activity (dismiss the image dialog) if the user clicks
        //anywhere on the image
        mDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
