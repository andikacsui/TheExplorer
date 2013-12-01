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
	public static double distance;

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
			if (tempat.getMisiID() == misiID) {
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

	public static Tempat GetTempatByID(Context context, int ID) {
		db = new DatabaseHelper(context);
		tempat = db.GetTempatByID(ID);
		return tempat;
	}

	public static void UpdateStatus(Context context, int ID) {
		db = new DatabaseHelper(context);
		Tempat tempat = db.GetTempatByID(ID);
		db.UpdateStatusTempat(ID, tempat.getNama(), tempat.getDeskripsi(),
				tempat.getPoint(), tempat.getLatitude(), tempat.getLongitude(),
				tempat.getFoto(), 1, tempat.getMisiID());
	}

	public static void CheckInTempat(Context context, int ID, long lat1,
			long long1, long lat2, long long2) {
		int skor = 0;
		distance = GPSTracker.getDistance(lat1, long1, lat2, long2);
		if (distance <= 500) {
			TempatHelper.UpdateStatus(context, ID);
			skor = TempatHelper.GetTempatByID(context, ID).getPoint();
			PenjelajahHelper.UpdateSkor(context, skor);
			MisiHelper.UpdateStatusMisi(context,
					TempatHelper.GetTempatByID(context, ID).getMisiID());
		}
	}

}
