package project.TheExplorer.Database;

import java.util.ArrayList;

import project.TheExplorer.Model.Misi;
import project.TheExplorer.Model.Penjelajah;
import project.TheExplorer.Model.Tempat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "TheExplorerMissionDatabase";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE PENJELAJAH (id integer primary key, Username text, Password text, skor integer, LastCheckIn text)");
		db.execSQL("CREATE TABLE MISI (id integer primary key, nama text, deskripsi text, lokasi text, foto text, status text, badge text, penjelajahID integer)");
		db.execSQL("CREATE TABLE TEMPAT (id integer primary key, nama text, alamat text, TitikPoint text, Foto text, Status integer, MisiID integer)");
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
			value.put("id", 1);
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
			value1.put("id", "1");
			value1.put("nama", "Menjelajah Jogja");
			value1.put(
					"deskripsi",
					"Jogjakarta adalah Daerah Istimewa yang terletak dekat Provinsi Jawa Tengah. Jogjakarta terkenal dengan keindahan alamnya, kekayaan seni dan tradisi dan warisan budaya, hingga berwisata kuliner. Inilah sebabnya mengapa Jogja menjadi tujuan wisata paling sering dikunjungi kedua di Indonesia setelah Bali");
			value1.put("lokasi", "DI Jogjakarta, Indonesia");
			value1.put("foto", "@drawable/splash");
			value1.put("status", "0");
			value1.put("badge", "Jogja");
			value1.put("penjelajahID", "0");
			db.insertOrThrow("MISI", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("id", "2");
			value2.put("nama", "Menjelajah Jakarta");
			value2.put(
					"deskripsi",
					"Jakarta adalah ibukota negara Indonesia. Jakarta menjadi pusat pemerintahan yang mengatur keuangan, bisnis, politik dan ekonomi karena di Jakarta tempat bertemunya orang dari seluruh Indonesia. Jakarta telah memikat orang dari segala aspek kehidupan.. Oleh karenanya, tidak heran jika apapun yang terjadi di Jakarta menjadi perhatian nasional dan merupakan pusat roda sejarah dan kehidupan modern Indonesia");
			value2.put("lokasi", "DKI Jakarta, Indonesia");
			value2.put("foto", "xxxx");
			value2.put("status", "0");
			value2.put("badge", "Jakarta");
			value2.put("penjelajahID", "0");
			db.insertOrThrow("MISI", null, value2);

			ContentValues value3 = new ContentValues();
			value3.put("id", "3");
			value3.put("nama", "Menjelajah Bali");
			value3.put(
					"deskripsi",
					"Bali adalah tujuan wisata favorit wisatawan lokal maupun mancanegara. Pulau indah ini terkenal karena memiliki pantai yang indah, pemandangan yang menakjubkan, souvenir yang menarik, serta adat dan kebudayaan yang menawan");
			value3.put("lokasi", "Bali, Indonesia");
			value3.put("foto", "xxxx");
			value3.put("status", "0");
			value3.put("badge", "Bali");
			value3.put("penjelajahID", "0");
			db.insertOrThrow("MISI", null, value3);

			ContentValues value4 = new ContentValues();
			value4.put("id", "4");
			value4.put("nama", "Menjelajah Sumatra Barat");
			value4.put(
					"deskripsi",
					"dataran rendah di pantai barat, serta dataran tinggi vulkanik yang dibentuk oleh Bukit Barisan yang membentang dari barat laut ke tenggara. Sumatera Barat merupakan tempat yang tepat untuk berpetualang hingga ke daerah pedalaman, mulai dari alam bebas, satwa liar, pulau, pantai, hingga hutan hujan tropis. Itu karena inilah salah satu provinsi di Indonesia yang kaya dengan sumber keanekaragaman hayati dan keindahan alam.");
			value4.put("lokasi", "Sumatra Barat,Indonesia");
			value4.put("foto", "xxxx");
			value4.put("status", "0");
			value4.put("badge", "Sumatra Barat");
			value4.put("penjelajahID", "0");
			db.insertOrThrow("MISI", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", "5");
			value5.put("nama", "Menjelajah Aceh");
			value5.put(
					"deskripsi",
					"Aceh merupakan salah satu daerah di Nusantara yang masyarakatnya bersifat multietnis bercirikan Islam. Penduduk Aceh sering disebutkan merupakan keturunan berbagai kaum dan bangsa. Seperti halnya kata ACEH sering diidentikkan dengan kepanjangan dari Arab, China, Eropa, Hindia dimana memang secara fisik menunjukkan ciri-ciri orang Arab, India, Eropa dan Cina. Aceh merupakan daerah istimewa di Indonesia yang terletak paling ujung utara Pulau Sumatra.");
			value5.put("lokasi", "Aceh, Indonesia");
			value5.put("foto", "xxxx");
			value5.put("status", "0");
			value5.put("badge", "Aceh");
			value5.put("penjelajahID", "0");
			db.insertOrThrow("MISI", null, value5);
			db.close();
		}
	}

	public Tempat GetTempat() {
		int ID = 0;
		String nama = "";
		String alamat = "";
		String titikPoint = "";
		String foto = "";
		int status = 0;
		int misiID = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);
		cursor.moveToFirst();
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		alamat = cursor.getString(2);
		titikPoint = cursor.getString(3);
		foto = cursor.getString(4);
		status = cursor.getInt(5);
		misiID = cursor.getInt(6);
		cursor.close();
		db.close();
		tempat = new Tempat(ID, nama, alamat, titikPoint, foto, status, misiID);
		return tempat;
	}

	public boolean IsTempatExist() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);

		if (cursor.getCount() != 0) {
			db.close();
			return true;
		} else {
			db.close();
			return false;
		}
	}

	public void AddTempat() {
		if (IsTempatExist() == false) {
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues value1 = new ContentValues();
			value1.put("id", 1);
			value1.put("nama", "Malioboro");
			value1.put("alamat",
					"Malioboro kaya akan keindahan alam dan budayanya");
			value1.put("TitikPoint", "aaaaa");
			value1.put("Foto", "xxxx");
			value1.put("Status", 0);
			value1.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("id", 2);
			value2.put("nama", "Candi Borobudur");
			value2.put("alamat",
					"Borobudur kaya akan keindahan alam dan budayanya");
			value2.put("TitikPoint", "aaaaca");
			value2.put("Foto", "xxxx");
			value2.put("Status", 0);
			value2.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value2);

			ContentValues value3 = new ContentValues();
			value3.put("id", 3);
			value3.put("nama", "Candi Prambanan");
			value3.put("alamat",
					"Prambanan kaya akan keindahan alam dan budayanya");
			value3.put("TitikPoint", "aaasssaaca");
			value3.put("Foto", "xxxx");
			value3.put("Status", 0);
			value3.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value3);

			ContentValues value4 = new ContentValues();
			value4.put("id", 4);
			value4.put("nama", "Keraton Jogja");
			value4.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value4.put("TitikPoint", "sdd");
			value4.put("Foto", "xxxx");
			value4.put("Status", "0");
			value4.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", 5);
			value5.put("nama", "Pantai Parangtritis");
			value5.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value5.put("TitikPoint", "aaaassca");
			value5.put("Foto", "xxxx");
			value5.put("Status", 0);
			value5.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value5);
			db.close();
			
			ContentValues value6 = new ContentValues();
			value6.put("id",6);
			value6.put("nama", "Candi Ratu Boko");
			value6.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value6.put("TitikPoint", "akjkl");
			value6.put("Foto","xxxx");
			value6.put("Status",0);
			value6.put("Foto", 1); 
			value6.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value6);
			db.close();
			
			ContentValues value7 = new ContentValues();
			value7.put("id",7);
			value7.put("nama", "Taman Sari");
			value7.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value7.put("TitikPoint", "aaacs");
			value7.put("Foto","xxxx");
			value7.put("Status",0);
			value7.put("Foto", 1); 
			value7.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value7);
			db.close();
			
			ContentValues value8 = new ContentValues();
			value8.put("id",8);
			value8.put("nama", "Gunung Merapi");
			value8.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value8.put("TitikPoint", "baaacs");
			value8.put("Foto","xxxx");
			value8.put("Status",0);
			value8.put("Foto", 1); 
			value8.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value8);
			db.close();
			
			ContentValues value9 = new ContentValues();
			value9.put("id",9);
			value9.put("nama", "Goa Pindul");
			value9.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value9.put("TitikPoint", "abfacs");
			value9.put("Foto","xxxx");
			value9.put("Status",0);
			value9.put("Foto", 1); 
			value9.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value9);
			db.close();
			
			ContentValues value10 = new ContentValues();
			value10.put("id",10);
			value10.put("nama", "Kota Gede");
			value10.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value10.put("TitikPoint", "aaacs");
			value10.put("Foto","xxxx");
			value10.put("Status",0);
			value10.put("Foto", 1); 
			value10.put("MisiID", 1); 
			db.insertOrThrow("TEMPAT", null, value10);
			db.close();
			
			ContentValues value11 = new ContentValues();
			value11.put("id",11);
			value11.put("nama", "Monumen Nasional");
			value11.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value11.put("TitikPoint", "aklcs");
			value11.put("Foto","xxxx");
			value11.put("Status",0);
			value11.put("Foto", 1); 
			value11.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value11);
			db.close();
			
			ContentValues value12 = new ContentValues();
			value12.put("id",12);
			value12.put("nama", "Taman Mini Indonesia Indah");
			value12.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value12.put("TitikPoint", "apoacs");
			value12.put("Foto","xxxx");
			value12.put("Status",0);
			value12.put("Foto", 1); 
			value12.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value12);
			db.close();
			
			ContentValues value13 = new ContentValues();
			value13.put("id",13);
			value13.put("nama", "Kota Tua Batavia");
			value13.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value13.put("TitikPoint", "apoacs");
			value13.put("Foto","xxxx");
			value13.put("Status",0);
			value13.put("Foto", 1); 
			value13.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value13);
			db.close(); 
			
			ContentValues value14 = new ContentValues();
			value14.put("id",14);
			value14.put("nama", "Ancol");
			value14.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value14.put("TitikPoint", "lkoacs");
			value14.put("Foto","xxxx");
			value14.put("Status",0);
			value14.put("Foto", 1); 
			value14.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value14);
			db.close(); 
			
			ContentValues value15 = new ContentValues();
			value15.put("id",15);
			value15.put("nama", "Kemang");
			value15.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value15.put("TitikPoint", "lkoacs");
			value15.put("Foto","xxxx");
			value15.put("Status",0);
			value15.put("Foto", 1); 
			value15.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value15);
			db.close(); 
			
			ContentValues value16 = new ContentValues();
			value16.put("id",16);
			value16.put("nama", "Komplek Gelora Bung Karno");
			value16.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value16.put("TitikPoint", "poics");
			value16.put("Foto","xxxx");
			value16.put("Status",0);
			value16.put("Foto", 1); 
			value16.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value16);
			db.close(); 
			
			ContentValues value17 = new ContentValues();
			value17.put("id",17);
			value17.put("nama", "Pulau Tidung Kep. Seribu");
			value17.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value17.put("TitikPoint", "poics");
			value17.put("Foto","xxxx");
			value17.put("Status",0);
			value17.put("Foto", 1); 
			value17.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value17);
			db.close(); 
			
			ContentValues value18 = new ContentValues();
			value18.put("id",18);
			value18.put("nama", "Pelabuhan Sunda Kelapa");
			value18.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value18.put("TitikPoint", "poics");
			value18.put("Foto","xxxx");
			value18.put("Status",0);
			value18.put("Foto", 1); 
			value18.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value18);
			db.close(); 
			
			ContentValues value19 = new ContentValues();
			value19.put("id",19);
			value19.put("nama", "Museum Fatahillah");
			value19.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value19.put("TitikPoint", "poics");
			value19.put("Foto","xxxx");
			value19.put("Status",0);
			value19.put("Foto", 1); 
			value19.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value19);
			db.close();
			
			ContentValues value20 = new ContentValues();
			value20.put("id",20);
			value20.put("nama", "Museum Bank Indonesia");
			value20.put("alamat",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value20.put("TitikPoint", "poics");
			value20.put("Foto","xxxx");
			value20.put("Status",0);
			value20.put("Foto", 1); 
			value20.put("MisiID", 2); 
			db.insertOrThrow("TEMPAT", null, value20);
			db.close();
			
		}
	}

	public ArrayList<Tempat> getListTempat() {
		ArrayList<Tempat> ListTempat = new ArrayList<Tempat>();
		int ID = 0;
		String nama = "";
		String alamat = "";
		String TitikPoint = "";
		String Foto = "";
		int Status = 0;
		int MisiID = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			ID = cursor.getInt(0);
			nama = cursor.getString(1);
			alamat = cursor.getString(2);
			TitikPoint = cursor.getString(3);
			Foto = cursor.getString(4);
			Status = cursor.getInt(5);
			MisiID = cursor.getInt(6);
			tempat = new Tempat(ID, nama, alamat, TitikPoint, Foto, Status,
					MisiID);
			ListTempat.add(tempat);
		}
		cursor.close();
		db.close();
		return ListTempat;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}