package project.TheExplorer.Controller;

import java.util.ArrayList;

import project.TheExplorer.Database.DatabaseHelper;
import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Tempat;
import android.content.Context;
import android.util.Log;

public class MisiHelper {
	public static DatabaseHelper db;
	public static ArrayList<Misi> DaftarMisi;
	public static ArrayList<Tempat> DaftarTempat;
	public static String[] DaftarNamaMisi;
	public static Misi temp;
	public static String temp1;
	public static boolean status = true;

	public static ArrayList<Misi> GetListMisi(Context context) {
		db = new DatabaseHelper(context);
		DaftarMisi = db.getListMisi();
		return DaftarMisi;
	}

	public static void AddMisi(Context context) {
		db = new DatabaseHelper(context);
		db.AddMisi();
	}

	public static String GetMisiName(Context context) {
		db = new DatabaseHelper(context);
		temp1 = db.GetMisi().getNama();
		return temp1;
	}

	public static ArrayList<Misi> GetSavedMission(Context context) {
		db = new DatabaseHelper(context);

		ArrayList<Misi> Saved = new ArrayList<Misi>();
		DaftarMisi = db.getListMisi();
		for (int j = 0; j < DaftarMisi.size(); j++) {
			temp = DaftarMisi.get(j);
			if (temp.getPenjelajahID() == 1) {
				Saved.add(temp);
			}
		}
		return Saved;
	}

	public static void UpdateStatusMisi(Context context, int MisiID) {
		DaftarTempat = TempatHelper.GetListTempatByMisi(context, MisiID);
		for (int i = 0; i < DaftarTempat.size(); i++) {
			if (DaftarTempat.get(i).getStatus() == 0) {
				status = false;
			}
		}
		if (status = true) {
			db = new DatabaseHelper(context);
			db.UpdateStatusMisi(MisiID);
		}
	}
	public static void JoinMission(Context context, int MisiID){
		db = new DatabaseHelper(context);
		db.UpdatePenjelajahMisi(MisiID);
	}
}