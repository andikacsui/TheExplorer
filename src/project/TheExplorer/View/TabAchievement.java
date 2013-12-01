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
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Controller.TempatHelper;
import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Tempat;

public class TabAchievement extends Activity {
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
	static ArrayList<Misi> daftarBadgeMisi, daftarBadge;
	HashMap<String, String> map;

	ListView list;
	ListBadgeAdapter adapter;
	Context context;
	CustomizedDaftarMisi daftarMisi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_badge);
		context = this;
		daftarMisi = new CustomizedDaftarMisi();

		Intent prev = getIntent();
		NomorMisi = prev.getIntExtra("IDMisi", 0);
		ArrayList<HashMap<String, String>> tempatList = new ArrayList<HashMap<String, String>>();
		daftarMisi = new CustomizedDaftarMisi();

		daftarBadgeMisi = MisiHelper.GetBadge(context);
		// daftarBadgeMisi = PenjelajahHelper.GetBadge(context);
		if (daftarBadgeMisi != null) {
			daftarBadgeMisi.clear();
		}

		try {
			daftarBadgeMisi = MisiHelper.GetBadge(context);
			// daftarBadgeMisi = PenjelajahHelper.GetBadge(context);
			int apa = daftarBadgeMisi.size();
			Log.d("taggg", apa + "");
			for (int i = 0; i < daftarBadgeMisi.size(); i++) {
				map = new HashMap<String, String>();
				map.put(KEY_NAMA, daftarBadgeMisi.get(i).getNama());
				map.put(KEY_FOTO, daftarBadgeMisi.get(i).getFoto());
				String namaGambar = daftarBadgeMisi.get(i).getBadge();
				Resources res = getResources();
				int resId = res.getIdentifier(namaGambar, "drawable",
						getPackageName());
				map.put(KEY_FOTO_PATH, "" + resId);
				tempatList.add(map);
			}
			daftarBadgeMisi.clear();
			list = (ListView) findViewById(R.id.list_badge_layout);

			// Getting adapter by passing xml data ArrayList
			adapter = new ListBadgeAdapter(this, tempatList);
			list.setAdapter(adapter);
		} catch (Exception e) {
		}
	}
}
