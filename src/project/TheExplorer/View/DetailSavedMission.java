package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailSavedMission extends Activity {

	String missionName;
	String missionDetails;
	String missionID;
	ArrayList<Misi> daftarMisi;
	int StatusMisi;

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_saved_mission);
		context = this;

		TextView judul = (TextView) findViewById(R.id.namaMisi);
		TextView deskripsi = (TextView) findViewById(R.id.deskripsiMisi);
		TextView lokasi = (TextView) findViewById(R.id.lokasiMisi);
		TextView status = (TextView) findViewById(R.id.TextViewSavedStatusMisi);
		Button detail = (Button) findViewById(R.id.buttonDetails);
		// TextView judul = (TextView) findViewById(R.id.namaMisi);

		daftarMisi = MisiHelper.GetListMisi(context);
		judul.setText(daftarMisi.get(ListViewSavedMission.idMisi - 1).getNama());
		deskripsi.setText(daftarMisi.get(ListViewSavedMission.idMisi - 1)
				.getDeskripsi());
		lokasi.setText(daftarMisi.get(ListViewSavedMission.idMisi - 1)
				.getLokasi());
		detail.setId(daftarMisi.get(ListViewSavedMission.idMisi - 1).getID());
		StatusMisi = daftarMisi.get(ListViewSavedMission.idMisi - 1)
				.getStatus();
		Log.d("tag", StatusMisi + "");
		if (StatusMisi == 0) {
			status.setText("INCOMPLETED");
		} else if (StatusMisi == 1) {
			status.setText("COMPLETED");
		}

	}

	public void buttonDetails_onClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempatSavedMission.class);
		startActivity(nextScreen);
	}
}
