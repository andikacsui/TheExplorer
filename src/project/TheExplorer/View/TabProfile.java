package project.TheExplorer.View;

import project.TheExplorer.Controller.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabProfile extends TabActivity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_tab);
		
		TabHost tabHost = getTabHost();
		
		TabSpec summarySpec = tabHost.newTabSpec("Summary");
		summarySpec.setIndicator("Summary", getResources().getDrawable(R.drawable.activity_tab_summary_icon));
		Intent summaryIntent = new Intent(this, TabSummary.class);
		summarySpec.setContent(summaryIntent);
		
		TabSpec editProfileSpec = tabHost.newTabSpec("Edit Profile");
		editProfileSpec.setIndicator("Edit Profile", getResources().getDrawable(R.drawable.activity_tab_editprofile_icon));
		Intent editProfileIntent = new Intent(this, TabEditProfile.class);
		editProfileSpec.setContent(editProfileIntent);
		
		TabSpec achievementSpec = tabHost.newTabSpec("Achievement");
		achievementSpec.setIndicator("Achievement", getResources().getDrawable(R.drawable.activity_tab_achievement_icon));
		Intent achievementIntent = new Intent(this, TabAchievement.class);
		achievementSpec.setContent(achievementIntent);
		
		tabHost.addTab(summarySpec);
		tabHost.addTab(editProfileSpec);
		tabHost.addTab(achievementSpec);
	}
	
	
}
