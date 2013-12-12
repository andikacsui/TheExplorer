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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
		// TextView judul = (TextView) findViewById(R.id.namaMisi);

		daftarMisi = MisiHelper.GetListMisi(context);
		judul.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getNama());
		deskripsi.setText(daftarMisi.get(ListViewActivity.idMisi - 1)
				.getDeskripsi());
		lokasi.setText(daftarMisi.get(ListViewActivity.idMisi - 1).getLokasi());
		detail.setId(daftarMisi.get(ListViewActivity.idMisi - 1).getID());

		/* Tambahin foto */
		String namaGambar = daftarMisi.get(ListViewActivity.idMisi - 1)
				.getFoto();
		Resources res = getResources();
		int resId = res.getIdentifier(namaGambar, "drawable", getPackageName());
		foto.setImageResource(resId);
	}

	public void buttonDetails_onClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		startActivity(nextScreen);
	}

	public void buttonJoin_onClick(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Join Mission");

		// set dialog message
		alertDialogBuilder
				.setMessage("Are you sure to join this mission?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
								int misiId = daftarMisi.get(ListViewActivity.idMisi - 1).getID();
								MisiHelper.JoinMission(context, misiId);
								
								Intent nextScreen = new Intent(
										getApplicationContext(),
										ListViewSavedMission.class);
								startActivity(nextScreen);
								// MissionOverview.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
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
	
	public void showAlertbox(String erroMessage) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle("Error Message");

		// Setting Dialog Message
		alertDialog.setMessage(erroMessage);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Write your code here to execute after dialog closed

			}
		});

		// Showing Alert Message
		alertDialog.show();

	}

	public static boolean notEmpty(ArrayList<Misi> s) {
		return (s != null && s.size() > 0);
	}

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.new_mission:
			// Single menu item is selected do something
			// Ex: launching new activity/screen or show alert message
			ArrayList<Misi> ListMisi = MisiHelper.GetNotSavedMission(context);
			if (notEmpty(ListMisi)) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ListViewActivity.class);
				startActivity(nextScreen);
			} else {
				showAlertbox("All missions have been saved");
			}
			return true;

		case R.id.saved_mission:
			ArrayList<Misi> ListMisi2 = MisiHelper.GetSavedMission(context);
			if (notEmpty(ListMisi2)) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ListViewSavedMission.class);
				startActivity(nextScreen);
			} else {
				showAlertbox("You don't have saved mission");
			}
			return true;

		case R.id.achievement:
			Intent nextScreen = new Intent(getApplicationContext(),
					TabProfile.class);
			startActivity(nextScreen);
			return true;
			
		case R.id.help:
			Intent nextScreen2 = new Intent(getApplicationContext(),
					BantuanSlider.class);
			startActivity(nextScreen2);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
