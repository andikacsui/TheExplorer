package project.TheExplorer.View;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 1000;
	private static boolean HaveLogin;
	private static Context Context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Context = this;
		MisiHelper.AddMisi(Context);
		setContentView(R.layout.activity_splah_screen);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				HaveLogin = PenjelajahHelper.IsPenjelajahExist(Context);
				if (HaveLogin == false) {
					Intent i = new Intent(SplashScreen.this, SignIn.class);
					startActivity(i);
					finish();
				} else if (HaveLogin == true) {
					Intent i = new Intent(SplashScreen.this, DaftarMenu.class);
					startActivity(i);
					finish();
				}

			}
		}, SPLASH_TIME_OUT);
	}

}