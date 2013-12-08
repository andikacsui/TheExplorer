package project.TheExplorer.View;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;

public class ShareTwitter extends Activity {
	private ImageView img;
	Context context;
	
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_share_twitter);
	      img = (ImageView) findViewById(R.id.imageView1);
	      context = this;
	   }
/*
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      getMenuInflater().inflate(R.id.ButtonShare, menu);
	      return true;
	   }
*/   
	   public void open(View view){
	      
		  initShareIntent("twi");

	   }
	   
	   private void initShareIntent(String type) {
		  //try{
		   	boolean found = false;
		    Intent share = new Intent(android.content.Intent.ACTION_SEND);
		    share.setType("image/*");
		    
		    
		    // gets the list of intents that can be loaded.
		    List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);
		    if (!resInfo.isEmpty()){
		        for (ResolveInfo info : resInfo) {
		            if (info.activityInfo.packageName.toLowerCase().contains(type) || 
		                    info.activityInfo.name.toLowerCase().contains(type) ) {
		            	
		                share.putExtra(Intent.EXTRA_SUBJECT,  "subject");
		                share.putExtra(Intent.EXTRA_TEXT,     "Hi, I am in " + CustomizedDaftarTempatSavedMission.namaTempat + " right now. - Sent from The Explorer Mission");
		                share.setPackage(info.activityInfo.packageName);
		                found = true;
		                break;
		            }
		        }
		        if (!found)
		            return;

		        startActivity(Intent.createChooser(share, "Select"));
		    }
		 /* } catch (final ActivityNotFoundException e) {
		        Toast.makeText(null, "You don't seem to have twitter installed on this device", Toast.LENGTH_SHORT).show();
		    } */
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

			default:
				return super.onOptionsItemSelected(item);
			}
		}


}
