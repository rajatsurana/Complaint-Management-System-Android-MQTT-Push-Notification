package com.rajat.compmsys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Lenovo on 3/26/2016.
 */
public class imagedialog extends Activity {

    private ImageView mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogboxlayout);



        mDialog = (ImageView)findViewById(R.id.your_image);
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
