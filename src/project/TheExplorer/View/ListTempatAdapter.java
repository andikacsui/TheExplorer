package project.TheExplorer.View;

import java.util.ArrayList;
import java.util.HashMap;

import project.TheExplorer.Controller.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListTempatAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflat = null;

	public ListTempatAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflat = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView2, ViewGroup parent) {
		View view = convertView2;
		if (convertView2 == null)
			view = inflat.inflate(R.layout.activity_list_tempat, null);

		TextView nama = (TextView) view.findViewById(R.id.TextViewNamaTempat); 
		TextView alamat = (TextView) view.findViewById(R.id.TextViewAlamat);
		TextView titikpoint = (TextView) view.findViewById(R.id.TextViewTitikPoint);
		TextView status = (TextView) view.findViewById(R.id.TextViewStatus);
		// ImageView gambar=(ImageView)vi.findViewById(R.id.gambar);
		

		HashMap<String, String> tempat = new HashMap<String, String>();
		tempat = data.get(position);
		nama.setText(tempat.get(CustomizedDaftarTempat.KEY_NAMA));
		alamat.setText(tempat.get(CustomizedDaftarTempat.KEY_ALAMAT));
		titikpoint.setText(tempat.get(CustomizedDaftarTempat.KEY_TITIKPOINT));
		status.setText(tempat.get(CustomizedDaftarTempat.KEY_STATUS));
		// gambar.setImageDrawable(mission.get(CustomizedDaftarMisi.KEY_THUMB_URL));
		return view;
	}
}
