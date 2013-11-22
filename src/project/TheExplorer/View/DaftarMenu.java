package project.TheExplorer.View;


import project.TheExplorer.Controller.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DaftarMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar_menu);
		
	}

	public void ButtonChooseNewMission_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), CustomizedDaftarMisi.class);
		startActivity(nextScreen);
	}

	public void ButtonSavedMission_OnClick(View view) {

	}

	public void ButtonProfile_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), TabProfile.class);
		startActivity(nextScreen);
	}

	public void ButtonShare_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), Share.class);
		startActivity(nextScreen);
	}
}