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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;

public class CustomizedDaftarMisi extends Activity {
	// XML node keys
	static final String KEY_ID = "id";
	static final String KEY_ID_MISI = "idmisi";
	static final String KEY_NAMA = "nama";
	static final String KEY_LOKASI = "lokasi";
	static final String KEY_DESKRIPSI = "deskripsi";
	static final String KEY_FOTO = "foto";
	ArrayList<Misi> daftarMisi;
	static String id_misi;
	ListView list;
	ListMisiAdapter adapter;
	Context context;
	EditText IDMisi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_misi);
		context = this;

		if (daftarMisi != null) {
			daftarMisi.clear();
		}

		ArrayList<HashMap<String, String>> missionList = new ArrayList<HashMap<String, String>>();
		try {

			daftarMisi = MisiHelper.GetListMisi(context);

			// looping through all song nodes &lt;song&gt;
			for (int i = 0; i < daftarMisi.size(); i++) {

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				// adding each child node to HashMap key => value
				// Log.d("ID atas", String.valueOf(daftarMisi.get(i).getID()));
				// Log.d("Nama atas",
				// String.valueOf(daftarMisi.get(i).getNama()));
				map.put(KEY_NAMA, daftarMisi.get(i).getNama());
				map.put(KEY_LOKASI, daftarMisi.get(i).getLokasi());
				map.put(KEY_DESKRIPSI, daftarMisi.get(i).getDeskripsi());
				map.put(KEY_FOTO, daftarMisi.get(i).getFoto());
				map.put(KEY_ID_MISI, daftarMisi.get(i).getID() + "");
				// Log.d("ID ", String.valueOf(daftarMisi.get(i).getID()));
				// Log.d("Nama ", String.valueOf(daftarMisi.get(i).getNama()));
				// adding HashList to ArrayList
				missionList.add(map);
			}

			list = (ListView) findViewById(R.id.list_misi_layout);

			// Getting adapter by passing xml data ArrayList
			adapter = new ListMisiAdapter(this, missionList);
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

	public void TextViewDetailMisi_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		TextView tvValue = (TextView) findViewById(R.id.TextViewDetails);
		id_misi = ""+tvValue.getTag();
		nextScreen.putExtra("mission_id", id_misi);
		startActivity(nextScreen);
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
