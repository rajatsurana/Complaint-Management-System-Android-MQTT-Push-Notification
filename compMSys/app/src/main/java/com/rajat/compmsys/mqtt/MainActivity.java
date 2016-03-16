package com.rajat.compmsys.mqtt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.rajat.compmsys.R;
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(this, MQTTService.class);
		startService(intent);
		Button connect = (Button)findViewById(R.id.connectButton);
		connect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText serverEditText = (EditText)findViewById(R.id.serverText);
				Button connectButton = (Button)findViewById(R.id.connectButton);
				Button sendButton = (Button)findViewById(R.id.sendButton);
				TextView resultText = (TextView)findViewById(R.id.resultTextView);
				String server = serverEditText.getText().toString();
				if (MQTTUtils.connect(server)) {
					connectButton.setEnabled(false);
					serverEditText.setEnabled(false);
					sendButton.setEnabled(true);
					resultText.setText("Connected to the server.");
					MQTTUtils.sub("presence", 0);
				} else {
					resultText.setText("Error connecting the server.");
				}
			}
			
		});
		
		Button send = (Button)findViewById(R.id.sendButton);
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
				LocationListener locationListener = new LocationListener() {

					@Override
					public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
					}

					@Override
					public void onProviderEnabled(String arg0) {
					}

					@Override
					public void onProviderDisabled(String arg0) {
					}

					@Override
					public void onLocationChanged(Location arg0) {
					}
				};
				TextView resultText = (TextView) findViewById(R.id.resultTextView);
				String provider = LocationManager.GPS_PROVIDER;
				if (!lm.isProviderEnabled(provider)) {
					provider = LocationManager.NETWORK_PROVIDER;
				}
				if (!lm.isProviderEnabled(provider)) {
					resultText.setText("Providers disabled.");
				} else {
					lm.requestSingleUpdate(provider, locationListener, Looper.getMainLooper());
					Location location = lm.getLastKnownLocation(provider);
					Double lon = 0.0;
					Double lat = 0.0;
					try {
						lon = location.getLongitude();
						lat = location.getLatitude();
					} catch (Exception e) {
						e.printStackTrace();
					}
					String topic = "presence";
					String payload = String.format("{lon: %f, lat: %f}", lon, lat);
					MQTTUtils.pub(topic, payload);
					resultText.setText(String.format("Topic: %s, message: %s", topic, payload));

				}
			}
		});
		//if(){}

		//MQTTUtils.sub("presence", 1);
		//MQTTUtils.sub("presence", 2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

}
