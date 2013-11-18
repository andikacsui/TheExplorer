package project.TheExplorer.View;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

	TextView TextViewUsername, TextViewPassword, TextViewCoba;
	EditText EditTextUsername, EditTextPassword;
	Context context;
	String Username;
	String Password;
	String temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		TextViewUsername = (TextView) findViewById(R.id.TextViewUsername);
		EditTextUsername = (EditText) findViewById(R.id.EditTextUsername);
		TextViewPassword = (TextView) findViewById(R.id.TextViewPassword);
		EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);
		TextViewCoba = (TextView) findViewById(R.id.TextViewCoba);
		context = this;
		MisiHelper.AddMisi(context);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}
	
	public void ButtonSimpan_onClick(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), DaftarMenu.class);
		startActivity(nextScreen);
		Username = EditTextUsername.getText().toString();
		Password = EditTextPassword.getText().toString();
		PenjelajahHelper.AddPenjelajah(context, Username, Password);
		//temp = PenjelajahHelper.GetPenjelajahName(context);
		//TextViewCoba.setText("Hallo " + temp);
	}

}
