package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;
import android.app.Activity;
import android.content.Context;
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
	        R.drawable.badge_bali,
	        R.drawable.badge_jakarta,
	        R.drawable.badge_jogja,
	        R.drawable.badge_sumbar,
	        R.drawable.badge_ntb       
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
	                 if (curIndex > 4) {
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
	
}
