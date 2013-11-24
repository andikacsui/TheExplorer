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
	static final String KEY_ID = "id";
	static final String KEY_NAMA = "nama";
	static final String KEY_DESKRIPSI = "deskripsi";
	static final String KEY_FOTO = "foto";
	static final String KEY_STATUS = "status";
	static int NomorMisi;
	ArrayList<Tempat> daftarTempat;

	ListView list;
	ListTempatAdapter adapter;
	Context context;
	CustomizedDaftarMisi daftarMisi;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_tempat);
		context = this;
<<<<<<< HEAD
=======

		ArrayList<HashMap<String, String>> tempatList = new ArrayList<HashMap<String, String>>();
>>>>>>> 41c0243d420a09475b6711aa01e9dd530a4f1fa8
		Intent prev = getIntent();
		NomorMisi = prev.getIntExtra("IDMisi", 0);
		ArrayList<HashMap<String, String>> tempatList = new ArrayList<HashMap<String, String>>();
		daftarMisi = new CustomizedDaftarMisi();
		
		if(daftarTempat != null){
			daftarTempat.clear();
		}
<<<<<<< HEAD
=======

>>>>>>> 41c0243d420a09475b6711aa01e9dd530a4f1fa8
		try {

			//daftarMisi = new CustomizedDaftarMisi();
			//IDMisi = Integer.parseInt(daftarMisi.KEY_ID_MISI);
			
			Intent intent = getIntent();
			
			daftarTempat = TempatHelper.GetListTempatByMisi(context, intent.getIntExtra("IDMisi", 0));
			

			// looping through all song nodes &lt;song&gt;
			for (int i = 0; i < daftarTempat.size(); i++) {

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				// adding each child node to HashMap key => value
				if (daftarTempat.get(i).getStatus() == 1) {
					map.put(KEY_STATUS, "VISITED");
				} else {
					map.put(KEY_STATUS, "UNVISITED");
				}
				map.put(KEY_NAMA, daftarTempat.get(i).getNama());
				map.put(KEY_DESKRIPSI, daftarTempat.get(i).getDeskripsi());
				map.put(KEY_FOTO, daftarTempat.get(i).getFoto());

				// adding HashList to ArrayList
				tempatList.add(map);
			}

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
}
