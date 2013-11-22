package project.TheExplorer.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import project.TheExplorer.Controller.R;
import project.TheExplorer.Controller.TempatHelper;
import project.TheExplorer.Model.Tempat;

public class CustomizedDaftarTempat extends Activity {
	// All static variables
	static final String URL = "http://api.androidhive.info/music/music.xml";
	// XML node keys
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_NAMA = "nama";
	static final String KEY_ALAMAT = "lokasi";
	static final String KEY_TITIKPOINT = "deskripsi";
	static final String KEY_STATUS = "status";
	static final String KEY_FOTO = "foto";
	int status;
	ArrayList<Tempat> daftarTempat;

	ListView list;
	ListTempatAdapter adapter;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_tempat2);
		context = this;

		ArrayList<HashMap<String, String>> missionList = new ArrayList<HashMap<String, String>>();
		try {

			daftarTempat = TempatHelper.GetListTempatByMisi(context, 1);

			// looping through all song nodes &lt;song&gt;
			for (int j = 0; j < daftarTempat.size(); j++) {

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				status = daftarTempat.get(j).getStatus();
				if (status == 1) {
					map.put(KEY_STATUS, "Visited");
				} else if (status == 0) {
					map.put(KEY_STATUS, "Unvisited");
				}
				map.put(KEY_NAMA, daftarTempat.get(j).getNama());
				map.put(KEY_ALAMAT, daftarTempat.get(j).getAlamat());
				map.put(KEY_TITIKPOINT, daftarTempat.get(j).getTitikPoint());
				map.put(KEY_FOTO, daftarTempat.get(j).getFoto());
				missionList.add(map);
			}

			list = (ListView) findViewById(R.id.list_tempat_layout);
			adapter = new ListTempatAdapter(this, missionList);
			list.setAdapter(adapter);
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// ListView Clicked item index
					int itemPosition = position;

					// ListView Clicked item value
					String itemValue = list
							.getItemAtPosition(position).toString();
					Toast.makeText(
							getApplicationContext(),
							"Position :" + itemPosition + "  ListItem : "
									+ itemValue, Toast.LENGTH_LONG).show();

				}
			});
		} catch (Exception e) {
		}
	}
}
