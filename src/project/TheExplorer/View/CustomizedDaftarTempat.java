package project.TheExplorer.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Controller.TempatHelper;
import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Tempat;

public class CustomizedDaftarTempat extends Activity {
	// All static variables
	static final String URL = "http://api.androidhive.info/music/music.xml";
	// XML node keys
	static final String KEY_IDX = "id";
	static final String KEY_NAMAX = "nama";
	static final String KEY_DESKRIPSIX = "deskripsi";
	static final String KEY_FOTOX = "foto";
	static final String KEY_STATUSX = "status";
	ArrayList<Tempat> daftarTempat;
	HashMap<String, String> map;

	ListView list;
	ListTempatAdapter adapter;
	Context context;
	CustomizedDaftarMisi daftarMisi;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_tempat);
		context = this;
		ArrayList<HashMap<String, String>> missionList = new ArrayList<HashMap<String, String>>();
		daftarMisi = new CustomizedDaftarMisi();

		try {

			//daftarMisi = new CustomizedDaftarMisi();
			//IDMisi = Integer.parseInt(daftarMisi.KEY_ID_MISI);
			
			Intent inten = getIntent();
			daftarTempat = TempatHelper.GetListTempatByMisi(context, 0);
			daftarTempat.clear();
			daftarTempat = TempatHelper.GetListTempatByMisi(context, inten.getIntExtra("mission_id", 0));
			
			// looping through all song nodes &lt;song&gt;
			for (int i = 0; i < daftarTempat.size(); i++) {

				// creating new HashMap
				map = new HashMap<String, String>();
				// adding each child node to HashMap key => value
				if (daftarTempat.get(i).getStatus() == 1) {
					map.put(KEY_STATUSX, "VISITED");
				} else {
					map.put(KEY_STATUSX, "UNVISITED");
				}
				map.put(KEY_NAMAX, daftarTempat.get(i).getNama());
				map.put(KEY_DESKRIPSIX, daftarTempat.get(i).getDeskripsi());
				map.put(KEY_FOTOX, daftarTempat.get(i).getFoto());

				// adding HashList to ArrayList
				missionList.add(map);
			}

			list = (ListView) findViewById(R.id.list_tempat_layout);

			// Getting adapter by passing xml data ArrayList
			adapter = new ListTempatAdapter(this, missionList);
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
}
