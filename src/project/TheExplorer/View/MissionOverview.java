package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
		Button detail = (Button) findViewById(R.id.buttonDetails);
		ImageView foto = (ImageView) findViewById(R.id.misi_img);
		//TextView judul = (TextView) findViewById(R.id.namaMisi);
		
		
		daftarMisi = MisiHelper.GetListMisi(context);
		judul.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getNama());
		deskripsi.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getDeskripsi());
		lokasi.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getLokasi());
		detail.setId(daftarMisi.get(ListViewActivity.idMisi - 1).getID());
		
		/* Tambahin foto */
		String namaGambar = daftarMisi.get(ListViewActivity.idMisi - 1).getFoto();
		Resources res = getResources();
		int resId = res.getIdentifier(namaGambar, "drawable", getPackageName());
		foto.setImageResource(resId);
	}
	
	public void buttonDetails_onClick(View view){
		Intent nextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		startActivity(nextScreen);
	}
	
	public void buttonJoin_onClick(View view){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
 
			// set title
			alertDialogBuilder.setTitle("Your Title");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Click yes to exit!")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						MissionOverview.this.finish();
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
	}
	
}
