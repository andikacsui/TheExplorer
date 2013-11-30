package project.TheExplorer.Controller;

import project.TheExplorer.Database.DatabaseHelper;
import project.TheExplorer.Model.Penjelajah;
import android.content.Context;

public class PenjelajahHelper {
	public static DatabaseHelper db;
	public static String temp;
	public static boolean IsExist;
	public static Penjelajah penjelajah;
	public static int skor = 0;

	public static String GetPenjelajahName(Context context) {
		db = new DatabaseHelper(context);
		temp = db.GetPenjelajah().getUsername();
		return temp;
	}

	public static void AddPenjelajah(Context context, String username) {
		db = new DatabaseHelper(context);
		db.AddPenjelajah(username);
	}

	public static boolean IsPenjelajahExist(Context context) {
		db = new DatabaseHelper(context);
		IsExist = db.IsPenjelajahExist();
		return IsExist;
	}

	public static Penjelajah GetPenjelajah(Context context) {
		db = new DatabaseHelper(context);
		penjelajah = db.GetPenjelajah();
		return penjelajah;
	}

	public static void UpdateSkor(Context context, int skor1) {
		db = new DatabaseHelper(context);
		penjelajah = db.GetPenjelajah();
		skor = penjelajah.getSkor() + skor1;
		db.UpdatePenjelajahSkor(skor);
	}
}