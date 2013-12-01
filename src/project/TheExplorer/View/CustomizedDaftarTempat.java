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
import android.content.res.Resources;
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
	static final String KEY_ID = "id";
	static final String KEY_NAMA = "nama";
	static final String KEY_DESKRIPSI = "deskripsi";
	static final String KEY_FOTO = "foto";
	static final String KEY_FOTO_PATH = "fotopath";
	static final String KEY_STATUS = "status";
	static int NomorMisi, MisiID;
	static String Misi = "";
	static int[] imgArr = new int[100];
	static ArrayList<Tempat> daftarTempat;
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
			
			//log.d("hmmm", "haaah lala" + misiid);
			//Log.d("debag", "list0 " + daftarTempat.isEmpty());
			daftarTempat = TempatHelper.GetListTempatByMisi(context, ListViewActivity.idMisi);
			Log.d("debag", "list1 " + ListViewActivity.idMisi);
			Log.d("debag", "list2 " + daftarTempat.size());

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
				//map.put(KEY_ID, ""+daftarTempat.get(i).getID());
				//add foto
				String namaGambar = daftarTempat.get(i).getFoto();
				Resources res = getResources();
				int resId = res.getIdentifier(namaGambar, "drawable", getPackageName());
				map.put(KEY_FOTO_PATH, ""+resId);
				// adding HashList to ArrayList
				tempatList.add(map);
			}
			daftarTempat.clear();
			list = (ListView) findViewById(R.id.list_badge_layout);

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
