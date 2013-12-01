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

public class CustomizedDaftarBadge extends Activity {
	// All static variables
	static final String URL = "http://api.androidhive.info/music/music.xml";
	// XML node keys
	static final String KEY_ID = "id";
	static final String KEY_NAMA = "nama";
	static final String KEY_FOTO = "foto";
	static final String KEY_FOTO_PATH = "fotopath";
	static int NomorMisi, MisiID;
	static String Misi = "";
	static int[] imgArr = new int[100];
	static ArrayList<Misi> daftarMisi;
	static ArrayList<Misi> ListBadgeMisi;
	HashMap<String, String> map;

	ListView list;
	ListBadgeAdapter adapter;
	Context context;
	CustomizedDaftarBadge daftarBadge;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_badge);
		context = this;
		daftarBadge = new CustomizedDaftarBadge();

		Intent prev = getIntent();
		NomorMisi = prev.getIntExtra("IDMisi", 0);
		ArrayList<HashMap<String, String>> badgeList = new ArrayList<HashMap<String, String>>();
		daftarBadge = new CustomizedDaftarBadge();

		daftarMisi = MisiHelper.GetSavedMission(context);
		for (int i = 0; i < daftarMisi.size(); i++) {
			Misi misi = daftarMisi.get(i);
			if (misi.getStatus() == 1) {
				ListBadgeMisi.add(misi);
			}
		}

		// if (ListBadgeMisi != null) {
		// ListBadgeMisi.clear();
		// }

		try {
			for (int i = 0; i < ListBadgeMisi.size(); i++) {
				map = new HashMap<String, String>();
				map.put(KEY_NAMA, ListBadgeMisi.get(i).getNama());
				map.put(KEY_FOTO, ListBadgeMisi.get(i).getFoto());
				String namaGambar = ListBadgeMisi.get(i).getFoto();
				Resources res = getResources();
				int resId = res.getIdentifier(namaGambar, "drawable",
						getPackageName());
				map.put(KEY_FOTO_PATH, "" + resId);
				badgeList.add(map);
			}
			ListBadgeMisi.clear();
			list = (ListView) findViewById(R.id.list_badge_layout);
			adapter = new ListBadgeAdapter(this, badgeList);
			list.setAdapter(adapter);
		} catch (Exception e) {
		}
	}
}
