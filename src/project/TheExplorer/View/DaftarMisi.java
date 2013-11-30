package project.TheExplorer.View;

import java.util.ArrayList;
import java.util.List;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DaftarMisi extends Activity {
	TextView NamaMisi1, Lokasi1, Deskripsi1, Details1, Join1, NamaMisi2,
			Lokasi2, Deskripsi2, Details2, Join2, NamaMisi3, Lokasi3,
			Deskripsi3, Details3, Join3, NamaMisi4, Lokasi4, Deskripsi4,
			Details4, Join4, NamaMisi5, Lokasi5, Deskripsi5, Details5, Join5;
	ImageView GambarMisi1, GambarMisi2, GambarMisi3, GambarMisi4, GambarMisi5;
	ArrayList<Misi> DaftarMisi;
	Misi misi1, misi2, misi3, misi4, misi5;
	Context context;
	public String MisiID1, MisiID2, MisiID3, MisiID4, MisiID5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_daftar_misi_hc);
		GambarMisi1 = (ImageView) findViewById(R.id.ImageViewGambarMisi1);
		NamaMisi1 = (TextView) findViewById(R.id.TextViewNamaMisi1);
		Lokasi1 = (TextView) findViewById(R.id.TextViewLokasi1);
		Deskripsi1 = (TextView) findViewById(R.id.TextViewDeskripsi1);
		Details1 = (TextView) findViewById(R.id.TextViewDetailsMisi1);
		Join1 = (TextView) findViewById(R.id.TextViewJoinMisi1);

		GambarMisi2 = (ImageView) findViewById(R.id.ImageViewGambarMisi2);
		NamaMisi2 = (TextView) findViewById(R.id.TextViewNamaMisi2);
		Lokasi2 = (TextView) findViewById(R.id.TextViewLokasi2);
		Deskripsi2 = (TextView) findViewById(R.id.TextViewDeskripsi2);
		Details2 = (TextView) findViewById(R.id.TextViewDetailsMisi2);
		Join2 = (TextView) findViewById(R.id.TextViewJoinMisi2);

		GambarMisi3 = (ImageView) findViewById(R.id.ImageViewGambarMisi3);
		NamaMisi3 = (TextView) findViewById(R.id.TextViewNamaMisi3);
		Lokasi3 = (TextView) findViewById(R.id.TextViewLokasi3);
		Deskripsi3 = (TextView) findViewById(R.id.TextViewDeskripsi3);
		Details3 = (TextView) findViewById(R.id.TextViewDetailsMisi3);
		Join3 = (TextView) findViewById(R.id.TextViewJoinMisi3);

		GambarMisi4 = (ImageView) findViewById(R.id.ImageViewGambarMisi4);
		NamaMisi4 = (TextView) findViewById(R.id.TextViewNamaMisi4);
		Lokasi4 = (TextView) findViewById(R.id.TextViewLokasi4);
		Deskripsi4 = (TextView) findViewById(R.id.TextViewDeskripsi4);
		Details4 = (TextView) findViewById(R.id.TextViewDetailsMisi4);
		Join4 = (TextView) findViewById(R.id.TextViewJoinMisi4);

		GambarMisi5 = (ImageView) findViewById(R.id.ImageViewGambarMisi5);
		NamaMisi5 = (TextView) findViewById(R.id.TextViewNamaMisi5);
		Lokasi5 = (TextView) findViewById(R.id.TextViewLokasi5);
		Deskripsi5 = (TextView) findViewById(R.id.TextViewDeskripsi5);
		Details5 = (TextView) findViewById(R.id.TextViewDetailsMisi5);
		Join5 = (TextView) findViewById(R.id.TextViewJoinMisi5);

		// apa = MisiHelper.GetListMisi(context);
		// misi1 = DaftarMisi.get(1);
		// NamaMisi1.setText("Halooo" + misi1.getNama());
		// Lokasi1.setText(apa);
		// Deskripsi1.setText(misi1.getDeskripsi());
		DaftarMisi = MisiHelper.GetListMisi(context);
		// String path = "@drawable/ic_launcher.png";
		// int imageResource = getResources().getIdentifier(path, null,
		// getPackageName());
		// Drawable res = getResources().getDrawable(imageResource);
		// GambarMisi1.setImageDrawable(res);

		misi1 = DaftarMisi.get(0);
		NamaMisi1.setText(misi1.getNama());
		Lokasi1.setText(misi1.getLokasi());
		Deskripsi1.setText(misi1.getDeskripsi());
		MisiID1 = "1";

		misi2 = DaftarMisi.get(1);
		NamaMisi2.setText(misi2.getNama());
		Lokasi2.setText(misi2.getLokasi());
		Deskripsi2.setText(misi2.getDeskripsi());
		MisiID2 = "2";

		misi3 = DaftarMisi.get(2);
		NamaMisi3.setText(misi3.getNama());
		Lokasi3.setText(misi3.getLokasi());
		Deskripsi3.setText(misi3.getDeskripsi());
		MisiID3 = "3";

		misi4 = DaftarMisi.get(3);
		NamaMisi4.setText(misi4.getNama());
		Lokasi4.setText(misi4.getLokasi());
		Deskripsi4.setText(misi4.getDeskripsi());
		MisiID4 = "4";

		misi5 = DaftarMisi.get(4);
		NamaMisi5.setText(misi5.getNama());
		Lokasi5.setText(misi5.getLokasi());
		Deskripsi5.setText(misi5.getDeskripsi());
		MisiID5 = "5";
	}

	public void TextViewDetailsMisi1_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID1);
		startActivity(NextScreen);
	}

	public void TextViewJoinMisi1_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID1);
		startActivity(NextScreen);
	}

	public void TextViewDetailsMisi2_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID2);
		startActivity(NextScreen);
	}

	public void TextViewJoinMisi2_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID2);
		startActivity(NextScreen);
	}

	public void TextViewDetailsMisi3_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID3);
		startActivity(NextScreen);
	}

	public void TextViewJoinMisi3_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID3);
		startActivity(NextScreen);
	}

	public void TextViewDetailsMisi4_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID4);
		startActivity(NextScreen);
	}

	public void TextViewJoinMisi4_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID4);
		startActivity(NextScreen);
	}

	public void TextViewDetailsMisi5_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID5);
		startActivity(NextScreen);
	}

	public void TextViewJoinMisi5_OnClick(View view) {
		Intent NextScreen = new Intent(getApplicationContext(),
				CustomizedDaftarTempat.class);
		NextScreen.putExtra("MISI_ID", MisiID5);
		startActivity(NextScreen);
	}
}
