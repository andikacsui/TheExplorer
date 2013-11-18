package project.TheExplorer.View;

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
		setContentView(R.layout.activity_splah_screen);
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
					Intent i = new Intent(SplashScreen.this, SignIn.class);
					startActivity(i);

					// close this activity
					finish();
				
			}
		}, SPLASH_TIME_OUT);
	}

}