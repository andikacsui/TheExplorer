package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class BantuanSlider extends Activity implements ViewFactory {
	
	private ImageSwitcher imageSwitcher ;
	Context context;

	Integer[] imageList = {
	        R.drawable.help1,
	        R.drawable.help2,
	        R.drawable.help3,
	        R.drawable.help4,
	        R.drawable.help5,
	        R.drawable.help6,
	        R.drawable.help7,
	        R.drawable.help8,
	        R.drawable.help9,
	        R.drawable.help10       
	};

	int curIndex=0;
	int downX,upX;

	@Override  
	public void onCreate(Bundle savedInstanceState) {
		 context = this;
	     super.onCreate(savedInstanceState);  
	     setContentView(R.layout.activity_bantuan);  

	     imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
	     imageSwitcher.setFactory(this);
	     imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
	     imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));

	     imageSwitcher.setImageResource(imageList[curIndex]);
	     imageSwitcher.setOnTouchListener(new OnTouchListener() {
	     @Override
	     public boolean onTouch(View v, MotionEvent event) {

	         if (event.getAction() == MotionEvent.ACTION_DOWN) {
	             downX = (int) event.getX(); 
	             return true;
	         } 

	         else if (event.getAction() == MotionEvent.ACTION_UP) {
	             upX = (int) event.getX(); 
	             if (upX - downX > 100) {

	                 //curIndex  current image index in array viewed by user
	                 curIndex--;
	                 if (curIndex < 0) {
	                     curIndex = imageList.length-1;
	                 }

	                 imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(BantuanSlider.this,R.anim.slide_in_left));
	                 imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(BantuanSlider.this,R.anim.slide_out_right));

	                 imageSwitcher.setImageResource(imageList[curIndex]);
	                 //GalleryActivity.this.setTitle(curIndex);
	             } 

	             else if (downX - upX > -100) {

	                 curIndex++;
	                 if (curIndex > 9) {
	                     curIndex = 0;
	                 }

	                 imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(BantuanSlider.this,R.anim.slide_in_right));
	                 imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(BantuanSlider.this,R.anim.slide_out_left));

	                 imageSwitcher.setImageResource(imageList[curIndex]);
	                 //GalleryActivity.this.setTitle(curIndex);
	             }
	                 return true;
	             }
	             return false;
	         }
	     });
	     
	     
	}
	@Override
	public View makeView() {
	    ImageView i = new ImageView(this);  
	    i.setScaleType(ImageView.ScaleType.FIT_CENTER);
	    return i;
	}
	
	public static boolean notEmpty(ArrayList<Misi> s) {
		return (s != null && s.size() > 0);
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
