package project.TheExplorer.View;

import java.util.ArrayList;

import project.TheExplorer.Controller.R;
import project.TheExplorer.Controller.TempatHelper;
import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Tempat;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DaftarTempat extends Activity {
	TextView NamaTempat1, Alamat1, TitikPoint1, Status1;
	ArrayList<Tempat> DaftarTempat;
	Misi misi1, misi2, misi3, misi4, misi5;
	Context context;
	int StatusMisi1, MisiID;
	String ID;
	Tempat Tempat1, Tempat2, Tempat3, Tempat4, Tempat5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_daftar_tempat);
		NamaTempat1 = (TextView) findViewById(R.id.TextViewNamaTempat1);
		Alamat1 = (TextView) findViewById(R.id.TextViewAlamat1);
		TitikPoint1 = (TextView) findViewById(R.id.TextViewTitikPoint1);
		Status1 = (TextView) findViewById(R.id.textView1);

		Intent intent = getIntent();
		ID = intent.getStringExtra("MISI_ID");
		MisiID = Integer.parseInt(ID);
		Tempat1 = TempatHelper.GetTempat(context);

	}
}
