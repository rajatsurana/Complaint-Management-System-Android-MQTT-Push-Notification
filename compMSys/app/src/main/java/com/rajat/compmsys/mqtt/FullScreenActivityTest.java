package com.rajat.compmsys.mqtt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.rajat.compmsys.R;
/**
 * Created by Rajat on 29-02-2016.
 */
public class FullScreenActivityTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = (TextView)findViewById(R.id.dataText);
        Intent i = getIntent();
        byte[] b=i.getByteArrayExtra("message");
        tv.setText(new String(b));
    }


}
