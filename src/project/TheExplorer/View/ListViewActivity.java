package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity {

	static int idMisi;
	Context context;
	ArrayList<String> daftarMisi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		// storing string resources into Array
		daftarMisi = MisiHelper.GetNotSavedMissionByName(context);

		// Binding resources Array to ListAdapter
		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.listview_misi, R.id.label, daftarMisi));

		ListView lv = getListView();

		// listening to single list item on click
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// selected item
				String namaMisi = ((TextView) view).getText().toString();

				if (namaMisi.equals("Explore Jogja"))
					idMisi = 1;
				else if (namaMisi.equals("Explore Jakarta"))
					idMisi = 2;
				else if (namaMisi.equals("Explore Bali"))
					idMisi = 3;
				else if (namaMisi.equals("Explore West Sumatera"))
					idMisi = 4;
				else if (namaMisi.equals("Explore West Nusa Tenggara"))
					idMisi = 5;
				else if (namaMisi.equals("Explore Malang"))
					idMisi = 6;
				else if (namaMisi.equals("Explore Surabaya"))
					idMisi = 7;
				else if (namaMisi.equals("Explore Bandung"))
					idMisi = 8;
				else if (namaMisi.equals("Explore Medan"))
					idMisi = 9;
				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						MissionOverview.class);
				// sending data to new activity
				i.putExtra("namaMisi", idMisi);
				startActivity(i);

			}
		});
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

	// Initiating Menu XML file (menu.xml)
	@Override
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


}