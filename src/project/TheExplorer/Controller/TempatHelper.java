package project.TheExplorer.Controller;

import java.util.ArrayList;

import project.TheExplorer.Database.DatabaseHelper;
import project.TheExplorer.Model.Tempat;
import android.content.Context;

public class TempatHelper {
	public static DatabaseHelper db;
	public static ArrayList<Tempat> temp = new ArrayList<Tempat>();
	public static ArrayList<Tempat> DaftarTempat = new ArrayList<Tempat>();
	public static Tempat tempat;

	public static void AddTempat(Context context) {
		db = new DatabaseHelper(context);
		db.AddTempat();
	}

	public static ArrayList<Tempat> GetListTempatByMisi(Context context,
			int misiID) {
		db = new DatabaseHelper(context);
		temp = db.getListTempat();
		for (int i = 0; i < temp.size(); i++) {
			tempat = temp.get(i);
			if (tempat.getMisiID() == 1) {
				DaftarTempat.add(tempat);
			}
		}
		return DaftarTempat;
	}

	public static ArrayList<Tempat> GetListTempat(Context context) {
		db = new DatabaseHelper(context);
		DaftarTempat = db.getListTempat();
		return DaftarTempat;
	}

	public static Tempat GetTempat(Context context) {
		db = new DatabaseHelper(context);
		tempat = db.GetTempat();
		return tempat;

	}

}
