package project.TheExplorer.Controller;

import java.util.ArrayList;

import project.TheExplorer.Database.DatabaseHelper;
import project.TheExplorer.Model.Penjelajah;
import project.TheExplorer.Model.Misi;
import android.content.Context;

public class PenjelajahHelper {
	public static DatabaseHelper db;
	public static String temp;
	public static boolean IsExist;
	public static Penjelajah penjelajah;
	public static int skor = 0;
	public static ArrayList<Misi> ListMisi, ListBadge;

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
		db.UpdatePenjelajahSkor(penjelajah.getUsername(), skor);
	}

	public static void UpdatePenjelajahUsername(Context context, String username) {
		db = new DatabaseHelper(context);
		Penjelajah p = db.GetPenjelajah();
		db.UpdatePenjelajahUsername(username, p.getSkor());
	}

	public static int GetJumlahBadge(Context context) {
		db = new DatabaseHelper(context);
		ListMisi = MisiHelper.GetSavedMission(context);
		int count = 0;
		for (int i = 0; i < ListMisi.size(); i++) {
			if (ListMisi.get(i).getStatus() == 1) {
				count = count + 1;
			}
		}
		return count;
	}
}