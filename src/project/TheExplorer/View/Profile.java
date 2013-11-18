package project.TheExplorer.View;

import project.TheExplorer.Controller.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
	}

	public void ButtonChooseNewMission_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), Profile.class);
		startActivity(nextScreen);
	}
}
