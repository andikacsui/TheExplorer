package project.TheExplorer.View;

import java.util.ArrayList;
import java.util.HashMap;

import project.TheExplorer.Controller.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.Resources;

public class ListTempatAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	private static Resources res;
	static int counter = 0;

	public ListTempatAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
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

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.activity_list_tempat, null);

		TextView nama = (TextView) vi.findViewById(R.id.TextViewNamaTempat); // title
		TextView deskripsi = (TextView) vi
				.findViewById(R.id.TextViewDeskripsiTempat); // artist name
		TextView status = (TextView) vi.findViewById(R.id.TextViewStatusTempat); // duration
		ImageView gambar=(ImageView)vi.findViewById(R.id.gambar_tempat); // thumb
		// image

		HashMap<String, String> mission = new HashMap<String, String>();
		mission = data.get(position);
		// Setting all values in listview
		nama.setText(mission.get(CustomizedDaftarTempat.KEY_NAMA));
		deskripsi.setText(mission.get(CustomizedDaftarTempat.KEY_DESKRIPSI));
		status.setText(mission.get(CustomizedDaftarTempat.KEY_STATUS));
		
		/* Tambahin foto */
		gambar.setImageResource(Integer.parseInt(mission.get(CustomizedDaftarTempat.KEY_FOTO_PATH)));
		
		
		
		// gambar.setImageDrawable(mission.get(CustomizedDaftarMisi.KEY_THUMB_URL));
		return vi;
	}
}
