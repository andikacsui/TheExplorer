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

import project.TheExplorer.Controller.R;

public class CustomizedDaftarMisi extends Activity{
	// All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";
 
    ListView list;
    ListMisiAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_misi2);
 
        ArrayList<HashMap<String, String>> missionList = new ArrayList<HashMap<String, String>>();
        try{
        final Context mContext = this;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = assetManager.open("xml_daftar_misi.xml");   
        Document doc=db.parse(inputStream);
        doc.getDocumentElement().normalize();
        NodeList nl = doc.getElementsByTagName("misi");  
        
        // looping through all song nodes &lt;song&gt;
        for (int i = 0; i < nl.getLength(); i++) {
        	
        	Node node = nl.item(i);   
        	Element element = (Element) node;
        	
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            // adding each child node to HashMap key => value
            
            NodeList listJudul =  element .getElementsByTagName("judul");
            Element judulElement = (Element) listJudul.item(0);
            listJudul = judulElement.getChildNodes();        
            map.put(KEY_TITLE, ((Node) listJudul.item(0)).getNodeValue());
            
            NodeList listLokasi =  element .getElementsByTagName("lokasi");
            Element lokasiElement = (Element) listLokasi.item(0);
            listLokasi = lokasiElement.getChildNodes();       
            map.put(KEY_ARTIST, ((Node) listLokasi.item(0)).getNodeValue());
            
            NodeList listKeterangan =  element .getElementsByTagName("keterangan");
            Element keteranganElement = (Element) listKeterangan.item(0);
            listKeterangan = keteranganElement.getChildNodes(); 
            map.put(KEY_DURATION, ((Node) listKeterangan.item(0)).getNodeValue());
            
            NodeList listGambar =  element .getElementsByTagName("gambar");
            Element gambarElement = (Element) listGambar.item(0);
            listGambar = gambarElement.getChildNodes(); 
            map.put(KEY_THUMB_URL, ((Node) listGambar.item(0)).getNodeValue());
 
            // adding HashList to ArrayList
            missionList.add(map);
        }
 
        list=(ListView)findViewById(R.id.list_misi_layout);
 
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
        } catch (Exception e){}
    }
}
