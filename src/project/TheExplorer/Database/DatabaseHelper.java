package project.TheExplorer.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Penjelajah;
import project.TheExplorer.Model.Tempat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "TheExplorerMissionDatabase";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE PENJELAJAH (id integer primary key autoincrement , Username text, Password text, skor integer, LastCheckIn text)");
		db.execSQL("CREATE TABLE MISI (id integer primary key autoincrement , nama text, deskripsi text, lokasi text, foto text, status text, badge text, penjelajahID integer)");
		db.execSQL("CREATE TABLE TEMPAT (id integer primary key autoincrement , nama text, deskripsi text, TitikPoint text, Foto text, Status text, MisiID integer)");
	}

	public Penjelajah GetPenjelajah() {
		int ID = 0;
		String username = "";
		String twitter = "";
		int skor = 0;
		String LastCheckIn = "";
		Penjelajah penjelajah;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM PENJELAJAH", null);
		cursor.moveToFirst();
		ID = cursor.getInt(0);
		username = cursor.getString(1);
		twitter = cursor.getString(2);
		skor = cursor.getInt(3);
		LastCheckIn = cursor.getString(4);
		cursor.close();
		db.close();
		penjelajah = new Penjelajah(ID, username, twitter, skor, LastCheckIn);
		return penjelajah;
	}

	public void AddPenjelajah(String Username, String Password) {
		if (IsPenjelajahExist() == false) {
			ContentValues value = new ContentValues();
			SQLiteDatabase db = this.getWritableDatabase();
			value.put("Username", Username);
			value.put("Password", Password);
			value.put("Skor", 0);
			value.put("LastCheckIn", "0");
			db.insertOrThrow("PENJELAJAH", null, value);
			db.close();
		}
	}

	public boolean IsPenjelajahExist() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM PENJELAJAH", null);

		if (cursor.getCount() == 0) {
			db.close();
			return false;
		} else {
			db.close();
			return true;
		}
	}

	public Misi GetMisi() {
		int ID = 0;
		String nama = "";
		String deskripsi = "";
		String lokasi = "";
		String foto = "";
		String status = "";
		String badge = "";
		int penjelajahID = 0;
		Misi misi;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM MISI", null);
		cursor.moveToFirst();
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		lokasi = cursor.getString(3);
		foto = cursor.getString(4);
		status = cursor.getString(5);
		badge = cursor.getString(6);
		penjelajahID = cursor.getInt(7);
		cursor.close();
		db.close();
		misi = new Misi(ID, nama, deskripsi, lokasi, foto, status, badge,
				penjelajahID);
		return misi;
	}

	public ArrayList<Misi> getListMisi() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Misi> ListMisi = new ArrayList<Misi>();
		int ID = 0;
		String nama = "";
		String deskripsi = "";
		String lokasi = "";
		String foto = "";
		String status = "";
		String badge = "";
		int penjelajahID = 0;
		Misi misi;

		Cursor cursor = db.rawQuery("SELECT * FROM MISI", null);
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			ID = cursor.getInt(0);
			nama = cursor.getString(1);
			deskripsi = cursor.getString(2);
			lokasi = cursor.getString(3);
			foto = cursor.getString(4);
			status = cursor.getString(5);
			badge = cursor.getString(6);
			penjelajahID = cursor.getInt(7);
			misi = new Misi(ID, nama, deskripsi, lokasi, foto, status, badge,
					penjelajahID);
			ListMisi.add(misi);
		}
		cursor.close();
		db.close();
		return ListMisi;
	}

	public boolean IsMisiExist() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM MISI", null);

		if (cursor.getCount() != 0) {
			db.close();
			return true;
		} else {
			db.close();
			return false;
		}
	}

	public void AddMisi() {
		if (IsMisiExist() == false) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues value1 = new ContentValues();
			value1.put("nama", "Menjelajah Jogja");
			value1.put("deskripsi",
					"Jogja kaya akan keindahan alam dan budayanya");
			value1.put("lokasi", "Jogjakarta");
			value1.put("foto", "xxxx");
			value1.put("status", "0");
			value1.put("badge", "Jogja");
			value1.put("penjelajahID", "1");
			db.insertOrThrow("MISI", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("nama", "Menjelajah Jakarta");
			value2.put("deskripsi", "Jakarta ibukota negara");
			value2.put("lokasi", "Jakarta");
			value2.put("foto", "xxxx");
			value2.put("status", "0");
			value2.put("badge", "Jakarta");
			value2.put("penjelajahID", "1");
			db.insertOrThrow("MISI", null, value2);
			db.close();
		}
	}

	public Tempat GetTempat() {
		int ID = 0;
		String nama = "";
		String deskripsi = "";
		String titikPoint = "";
		String foto = "";
		String status = "";
		int misiID = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		titikPoint = cursor.getString(3);
		foto = cursor.getString(4);
		status = cursor.getString(5);
		misiID = cursor.getInt(6);
		cursor.close();
		db.close();
		tempat = new Tempat(ID, nama, deskripsi, titikPoint, foto, status,
				misiID);
		return tempat;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}