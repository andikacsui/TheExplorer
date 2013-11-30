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
		db.execSQL("CREATE TABLE MISI (id integer primary key, nama text, deskripsi text, lokasi text, foto text, status integer, badge text, penjelajahID integer)");
		db.execSQL("CREATE TABLE TEMPAT (id integer primary key, nama text, deskripsi text, latitude double,longitude double, Foto text, Status integer, MisiID integer)");
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
		int status = 0;
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
		status = cursor.getInt(5);
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
		int status = 0;
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
			status = cursor.getInt(5);
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
			value1.put("id", 1);
			value1.put("nama", "Menjelajah Jogja");
			value1.put(
					"deskripsi",
					"Jogjakarta adalah Daerah Istimewa yang terletak dekat Provinsi Jawa Tengah. Jogjakarta terkenal dengan keindahan alamnya, kekayaan seni dan tradisi dan warisan budaya, hingga berwisata kuliner. Inilah sebabnya mengapa Jogja menjadi tujuan wisata paling sering dikunjungi kedua di Indonesia setelah Bali");
			value1.put("lokasi", "DI Jogjakarta, Indonesia");
			value1.put("foto", "@drawable/splash");
			value1.put("status", 0);
			value1.put("badge", "Jogja");
			value1.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("id", 2);
			value2.put("nama", "Menjelajah Jakarta");
			value2.put(
					"deskripsi",
					"Jakarta adalah ibukota negara Indonesia. Jakarta menjadi pusat pemerintahan yang mengatur keuangan, bisnis, politik dan ekonomi karena di Jakarta tempat bertemunya orang dari seluruh Indonesia. Jakarta telah memikat orang dari segala aspek kehidupan.. Oleh karenanya, tidak heran jika apapun yang terjadi di Jakarta menjadi perhatian nasional dan merupakan pusat roda sejarah dan kehidupan modern Indonesia");
			value2.put("lokasi", "DKI Jakarta, Indonesia");
			value2.put("foto", "xxxx");
			value2.put("status", 0);
			value2.put("badge", "Jakarta");
			value2.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value2);

			ContentValues value3 = new ContentValues();
			value3.put("id", 3);
			value3.put("nama", "Menjelajah Bali");
			value3.put(
					"deskripsi",
					"Bali adalah tujuan wisata favorit wisatawan lokal maupun mancanegara. Pulau indah ini terkenal karena memiliki pantai yang indah, pemandangan yang menakjubkan, souvenir yang menarik, serta adat dan kebudayaan yang menawan");
			value3.put("lokasi", "Bali, Indonesia");
			value3.put("foto", "xxxx");
			value3.put("status", 0);
			value3.put("badge", "Bali");
			value3.put("penjelajahID", 0);

			db.insertOrThrow("MISI", null, value3);

			ContentValues value4 = new ContentValues();
			value4.put("id", 4);
			value4.put("nama", "Menjelajah Sumatera Barat");
			value4.put(
					"deskripsi",
					"dataran rendah di pantai barat, serta dataran tinggi vulkanik yang dibentuk oleh Bukit Barisan yang membentang dari barat laut ke tenggara. Sumatera Barat merupakan tempat yang tepat untuk berpetualang hingga ke daerah pedalaman, mulai dari alam bebas, satwa liar, pulau, pantai, hingga hutan hujan tropis. Itu karena inilah salah satu provinsi di Indonesia yang kaya dengan sumber keanekaragaman hayati dan keindahan alam.");
			value4.put("lokasi", "Sumatera Barat,Indonesia");
			value4.put("foto", "xxxx");
			value4.put("status", 1);
			value4.put("badge", "Sumatera Barat");
			value4.put("penjelajahID", 1);
			db.insertOrThrow("MISI", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", 5);
			value5.put("nama", "Menjelajah Nusa Tenggara Barat");
			value5.put(
					"deskripsi",
					"Aceh merupakan salah satu daerah di Nusantara yang masyarakatnya bersifat multietnis bercirikan Islam. Penduduk Aceh sering disebutkan merupakan keturunan berbagai kaum dan bangsa. Seperti halnya kata ACEH sering diidentikkan dengan kepanjangan dari Arab, China, Eropa, Hindia dimana memang secara fisik menunjukkan ciri-ciri orang Arab, India, Eropa dan Cina. Aceh merupakan daerah istimewa di Indonesia yang terletak paling ujung utara Pulau Sumatra.");
			value5.put("lokasi", "NTB, Indonesia");
			value5.put("foto", "xxxx");
			value5.put("status", 0);
			value5.put("badge", "NTB");
			value5.put("penjelajahID", 1);
			db.insertOrThrow("MISI", null, value5);
			db.close();
		}
	}

	public Misi GetMisiByID(int MisiID) {
		int ID = 0;
		String nama = "";
		String deskripsi = "";
		String lokasi = "";
		String foto = "";
		int status = 0;
		String badge = "";
		int penjelajahID = 0;
		Misi misi;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM MISI where ID = " + "\" "
				+ MisiID + "\" ", null);
		cursor.moveToFirst();
		ID = MisiID;
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		lokasi = cursor.getString(3);
		foto = cursor.getString(4);
		status = cursor.getInt(5);
		badge = cursor.getString(6);
		penjelajahID = cursor.getInt(7);
		cursor.close();
		db.close();
		misi = new Misi(ID, nama, deskripsi, lokasi, foto, status, badge,
				penjelajahID);
		return misi;

	}

	public void UpdateStatusMisi(int ID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value1 = new ContentValues();
		Misi misi = GetMisiByID(ID);
		value1.put("id", ID);
		value1.put("nama", misi.getNama());
		value1.put("deskripsi", misi.getDeskripsi());
		value1.put("lokasi", misi.getLokasi());
		value1.put("foto", "@drawable/splash");
		value1.put("status", 1);
		value1.put("badge", misi.getBadge());
		value1.put("penjelajahID", misi.getPenjelajahID());

	}

	public Tempat GetTempat() {
		int ID = 0;
		String nama = "";
		String deskripsi = "";
		double latitude = 0;
		double longitude = 0;
		String foto = "";
		int status = 0;
		int misiID = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);
		cursor.moveToFirst();
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		latitude = cursor.getFloat(3);
		longitude = cursor.getFloat(4);
		foto = cursor.getString(5);
		status = cursor.getInt(6);
		misiID = cursor.getInt(7);
		cursor.close();
		db.close();
		tempat = new Tempat(ID, nama, deskripsi, latitude, longitude, foto,
				status, misiID);
		return tempat;
	}

	public Tempat GetTempatByID(int ID) {
		String nama = "";
		String deskripsi = "";
		double latitude = 0;
		double longitude = 0;
		String foto = "";
		int status = 0;
		int misiID = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM MISI where ID = " + "\" "
				+ ID + "\" ", null);
		cursor.moveToFirst();
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		latitude = cursor.getFloat(3);
		longitude = cursor.getFloat(4);
		foto = cursor.getString(5);
		status = cursor.getInt(6);
		misiID = cursor.getInt(7);
		cursor.close();
		db.close();
		tempat = new Tempat(ID, nama, deskripsi, latitude, longitude, foto,
				status, misiID);
		return tempat;
	}

	public void UpdateStatusTempat(int ID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value1 = new ContentValues();
		Tempat tempat = GetTempatByID(ID);
		value1.put("id", ID);
		value1.put("nama", tempat.getNama());
		value1.put("deskripsi", tempat.getDeskripsi());
		value1.put("latitude", tempat.getLatitude());
		value1.put("longitude", tempat.getLongitude());
		value1.put("Foto", tempat.getFoto());
		value1.put("Status", 1);
		value1.put("MisiID", tempat.getMisiID());
		db.update("TEMPAT", value1, "ID = " + "\"" + ID + "\"", null);
		db.close();
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
			value1.put("deskripsi",
					"Malioboro kaya akan keindahan alam dan budayanya");
<<<<<<< HEAD
			value1.put("latitude", "-7.791892");
			value1.put("longitude", "110.365731");
			value1.put("Foto", "xxxx");
=======
			value1.put("latitude", "0");
			value1.put("longitude", "0");
			value1.put("Foto", "@drawable/logo");
>>>>>>> 1cf7a55be8738ef7da181d36ec5658081d300cb8
			value1.put("Status", 0);
			value1.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("id", 2);
			value2.put("nama", "Candi Borobudur");
			value2.put("deskripsi",
					"Borobudur kaya akan keindahan alam dan budayanya");
			value2.put("latitude", "-7.607212");
			value2.put("longitude", "110.203314");
			value2.put("Foto", "xxxx");
			value2.put("Status", 0);
			value2.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value2);

			ContentValues value3 = new ContentValues();
			value3.put("id", 3);
			value3.put("nama", "Candi Prambanan");
			value3.put("deskripsi",
					"Prambanan kaya akan keindahan alam dan budayanya");
			value3.put("latitude", "-7.751901");
			value3.put("longitude", "110.492011");
			value3.put("Foto", "xxxx");
			value3.put("Status", 0);
			value3.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value3);

			ContentValues value4 = new ContentValues();
			value4.put("id", 4);
			value4.put("nama", "Keraton Jogja");
			value4.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value4.put("latitude", "-7.805269");
			value4.put("longitude", "110.364183");
			value4.put("Foto", "xxxx");
			value4.put("Status", "0");
			value4.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", 5);
			value5.put("nama", "Pantai Parangtritis");
			value5.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value5.put("latitude", "-8.021017");
			value5.put("longitude", "110.31815");
			value5.put("Foto", "xxxx");
			value5.put("Status", 0);
			value5.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value5);

			ContentValues value6 = new ContentValues();
			value6.put("id", 6);
			value6.put("nama", "Candi Ratu Boko");
			value6.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value6.put("latitude", "-7.750795");
			value6.put("longitude", "110.49222");
			value6.put("Foto", "xxxx");
			value6.put("Status", 0);
			value6.put("Foto", 1);
			value6.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value6);

			ContentValues value7 = new ContentValues();
			value7.put("id", 7);
			value7.put("nama", "Taman Sari");
			value7.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value7.put("latitude", "-7.780749");
			value7.put("longitude", "110.409945");
			value7.put("Foto", "xxxx");
			value7.put("Status", 0);
			value7.put("Foto", 1);
			value7.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value7);

			ContentValues value8 = new ContentValues();
			value8.put("id", 8);
			value8.put("nama", "Gunung Merapi");
			value8.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value8.put("latitude", "-7.539828");
			value8.put("longitude", "110.445371");
			value8.put("Foto", "xxxx");
			value8.put("Status", 0);
			value8.put("Foto", 1);
			value8.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value8);

			ContentValues value9 = new ContentValues();
			value9.put("id", 9);
			value9.put("nama", "Goa Pindul");
			value9.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value9.put("latitude", "-7.953157");
			value9.put("longitude", "110.597191");
			value9.put("Foto", "xxxx");
			value9.put("Status", 0);
			value9.put("Foto", 1);
			value9.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value9);

			ContentValues value10 = new ContentValues();
			value10.put("id", 10);
			value10.put("nama", "Kota Gede");
			value10.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value10.put("latitude", "-7.824439");
			value10.put("longitude", "110.39609");
			value10.put("Foto", "xxxx");
			value10.put("Status", 0);
			value10.put("Foto", 1);
			value10.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value10);

			ContentValues value11 = new ContentValues();
			value11.put("id", 11);
			value11.put("nama", "Monumen Nasional");
			value11.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value11.put("latitude", "-6.174774");
			value11.put("longitude", "106.827185");
			value11.put("Foto", "xxxx");
			value11.put("Status", 0);
			value11.put("Foto", 1);
			value11.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value11);

			ContentValues value12 = new ContentValues();
			value12.put("id", 12);
			value12.put("nama", "Taman Mini Indonesia Indah");
			value12.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value12.put("latitude", "-6.302438");
			value12.put("longitude", "106.895147");
			value12.put("Foto", "xxxx");
			value12.put("Status", 0);
			value12.put("Foto", 1);
			value12.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value12);

			ContentValues value13 = new ContentValues();
			value13.put("id", 13);
			value13.put("nama", "Kota Tua Batavia");
			value13.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value13.put("latitude", "-6.133216");
			value13.put("longitude", "106.813173");
			value13.put("Foto", "xxxx");
			value13.put("Status", 0);
			value13.put("Foto", 1);
			value13.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value13);

			ContentValues value14 = new ContentValues();
			value14.put("id", 14);
			value14.put("nama", "Ancol");
			value14.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value14.put("latitude", "-6.127242");
			value14.put("longitude", "106.845596");
			value14.put("Foto", "xxxx");
			value14.put("Status", 0);
			value14.put("Foto", 1);
			value14.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value14);

			ContentValues value15 = new ContentValues();
			value15.put("id", 15);
			value15.put("nama", "Universitas Indonesia");
			value15.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value15.put("latitude", "-6.368188");
			value15.put("longitude", "106.829442");
			value15.put("Foto", "xxxx");
			value15.put("Status", 0);
			value15.put("Foto", 1);
			value15.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value15);

			ContentValues value16 = new ContentValues();
			value16.put("id", 16);
			value16.put("nama", "Komplek Gelora Bung Karno");
			value16.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value16.put("latitude", "-6.218591");
			value16.put("longitude", "106.801693");
			value16.put("Foto", "xxxx");
			value16.put("Status", 0);
			value16.put("Foto", 1);
			value16.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value16);

			ContentValues value17 = new ContentValues();
			value17.put("id", 17);
			value17.put("nama", "Pulau Tidung Kep. Seribu");
			value17.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value17.put("latitude", "-5.803193");
			value17.put("longitude", "106.523066");
			value17.put("Foto", "xxxx");
			value17.put("Status", 0);
			value17.put("Foto", 1);
			value17.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value17);

			ContentValues value18 = new ContentValues();
			value18.put("id", 18);
			value18.put("nama", "Pelabuhan Sunda Kelapa");
			value18.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value18.put("latitude", "-6.122633");
			value18.put("longitude", "106.80886");
			value18.put("Foto", "xxxx");
			value18.put("Status", 0);
			value18.put("Foto", 1);
			value18.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value18);

			ContentValues value19 = new ContentValues();
			value19.put("id", 19);
			value19.put("nama", "Museum Fatahillah");
			value19.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value19.put("latitude", "-6.133045");
			value19.put("longitude", "106.813488");
			value19.put("Foto", "xxxx");
			value19.put("Status", 0);
			value19.put("Foto", 1);
			value19.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value19);

			ContentValues value20 = new ContentValues();
			value20.put("id", 20);
			value20.put("nama", "Museum Bank Indonesia");
			value20.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value20.put("latitude", "-6.137824");
			value20.put("longitude", "106.813145");
			value20.put("Foto", "xxxx");
			value20.put("Status", 0);
			value20.put("Foto", 1);
			value20.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value20);

			ContentValues value21 = new ContentValues();
			value21.put("id", 21);
			value21.put("nama", "Pantai Kuta");
			value21.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value21.put("latitude", "-8.723236");
			value21.put("longitude", "115.169992");
			value21.put("Foto", "xxxx");
			value21.put("Status", 0);
			value21.put("Foto", 1);
			value21.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value21);

			ContentValues value22 = new ContentValues();
			value22.put("id", 22);
			value22.put("nama", "Ubud Monkey Forest");
			value22.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value22.put("latitude", "-8.518743");
			value22.put("longitude", "115.258552");
			value22.put("Foto", "xxxx");
			value22.put("Status", 0);
			value22.put("Foto", 1);
			value22.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value22);

			ContentValues value23 = new ContentValues();
			value23.put("id", 23);
			value23.put("nama", "Bali Bird Park");
			value23.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value23.put("latitude", "-8.596637");
			value23.put("longitude", "115.251675");
			value23.put("Foto", "xxxx");
			value23.put("Status", 0);
			value23.put("Foto", 1);
			value23.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value23);

			ContentValues value24 = new ContentValues();
			value24.put("id", 24);
			value24.put("nama", "Pantai Lovina");
			value24.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value24.put("latitude", "-8.160347");
			value24.put("longitude", "115.029981");
			value24.put("Foto", "xxxx");
			value24.put("Status", 0);
			value24.put("Foto", 1);
			value24.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value24);

			ContentValues value25 = new ContentValues();
			value25.put("id", 25);
			value25.put("nama", "Danau Batur-Kintamani");
			value25.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value25.put("latitude", "-8.256682");
			value25.put("longitude", "115.408087");
			value25.put("Foto", "xxxx");
			value25.put("Status", 0);
			value25.put("Foto", 1);
			value25.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value25);

			ContentValues value26 = new ContentValues();
			value26.put("id", 26);
			value26.put("nama", "Pantai Sanur");
			value26.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value26.put("latitude", "-8.674748");
			value26.put("longitude", "115.263827");
			value26.put("Foto", "xxxx");
			value26.put("Status", 0);
			value26.put("Foto", 1);
			value26.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value26);

			ContentValues value27 = new ContentValues();
			value27.put("id", 27);
			value27.put("nama", "Nusa Dua");
			value27.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value27.put("latitude", "-8.794578");
			value27.put("longitude", "115.216577");
			value27.put("Foto", "xxxx");
			value27.put("Status", 0);
			value27.put("Foto", 1);
			value27.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value27);

<<<<<<< HEAD
=======
<<<<<<< HEAD
			ContentValues value28 = new ContentValues();
			value28.put("id", 28);
			value28.put("nama", "Tanah Lot");
			value28.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value28.put("latitude", "-8.681494");
			value28.put("longitude", "115.205247");
			value28.put("Foto", "xxxx");
			value28.put("Status", 0);
			value28.put("Foto", 1);
			value28.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value28);

			ContentValues value29 = new ContentValues();
			value29.put("id", 29);
			value29.put("nama", "Pantai Dreamland");
			value29.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value29.put("latitude", "-8.779225");
			value29.put("longitude", "115.166702");
			value29.put("Foto", "xxxx");
			value29.put("Status", 0);
			value29.put("Foto", 1);
			value29.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value29);

			ContentValues value30 = new ContentValues();
			value30.put("id", 30);
			value30.put("nama", "Jimbaran");
			value30.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value30.put("latitude", "-8.789998");
			value30.put("longitude", "115.159278");
			value30.put("Foto", "xxxx");
			value30.put("Status", 0);
			value30.put("Foto", 1);
			value30.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value30);

=======
>>>>>>> 1cf7a55be8738ef7da181d36ec5658081d300cb8
>>>>>>> 3ffb008e9a2c9d7eb4e68d5135d74d1f7437d423
			ContentValues value31 = new ContentValues();
			value31.put("id", 31);
			value31.put("nama", "Jam Gadang");
			value31.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value31.put("latitude", "-0.301393");
			value31.put("longitude", "100.365361");
			value31.put("Foto", "xxxx");
			value31.put("Status", 0);
			value31.put("Foto", 1);
			value31.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value31);

			
			ContentValues value35 = new ContentValues();
			value35.put("id", 35);
			value35.put("nama", "Pantai Air Manis");
			value35.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value35.put("latitude", "-0.60149");
			value35.put("longitude", "100.539901");
			value35.put("Foto", "xxxx");
			value35.put("Status", 0);
			value35.put("Foto", 1);
			value35.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value35);

			ContentValues value36 = new ContentValues();
			value36.put("id", 36);
			value36.put("nama", "Danau Singkarak");
			value36.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value36.put("latitude", "-0.60149");
			value36.put("longitude", "100.539901");
			value36.put("Foto", "xxxx");
			value36.put("Status", 0);
			value36.put("Foto", 1);
			value36.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value36);

			ContentValues value37 = new ContentValues();
			value37.put("id", 37);
			value37.put("nama", "Istana Pagaruyung");
			value37.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value37.put("latitude", "-0.307272");
			value37.put("longitude", "100.200752");
			value37.put("Foto", "xxxx");
			value37.put("Status", 0);
			value37.put("Foto", 1);
			value37.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value37);

			ContentValues value38 = new ContentValues();
			value38.put("id", 38);
			value38.put("nama", "Danau Maninjau");
			value38.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value38.put("latitude", "-0.307272");
			value38.put("longitude", "100.200752");
			value38.put("Foto", "xxxx");
			value38.put("Status", 0);
			value38.put("Foto", 1);
			value38.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value38);
			
			ContentValues value40 = new ContentValues();
			value40.put("id", 40);
			value40.put("nama", "Gili Terawangan");
			value40.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value40.put("latitude", "-8.346199");
			value40.put("longitude", "116.038099");
			value40.put("Foto", "xxxx");
			value40.put("Status", 0);
			value40.put("Foto", 1);
			value40.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value40);

			ContentValues value41 = new ContentValues();
			value41.put("id", 42);
			value41.put("nama", "Gili Meno Island");
			value41.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value41.put("latitude", "-8.34501");
			value41.put("longitude", "116.055609");
			value41.put("Foto", "xxxx");
			value41.put("Status", 0);
			value41.put("Foto", 1);
			value41.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value41);

			ContentValues value42 = new ContentValues();
			value42.put("id", 42);
			value42.put("nama", "Gili Air Island");
			value42.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value42.put("latitude", "-8.356814");
			value42.put("longitude", "116.081444");
			value42.put("Foto", "xxxx");
			value42.put("Status", 0);
			value42.put("Foto", 1);
			value42.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value42);

			ContentValues value44 = new ContentValues();
			value44.put("id", 44);
			value44.put("nama", "Pulau Moyo");
			value44.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value44.put("latitude", "-8.197898");
			value44.put("longitude", "117.578216");
			value44.put("Foto", "xxxx");
			value44.put("Status", 0);
			value44.put("Foto", 1);
			value44.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value44);

			ContentValues value45 = new ContentValues();
			value45.put("id", 45);
			value45.put("nama", "Taman Nasional Gunung Rinjani");
			value45.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value45.put("latitude", "-8.396979");
			value45.put("longitude", "116.457031");
			value45.put("Foto", "xxxx");
			value45.put("Status", 0);
			value45.put("Foto", 1);
			value45.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value45);

			ContentValues value46 = new ContentValues();
			value46.put("id", 46);
			value46.put("nama", "Pantai Senggigi");
			value46.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value46.put("latitude", "-8.482559");
			value46.put("longitude", "116.047356");
			value46.put("Foto", "xxxx");
			value46.put("Status", 0);
			value46.put("Foto", 1);
			value46.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value46);

			ContentValues value47 = new ContentValues();
			value47.put("id", 47);
			value47.put("nama", "Pantai Lakey");
			value47.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value47.put("latitude", "-8.578305");
			value47.put("longitude", "116.328306");
			value47.put("Foto", "xxxx");
			value47.put("Status", 0);
			value47.put("Foto", 1);
			value47.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value47);

			ContentValues value48 = new ContentValues();
			value48.put("id", 48);
			value48.put("nama", "Pulau Lombok");
			value48.put("deskripsi",
					"Keraton Jogja kaya akan keindahan alam dan budayanya");
			value48.put("latitude", "-8.578305");
			value48.put("longitude", "116.328306");
			value48.put("Foto", "xxxx");
			value48.put("Status", 0);
			value48.put("Foto", 1);
			value48.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value48);
			db.close();
		}
	}

	public ArrayList<Tempat> getListTempat() {
		ArrayList<Tempat> ListTempat = new ArrayList<Tempat>();
		int ID = 0;
		String nama = "";
		String deskripsi = "";
		double latitude = 0;
		double longitude = 0;
		String foto = "";
		int status = 0;
		int misiID = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			ID = cursor.getInt(0);
			nama = cursor.getString(1);
			deskripsi = cursor.getString(2);
			latitude = cursor.getFloat(3);
			longitude = cursor.getFloat(4);
			foto = cursor.getString(5);
			status = cursor.getInt(6);
			misiID = cursor.getInt(7);
			tempat = new Tempat(ID, nama, deskripsi, latitude, longitude, foto,
					status, misiID);
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