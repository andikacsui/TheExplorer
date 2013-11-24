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
 

public class ListSavedMissionAdapter extends BaseAdapter{
	private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null; 
 
    public ListSavedMissionAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            vi = inflater.inflate(R.layout.activity_list_saved_mission, null);
 
        TextView judul = (TextView)vi.findViewById(R.id.TextViewNamaSavedMisi); // title
        TextView lokasi = (TextView)vi.findViewById(R.id.TextViewSavedLokasi); // artist name
        TextView keterangan = (TextView)vi.findViewById(R.id.TextViewSavedDeskripsiMisi); // duration
        TextView status = (TextView) vi.findViewById(R.id.TextViewSavedStatusMisi);
        //ImageView gambar=(ImageView)vi.findViewById(R.id.gambar); // thumb image
        
        HashMap<String, String> mission = new HashMap<String, String>();
        mission = data.get(position);
 
        // Setting all values in listview
        judul.setText(mission.get(CustomizedSavedMission.KEY_NAMA));
        lokasi.setText(mission.get(CustomizedSavedMission.KEY_LOKASI));
        keterangan.setText(mission.get(CustomizedSavedMission.KEY_DESKRIPSI));
        status.setText(mission.get(CustomizedSavedMission.KEY_STATUS));
        //gambar.setImageDrawable(mission.get(CustomizedDaftarMisi.KEY_THUMB_URL));
        return vi;
    }
}
