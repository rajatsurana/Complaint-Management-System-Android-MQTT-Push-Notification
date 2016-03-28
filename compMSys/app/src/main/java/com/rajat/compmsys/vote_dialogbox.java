package com.rajat.compmsys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lenovo on 3/27/2016.
 */
public class vote_dialogbox extends Activity {

    private ImageView image,delete,upvote;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogboxlayout_vote);



        image = (ImageView)findViewById(R.id.your_image_1);
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
                upvote.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_up_black_48dp));
                text.setText("upvoted");
            }
        });

    }
}