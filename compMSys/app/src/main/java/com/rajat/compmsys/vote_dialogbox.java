package com.rajat.compmsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.Volley.CallVolley;
import com.rajat.compmsys.Volley.VolleyClick;

/**
 * Created by Lenovo on 3/27/2016.
 */
public class vote_dialogbox extends Activity {

    private ImageView image,delete;
    public static ImageView upvote;
    public static TextView text;
    ComplaintObject complaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogboxlayout_vote);
        Intent i=getIntent();
        complaint=i.getParcelableExtra("complaint");
        image = (ImageView)findViewById(R.id.your_image_1);
        VolleyClick.getBitmapClick(complaint.getUser_id(),complaint.getComplaint_id(),image,getApplicationContext());

        delete=(ImageView)findViewById(R.id.imageView2);
        upvote=(ImageView) findViewById(R.id.imageButton);
        text=(TextView) findViewById(R.id.view2);
        //mDialog.setImageDrawable();
        delete.setClickable(true);
        upvote.setClickable(true);


        //finish the activity (dismiss the image dialog) if the user clicks
        //anywhere on the image
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyClick.voteClick(complaint.getComplaint_id(),complaint.getUser_id(),true,getApplicationContext());

            }
        });

    }
}