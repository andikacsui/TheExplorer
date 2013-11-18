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
}