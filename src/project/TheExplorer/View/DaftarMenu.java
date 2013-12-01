package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.GPSTracker;
import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
		Intent nextScreen = new Intent(getApplicationContext(),
				ListViewActivity.class);
		startActivity(nextScreen);
	}

	public void ButtonSavedMission_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				ListViewSavedMission.class);
		startActivity(nextScreen);
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
}