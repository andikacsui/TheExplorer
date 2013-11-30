package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MissionOverview extends Activity {
	
	String missionName;
	String missionDetails;
	String missionID;
	ArrayList<Misi> daftarMisi;
	
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview_misi);
		context = this;
		
		
		TextView judul = (TextView) findViewById(R.id.namaMisi);
		TextView deskripsi = (TextView) findViewById(R.id.deskripsiMisi);
		TextView lokasi = (TextView) findViewById(R.id.lokasiMisi);
		//TextView judul = (TextView) findViewById(R.id.namaMisi);
		
		
		daftarMisi = MisiHelper.GetListMisi(context);
		judul.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getNama());
		deskripsi.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getDeskripsi());
		lokasi.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getLokasi());
	}
	
	
}