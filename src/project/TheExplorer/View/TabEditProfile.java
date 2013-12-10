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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TabEditProfile extends Activity {

	TextView TextViewUsername;
	EditText User;
	Context context;
	String Username;
	String Password;
	String temp;
	Penjelajah penjelajah;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_editprofile);
		TextViewUsername = (TextView) findViewById(R.id.textViewEditUsername);
		User = (EditText) findViewById(R.id.editTextEditUsername);
		context = this;
		penjelajah = PenjelajahHelper.GetPenjelajah(context);
		User.setText(penjelajah.getUsername().toString());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		getMenuInflater().inflate(R.layout.menu, menu);
		return true;
	}

	public void buttonUpdate_OnClick(View view) {
		Username = User.getText().toString();
		if (notEmpty(Username)) {
			PenjelajahHelper.UpdatePenjelajahUsername(context, Username);
			Intent NextScreen = new Intent(getApplicationContext(),
					TabProfile.class);
			startActivity(NextScreen);

		} else {
			showAlertbox("Username cannot be empty");
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

	public static boolean notEmpty(String s) {
		return (s != null && s.length() > 0);
	}
	

	public static boolean notEmpty(ArrayList<Misi> s) {
		return (s != null && s.size() > 0);
	}

	// Initiating Menu XML file (menu.xml)

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