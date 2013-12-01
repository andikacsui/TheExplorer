package project.TheExplorer.View;

import project.TheExplorer.Controller.PenjelajahHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Penjelajah;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
}
