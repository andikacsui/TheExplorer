package project.TheExplorer.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	static final String KEY_TITLE = "title";
	static final String KEY_ARTIST = "artist";
	static final String KEY_DURATION = "duration";
	static final String KEY_THUMB_URL = "thumb_url";
	ArrayList<Misi> daftarMisi;

	ListView list;
	ListMisiAdapter adapter;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_misi2);
		context = this;

		ArrayList<HashMap<String, String>> missionList = new ArrayList<HashMap<String, String>>();
		try {
			final Context mContext = this;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			AssetManager assetManager = mContext.getAssets();
			InputStream inputStream = assetManager.open("xml_daftar_misi.xml");
			Document doc = db.parse(inputStream);
			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("misi");

			daftarMisi = MisiHelper.GetListMisi(context);

			// looping through all song nodes &lt;song&gt;
			for (int i = 0; i < daftarMisi.size(); i++) {

				Node node = nl.item(i);
				Element element = (Element) node;

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				// adding each child node to HashMap key => value

				map.put(KEY_TITLE, daftarMisi.get(i).getNama());
				map.put(KEY_ARTIST, daftarMisi.get(i).getLokasi());
				map.put(KEY_DURATION, daftarMisi.get(i).getDeskripsi());
				map.put(KEY_THUMB_URL, daftarMisi.get(i).getFoto());

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
}
