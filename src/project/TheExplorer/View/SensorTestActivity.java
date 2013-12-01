package project.TheExplorer.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import project.TheExplorer.Controller.GPSTracker;
import project.TheExplorer.Controller.R;

public class SensorTestActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	// private boolean color = false;
	private View view;
	private long lastUpdate;
	GPSTracker gps;
	Context context;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*
		 * requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 * 
		 * super.onCreate(savedInstanceState);
		 * setContentView(R.layout.activity_sensor); view =
		 * findViewById(R.id.textView); view.setBackgroundColor(Color.GREEN);
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		view = findViewById(R.id.textView);
		context = this;

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

	}

	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		long actualTime = System.currentTimeMillis();
		if (accelationSquareRoot >= 2) //
		{
			if (actualTime - lastUpdate < 200) {
				return;
			}
			lastUpdate = actualTime;
			/*
			 * Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
			 * .show(); if (color) { view.setBackgroundColor(Color.GREEN);
			 * 
			 * } else { view.setBackgroundColor(Color.RED); } color = !color;
			 */

			// create class object
			gps = new GPSTracker(context);

			// check if GPS enabled
			if (gps.canGetLocation()) {

				double latitude = gps.getLatitude();
				double longitude = gps.getLongitude();

				// \n is for new line
				Toast.makeText(
						getApplicationContext(),
						"Your Location is - \nLat: " + latitude + "\nLong: "
								+ longitude, Toast.LENGTH_LONG).show();
			} else {
				// can't get location
				// GPS or Network is not enabled
				// Ask user to enable GPS/network in settings
				gps.showSettingsAlert();
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}