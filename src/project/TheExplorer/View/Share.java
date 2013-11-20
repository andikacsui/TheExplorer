package project.TheExplorer.View;

import project.TheExplorer.Controller.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Share extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		
	}
	
	public void ButtonTakePicture_OnClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), AmbilFoto.class);
		startActivity(nextScreen);
	}
}
