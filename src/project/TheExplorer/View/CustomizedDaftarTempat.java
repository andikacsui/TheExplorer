package project.TheExplorer.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import project.TheExplorer.Controller.GPSTracker;
import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Controller.TempatHelper;
import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Tempat;

public class CustomizedDaftarTempat extends Activity implements
		SensorEventListener {
	// All static variables
	static final String URL = "http://api.androidhive.info/music/music.xml";
	// XML node keys
	static final String KEY_ID = "id";
	static final String KEY_NAMA = "nama";
	static final String KEY_DESKRIPSI = "deskripsi";
	static final String KEY_FOTO = "foto";
	static final String KEY_FOTO_PATH = "fotopath";
	static final String KEY_STATUS = "status";
	static int NomorMisi, MisiID;
	static String Misi = "";
	static String namaTempat = "";
	static int[] imgArr = new int[100];
	ArrayList<Tempat> daftarTempat;
	HashMap<String, String> map;

	// sensor
	private SensorManager sensorManager;
	// private boolean color = false;
	private View view;
	private long lastUpdate;
	GPSTracker gps;

	ListView list;
	ListTempatAdapter adapter;
	Context context;
	CustomizedDaftarMisi daftarMisi;
	Location locDb;
	Location locNow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_tempat);
		context = this;
		daftarMisi = new CustomizedDaftarMisi();

		Intent prev = getIntent();
		NomorMisi = prev.getIntExtra("IDMisi", 0);
		ArrayList<HashMap<String, String>> tempatList = new ArrayList<HashMap<String, String>>();
		daftarMisi = new CustomizedDaftarMisi();

		daftarTempat = TempatHelper.GetListTempatByMisi(context, 1);

		if (daftarTempat != null) {
			daftarTempat.clear();
		}

		try {

			// daftarMisi = new CustomizedDaftarMisi();
			// IDMisi = Integer.parseInt(daftarMisi.KEY_ID_MISI);

			// log.d("hmmm", "haaah lala" + misiid);
			daftarTempat = TempatHelper.GetListTempatByMisi(context,
					ListViewSavedMission.idMisi);

			// looping through all song nodes &lt;song&gt;
			for (int i = 0; i < daftarTempat.size(); i++) {

				// creating new HashMap
				map = new HashMap<String, String>();

				// adding each child node to HashMap key => value
				if (daftarTempat.get(i).getStatus() == 1) {
					map.put(KEY_STATUS, "VISITED");
				} else {
					map.put(KEY_STATUS, "UNVISITED");
				}
				map.put(KEY_NAMA, daftarTempat.get(i).getNama());
				map.put(KEY_DESKRIPSI, daftarTempat.get(i).getDeskripsi());
				map.put(KEY_FOTO, daftarTempat.get(i).getFoto());
				// map.put(KEY_ID, ""+daftarTempat.get(i).getID());
				// add foto
				String namaGambar = daftarTempat.get(i).getFoto();
				Resources res = getResources();
				int resId = res.getIdentifier(namaGambar, "drawable",
						getPackageName());
				map.put(KEY_FOTO_PATH, "" + resId);
				// adding HashList to ArrayList
				tempatList.add(map);
			}
<<<<<<< HEAD
=======
			daftarTempat.clear();
>>>>>>> b249cc5bf9b9efd0b9381ba3bf38ca8c215f47f8
			list = (ListView) findViewById(R.id.list_tempat_layout);

			// Getting adapter by passing xml data ArrayList
			adapter = new ListTempatAdapter(this, tempatList);
			list.setAdapter(adapter);



			// Click event for single list row
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

				}
			});
		} catch (Exception e) {
		}

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

				// \n is for new line
				// Toast.makeText(getApplicationContext(),
				// "" + locationMatching(), Toast.LENGTH_LONG).show();

				locationMatching();

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

	private void locationMatching() {
		float distance = -1;
		boolean match = false;
		int idTempat = 0;

		locDb = new Location("");
		locNow = new Location("");
		locNow.setLatitude(gps.getLatitude());
		locNow.setLongitude(gps.getLongitude());

		for (int i = 0; i < daftarTempat.size(); i++) {
			locDb.setLatitude(daftarTempat.get(i).getLatitude());
			locDb.setLongitude(daftarTempat.get(i).getLongitude());
			distance = locNow.distanceTo(locDb);
			if (distance <= 1000f) {
				match = true;
				namaTempat = daftarTempat.get(i).getNama();
				idTempat = daftarTempat.get(i).getID();
				break;
			}
		}

		if (match == true) {
			
			TempatHelper.CheckInTempat(context, idTempat);
			//Toast.makeText(getApplicationContext(),
			//		"You have arrived in " + namaTempat, Toast.LENGTH_LONG)
			//		.show();
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set title
			alertDialogBuilder.setTitle("Join Mission");

			// set dialog message
			alertDialogBuilder
					.setMessage("You have arrived in " + namaTempat + "\nDo you want to" +
							" share it in twitter?")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent nextScreen = new Intent(
											getApplicationContext(),
											ShareTwitter.class);
									startActivity(nextScreen);
									
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, just close
									// the dialog box and do nothing
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

		} else {
			Toast.makeText(getApplicationContext(), "No match location",
					Toast.LENGTH_LONG).show();
		}
	}
}
