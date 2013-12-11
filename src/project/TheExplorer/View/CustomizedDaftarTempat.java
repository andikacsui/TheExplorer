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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class CustomizedDaftarTempat extends Activity {
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
					ListViewActivity.idMisi);

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
			daftarTempat.clear();
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

	}
	public void showAlertbox(String erroMessage) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle("Error Message");

		// Setting Dialog Message
		alertDialog.setMessage(erroMessage);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Write your code here to execute after dialog closed

			}
		});

		// Showing Alert Message
		alertDialog.show();

	}

	public static boolean notEmpty(ArrayList<Misi> s) {
		return (s != null && s.size() > 0);
	}

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.new_mission:
			// Single menu item is selected do something
			// Ex: launching new activity/screen or show alert message
			ArrayList<Misi> ListMisi = MisiHelper.GetNotSavedMission(context);
			if (notEmpty(ListMisi)) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ListViewActivity.class);
				startActivity(nextScreen);
			} else {
				showAlertbox("All missions have been saved");
			}
			return true;

		case R.id.saved_mission:
			ArrayList<Misi> ListMisi2 = MisiHelper.GetSavedMission(context);
			if (notEmpty(ListMisi2)) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ListViewSavedMission.class);
				startActivity(nextScreen);
			} else {
				showAlertbox("You don't have saved mission");
			}
			return true;

		case R.id.achievement:
			Intent nextScreen = new Intent(getApplicationContext(),
					TabProfile.class);
			startActivity(nextScreen);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
