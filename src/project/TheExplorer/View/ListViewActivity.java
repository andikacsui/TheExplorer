package project.TheExplorer.View;


import project.TheExplorer.Controller.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
public class ListViewActivity extends ListActivity {
	
	static int idMisi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        // storing string resources into Array
        String[] daftar_misi = getResources().getStringArray(R.array.daftar_misi);
         
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.listview_misi, R.id.label, daftar_misi));
         
        ListView lv = getListView();
 
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item 
              String namaMisi = ((TextView) view).getText().toString();
               
              
              if(namaMisi.equals("Menjelajah Jogja")) idMisi = 1;
              else if(namaMisi.equals("Menjelajah Jakarta")) idMisi = 2;
              else if(namaMisi.equals("Menjelajah Bali")) idMisi = 3;
              else if(namaMisi.equals("Menjelajah Sumatera Barat")) idMisi = 4;
              else if(namaMisi.equals("Menjelajah Nusa Tenggara Barat")) idMisi = 5;
              
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), MissionOverview.class);
              // sending data to new activity
              i.putExtra("namaMisi", idMisi);
              startActivity(i);
             
          }
        });
    }
}