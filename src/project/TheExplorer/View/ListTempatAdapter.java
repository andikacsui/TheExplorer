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
import android.widget.Button;
import android.widget.TextView;

public class ListTempatAdapter extends BaseAdapter {
	private Activity activity;
<<<<<<< HEAD
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null; 
 
    public ListTempatAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.activity_list_tempat, null);
 
        TextView nama = (TextView)vi.findViewById(R.id.TextViewNamaTempat); // title
        TextView deskripsi = (TextView)vi.findViewById(R.id.TextViewDeskripsiTempat); // artist name
        TextView status = (TextView)vi.findViewById(R.id.TextViewStatusTempat); // duration
        //ImageView gambar=(ImageView)vi.findViewById(R.id.gambar); // thumb image
        
        HashMap<String, String> mission = new HashMap<String, String>();
        mission = data.get(position);
 
        // Setting all values in listview
        nama.setText(mission.get(CustomizedDaftarTempat.KEY_NAMAX));
        deskripsi.setText(mission.get(CustomizedDaftarTempat.KEY_DESKRIPSIX));
        status.setText(mission.get(CustomizedDaftarTempat.KEY_STATUSX));
        
        //gambar.setImageDrawable(mission.get(CustomizedDaftarMisi.KEY_THUMB_URL));
        return vi;
    }
=======
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;

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
		// ImageView gambar=(ImageView)vi.findViewById(R.id.gambar); // thumb
		// image

		HashMap<String, String> mission = new HashMap<String, String>();
		mission = data.get(position);

		// Setting all values in listview
		nama.setText(mission.get(CustomizedDaftarTempat.KEY_NAMA));
		deskripsi.setText(mission.get(CustomizedDaftarTempat.KEY_DESKRIPSI));
		status.setText(mission.get(CustomizedDaftarTempat.KEY_STATUS));

		// gambar.setImageDrawable(mission.get(CustomizedDaftarMisi.KEY_THUMB_URL));
		return vi;
	}
>>>>>>> ee18b83187bd854fa4a9ca823bae157dafa91be3
}
