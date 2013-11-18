package project.TheExplorer.Controller;

import java.util.ArrayList;

import project.TheExplorer.Database.DatabaseHelper;
import project.TheExplorer.Model.Misi;
import android.content.Context;

public class MisiHelper {
	public static DatabaseHelper db;
	public static ArrayList<Misi> DaftarMisi;
	public static String[] DaftarNamaMisi;
	public static Misi temp;
	public static String temp1;

	public static String GetListMisiName(Context context) {
		db = new DatabaseHelper(context);
		DaftarMisi = db.getListMisi();
		for (int i = 0; i < DaftarMisi.size(); i++) {
			temp = DaftarMisi.get(i);
			if (temp != null) {
				temp1 = temp.getNama();
				if (temp1 != null) {
					DaftarNamaMisi[i] = temp1;
				}
			}
		}
		return "aku";
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
}