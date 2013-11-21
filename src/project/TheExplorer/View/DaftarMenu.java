package project.TheExplorer.View;

import project.TheExplorer.Controller.GPSTracker;
import project.TheExplorer.Controller.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	}

	public void ButtonChooseNewMission_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		startActivity(nextScreen);
	}

	public void ButtonSavedMission_OnClick(View view) {

	}

	public void ButtonProfile_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				TabProfile.class);
		startActivity(nextScreen);
	}

	public void ButtonShare_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), Share.class);
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