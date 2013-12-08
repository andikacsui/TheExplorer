package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.GPSTracker;
import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DaftarMenu extends Activity {
	GPSTracker gps;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_menu);
		context = this;
		ArrayList<Misi> misi = MisiHelper.GetListMisi(context);
		for (int i = 1; i <= misi.size(); i++) {
			MisiHelper.UpdateStatusMisi(context, i);
		}

	}

	public void ButtonChooseNewMission_OnClick(View view) {
		ArrayList<Misi> ListMisi = MisiHelper.GetNotSavedMission(context);
		if (notEmpty(ListMisi)) {
			Intent nextScreen = new Intent(getApplicationContext(),
					ListViewActivity.class);
			startActivity(nextScreen);
		} else {
			showAlertbox("All missions have been saved");
		}
	}

	public void ButtonSavedMission_OnClick(View view) {
		ArrayList<Misi> ListMisi = MisiHelper.GetSavedMission(context);
		if (notEmpty(ListMisi)) {
			Intent nextScreen = new Intent(getApplicationContext(),
					ListViewSavedMission.class);
			startActivity(nextScreen);
		} else {
			showAlertbox("You don't have saved mission");
		}

	}

	public void ButtonProfile_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				TabProfile.class);
		startActivity(nextScreen);
	}

	public void ButtonShare_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				ShareTwitter.class);
		startActivity(nextScreen);
	}

	public void ButtonSensor_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				SensorTestActivity.class);
		startActivity(nextScreen);
	}

	public void ButtonGetLocation_OnClick(View view) {
		// create class object
		gps = new GPSTracker(context);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			// \n is for new line
			Toast.makeText(
					getApplicationContext(),
					"Your Location is - \nLat: " + latitude + "\nLong: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
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

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}