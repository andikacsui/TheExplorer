package project.TheExplorer.View;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Controller.TempatHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SignIn extends Activity {

	TextView TextViewUsername, TextViewPassword;
	EditText User, EditTextPassword;
	Context context;
	String Username;
	String Password;
	String temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		TextViewUsername = (TextView) findViewById(R.id.TextViewUsername);
		User = (EditText) findViewById(R.id.EditTextUsername);
		context = this;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

	public void ButtonSimpan_onClick(View view) {
		Username = User.getText().toString();
		if (notEmpty(Username)) {
			Intent NextScreen = new Intent(getApplicationContext(),
					DaftarMenu.class);
			startActivity(NextScreen);
			PenjelajahHelper.AddPenjelajah(context, Username);
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

}
