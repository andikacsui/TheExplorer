package project.TheExplorer.View;

import java.util.ArrayList;
import java.util.List;

import project.TheExplorer.Controller.MisiHelper;
import project.TheExplorer.Controller.R;
import project.TheExplorer.Model.Misi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DaftarMisi extends Activity {
	TextView NamaMisi1, Lokasi1, Deskripsi1, Details1, Join1;
	ArrayList<Misi> DaftarMisi;
	Misi misi1;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_daftar_misi);
		NamaMisi1 = (TextView) findViewById(R.id.TextViewNamaMisi1);
		Lokasi1 = (TextView) findViewById(R.id.TextViewLokasi1);
		Deskripsi1 = (TextView) findViewById(R.id.TextViewDeskripsi1);
		Details1 = (TextView) findViewById(R.id.TextViewDetailsMisi1);
		Join1 = (TextView) findViewById(R.id.TextViewJoinMisi1);

		misi1 = DaftarMisi.get(0);
		NamaMisi1.setText(misi1.getNama());
		Lokasi1.setText(misi1.getLokasi());
		Deskripsi1.setText(misi1.getDeskripsi());
	}
}
