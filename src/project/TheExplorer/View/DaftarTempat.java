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
	TextView NamaTempat1, Alamat1, TitikPoint1, Status1, NamaTempat2, Alamat2,
			TitikPoint2, Status2, NamaTempat3, Alamat3, TitikPoint3, Status3,
			NamaTempat4, Alamat4, TitikPoint4, Status4, NamaTempat5, Alamat5,
			TitikPoint5, Status5;
	ArrayList<Tempat> DaftarTempat;
	Misi misi1, misi2, misi3, misi4, misi5;
	Context context;
	int StatusMisi1, StatusMisi2, StatusMisi3, StatusMisi4, StatusMisi5,
			MisiID;
	String Misi;
	Tempat Tempat1, Tempat2, Tempat3, Tempat4, Tempat5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_daftar_tempat);
		NamaTempat1 = (TextView) findViewById(R.id.TextViewNamaTempat1);
		Alamat1 = (TextView) findViewById(R.id.TextViewAlamat1);
		TitikPoint1 = (TextView) findViewById(R.id.TextViewTitikPoint1);
		Status1 = (TextView) findViewById(R.id.TextViewVisitedTempat1);
		NamaTempat2 = (TextView) findViewById(R.id.TextViewNamaTempat2);
		Alamat2 = (TextView) findViewById(R.id.TextViewAlamat2);
		TitikPoint2 = (TextView) findViewById(R.id.TextViewTitikPoint2);
		Status2 = (TextView) findViewById(R.id.TextViewVisitedTempat2);
		NamaTempat3 = (TextView) findViewById(R.id.TextViewNamaTempat3);
		Alamat3 = (TextView) findViewById(R.id.TextViewAlamat3);
		TitikPoint3 = (TextView) findViewById(R.id.TextViewTitikPoint3);
		Status3 = (TextView) findViewById(R.id.TextViewVisitedTempat3);
		NamaTempat4 = (TextView) findViewById(R.id.TextViewNamaTempat4);
		Alamat4 = (TextView) findViewById(R.id.TextViewAlamat4);
		TitikPoint4 = (TextView) findViewById(R.id.TextViewTitikPoint4);
		Status4 = (TextView) findViewById(R.id.TextViewVisitedTempat4);
		NamaTempat5 = (TextView) findViewById(R.id.TextViewNamaTempat5);
		Alamat5 = (TextView) findViewById(R.id.TextViewAlamat5);
		TitikPoint5 = (TextView) findViewById(R.id.TextViewTitikPoint5);
		Status5 = (TextView) findViewById(R.id.TextViewVisitedTempat5);

		Intent intent = getIntent();
		Misi = intent.getStringExtra("MISI_ID");
		MisiID = Integer.parseInt(Misi);
		DaftarTempat = TempatHelper.GetListTempatByMisi(context, MisiID);

		Tempat1 = DaftarTempat.get(0);
		NamaTempat1.setText(Tempat1.getNama());
		Alamat1.setText(Tempat1.getAlamat());
		TitikPoint1.setText(Tempat1.getTitikPoint());
		StatusMisi1 = Tempat1.getStatus();
		if (StatusMisi1 == 0) {
			Status1.setText("Unvisited");
		} else {
			Status1.setText("Visited");
		}

		Tempat2 = DaftarTempat.get(1);
		NamaTempat2.setText(Tempat1.getNama());
		Alamat2.setText(Tempat1.getAlamat());
		TitikPoint2.setText(Tempat1.getTitikPoint());
		StatusMisi2 = Tempat1.getStatus();
		if (StatusMisi2 == 0) {
			Status2.setText("Unvisited");
		} else {
			Status2.setText("Visited");
		}

		Tempat3 = DaftarTempat.get(2);
		NamaTempat3.setText(Tempat1.getNama());
		Alamat3.setText(Tempat1.getAlamat());
		TitikPoint3.setText(Tempat1.getTitikPoint());
		StatusMisi3 = Tempat1.getStatus();
		if (StatusMisi3 == 0) {
			Status3.setText("Unvisited");
		} else {
			Status3.setText("Visited");
		}

		Tempat4 = DaftarTempat.get(3);
		NamaTempat4.setText(Tempat1.getNama());
		Alamat4.setText(Tempat1.getAlamat());
		TitikPoint4.setText(Tempat1.getTitikPoint());
		StatusMisi4 = Tempat1.getStatus();
		if (StatusMisi4 == 0) {
			Status4.setText("Unvisited");
		} else {
			Status4.setText("Visited");
		}

		Tempat5 = DaftarTempat.get(4);
		NamaTempat5.setText(Tempat1.getNama());
		Alamat5.setText(Tempat1.getAlamat());
		TitikPoint5.setText(Tempat1.getTitikPoint());
		StatusMisi5 = Tempat1.getStatus();
		if (StatusMisi5 == 0) {
			Status5.setText("Unvisited");
		} else {
			Status5.setText("Visited");
		}
	}
}
