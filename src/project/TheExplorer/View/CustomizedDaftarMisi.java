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
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;

public class CustomizedDaftarMisi extends Activity {
	// All static variables
	static final String URL = "http://api.androidhive.info/music/music.xml";
	// XML node keys
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_ID_MISI = "idmisi";
	static final String KEY_NAMA = "nama";
	static final String KEY_LOKASI = "lokasi";
	static final String KEY_DESKRIPSI = "deskripsi";
	static final String KEY_FOTO = "foto";
	ArrayList<Misi> daftarMisi;
	static int id_misi;
	ListView list;
	ListMisiAdapter adapter;
	Context context;
	EditText IDMisi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_misi);
		context = this;
		
		if(daftarMisi != null){
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

				map.put(KEY_NAMA, daftarMisi.get(i).getNama());
				map.put(KEY_LOKASI, daftarMisi.get(i).getLokasi());
				map.put(KEY_DESKRIPSI, daftarMisi.get(i).getDeskripsi());
				map.put(KEY_FOTO, daftarMisi.get(i).getFoto());
				map.put(KEY_ID_MISI, String.valueOf(daftarMisi.get(i).getID()));
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
					AlertDialog.Builder dialog = new AlertDialog.Builder(CustomizedDaftarMisi.this);
					dialog
					.setTitle("Pilihan menu");
					
				}
			});
		} catch (Exception e) {
		}
	}
	
	public void TextViewDetailMisi_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), CustomizedDaftarTempat.class);
		id_misi = 5;
		nextScreen.putExtra("IDMisi", 1);
		startActivity(nextScreen); 
	}
}
