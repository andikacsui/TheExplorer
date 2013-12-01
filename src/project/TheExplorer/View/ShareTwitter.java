package project.TheExplorer.View;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import project.TheExplorer.Controller.R;

public class ShareTwitter extends Activity {
	private ImageView img;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_share_twitter);
	      img = (ImageView) findViewById(R.id.imageView1);

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

}
