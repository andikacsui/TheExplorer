package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Penjelajah;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TabSummary extends Activity {
	TextView Greetings, Score, Achievement;
	Penjelajah penjelajah;
	Context context;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_summary);

		Greetings = (TextView) findViewById(R.id.TextViewGreetings);
		Score = (TextView) findViewById(R.id.TextViewScore);
		Achievement = (TextView) findViewById(R.id.TextViewAchievement);
		context = this;

		penjelajah = PenjelajahHelper.GetPenjelajah(context);
		Log.d("tag", "hai");
		Greetings.setText("Hi, " + penjelajah.getUsername());
		Score.setText("" + penjelajah.getSkor());
		Achievement.setText(PenjelajahHelper.GetJumlahBadge(context)
				+ " Badge(s)");

	}

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
}
