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
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "TheExplorerMissionDatabase";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE PENJELAJAH (id integer primary key, Username text, skor integer)");
		db.execSQL("CREATE TABLE MISI (id integer primary key, nama text, deskripsi text, lokasi text, foto text, status integer, badge text, penjelajahID integer)");
		db.execSQL("CREATE TABLE TEMPAT (id integer primary key, nama text, deskripsi text, point integer, latitude double,longitude double, Foto text, Status integer, MisiID integer)");
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
		skor = cursor.getInt(2);
		cursor.close();
		db.close();
		penjelajah = new Penjelajah(ID, username, skor);
		return penjelajah;
	}

	public void AddPenjelajah(String Username) {
		if (IsPenjelajahExist() == false) {
			ContentValues value = new ContentValues();
			SQLiteDatabase db = this.getWritableDatabase();
			value.put("id", 1);
			value.put("Username", Username);
			value.put("skor", 0);
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

	public void UpdatePenjelajahSkor(String username, int skor) {
		ContentValues value = new ContentValues();
		SQLiteDatabase db = this.getWritableDatabase();
		value.put("id", 1);
		value.put("Username", username);
		value.put("skor", skor);
		db.update("PENJELAJAH", value, "id=" + "\"" + 1 + "\"", null);
		db.close();
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
			value1.put("nama", "Explore Jogja");
			value1.put(
					"deskripsi",
					"Jogjakarta has been known as The Neverending Asia. Many say that a single visit to Jogja is never enough. There are many list of things that can be experienced in Jogjakarta,  ranging from natural splendors, art and tradition and heritages to culinary adventure. This is why Jogja is the second most visited destination in Indonesia, next to Bali.");
			value1.put("lokasi", "DI Jogjakarta, Indonesia");
			value1.put("foto", "g1_borobudur");
			value1.put("status", 0);
			value1.put("badge", "badge_jogja");
			value1.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("id", 2);
			value2.put("nama", "Explore Jakarta");
			value2.put(
					"deskripsi",
					"Jakarta is the Capital City of Republic Indonesia. Jakarta is a huge and sprawling metropolitan city. Jakarta not only the seat of national government, but also the center of finance, business, modern music, and others creative industry. Therefore, that whatever happens in Jakarta is national interest and it is for these reasons that Jakarta is the hub of Indonesia’s modern history and modern life.");
			value2.put("lokasi", "DKI Jakarta, Indonesia");
			value2.put("foto", "g2_monas");
			value2.put("status", 0);
			value2.put("badge", "badge_jakarta");
			value2.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value2);

			ContentValues value3 = new ContentValues();
			value3.put("id", 3);
			value3.put("nama", "Explore Bali");
			value3.put(
					"deskripsi",
					"Bali adalah tujuan wisata favorit wisatawan lokal maupun mancanegara. "
							+ "Pulau indah ini terkenal karena memiliki pantai yang indah, pemandangan yang menakjubkan,"
							+ " souvenir yang menarik, serta adat dan kebudayaan yang menawan");
			value3.put("lokasi", "Bali, Indonesia");     
			value3.put("foto", "g3_kuta");
			value3.put("status", 0);
			value3.put("badge", "badge_bali");
			value3.put("penjelajahID", 0);

			db.insertOrThrow("MISI", null, value3);

			ContentValues value4 = new ContentValues();
			value4.put("id", 4);
			value4.put("nama", "Explore West Sumatera");
			value4.put(
					"deskripsi",
					"West Sumatera lies on the west coast of the island Sumatera. The prime tourist attractions of West Sumatra are the natural environment, the culture, history of the Minangkabau and unique culinary. Natural attractions of the mainland include the tropical forests, mountains, volcanos, islands, lakes, valleys, rivers & waterfalls in the highlands, the fauna and flora, and the beaches around Padang.");
			value4.put("lokasi", "Sumatera Barat,Indonesia");
			value4.put("foto", "g4_istanapagaruyung");
			value4.put("status", 1);
			value4.put("badge", "badge_sumbar");
			value4.put("penjelajahID", 1);
			db.insertOrThrow("MISI", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", 5);
			value5.put("nama", "Explore West Nusa Tenggara");
			value5.put(
					"deskripsi",
					"West Nusa Tenggara lies in The Wallace Line, marks as a point of transition between the flora and fauna of Western and Eastern Indonesia and full of wonderful scenery and have unique floras and faunas. It has beautiful islands, lake, savannas forest, mountainous with tall tress and shrubs. It is a right place for escaping from the hectic life and bustling city.");
			value5.put("lokasi", "NTB, Indonesia");
			value5.put("foto", "g5_rinjani");
			value5.put("status", 1);
			value5.put("badge", "badge_ntb");
			value5.put("penjelajahID", 1);
			db.insertOrThrow("MISI", null, value5);
			db.close();
		}
	}

	public Misi GetMisiByName(String nama) {
		int ID = 0;
		String deskripsi = "";
		String lokasi = "";
		String foto = "";
		int status = 0;
		String badge = "";
		int penjelajahID = 0;
		Misi misi;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM MISI where nama = " + "\" "
				+ nama + "\" ", null);
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

	public void UpdatePenjelajahMisi(int ID, String nama, String Deskripsi,
			String lokasi, String foto, int status, String Badge,
			int PenjelajahID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value1 = new ContentValues();
		value1.put("id", ID);
		value1.put("nama", nama);
		value1.put("deskripsi", Deskripsi);
		value1.put("lokasi", lokasi);
		value1.put("foto", foto);
		value1.put("status", status);
		value1.put("badge", Badge);
		value1.put("penjelajahID", PenjelajahID);
		db.update("MISI", value1, "id = " + "\"" + ID + "\"", null);
		db.close();
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
		Cursor cursor = db.rawQuery("SELECT * FROM MISI where id = " + +MisiID,
				null);
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
		// db.close();
		misi = new Misi(ID, nama, deskripsi, lokasi, foto, status, badge,
				penjelajahID);
		return misi;

	}

	public void UpdateStatusMisi(int ID, String nama, String Deskripsi,
			String lokasi, String foto, int status, String Badge,
			int PenjelajahID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value1 = new ContentValues();
		Misi misi = GetMisiByID(ID);
		value1.put("id", ID);
		value1.put("nama", nama);
		value1.put("deskripsi", Deskripsi);
		value1.put("lokasi", lokasi);
		value1.put("foto", foto);
		value1.put("status", status);
		value1.put("badge", Badge);
		value1.put("penjelajahID", PenjelajahID);
		db.update("MISI", value1, "ID = " + "\"" + ID + "\"", null);
		db.close();
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
		int point = 0;
		Tempat tempat;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT", null);
		cursor.moveToFirst();
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		point = cursor.getInt(3);
		latitude = cursor.getFloat(4);
		longitude = cursor.getFloat(5);
		foto = cursor.getString(6);
		status = cursor.getInt(7);
		misiID = cursor.getInt(8);
		cursor.close();
		db.close();
		tempat = new Tempat(ID, nama, deskripsi, point, latitude, longitude,
				foto, status, misiID);
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
		int point = 0;
		Tempat tempat;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM TEMPAT where id = " + +ID,
				null);
		cursor.moveToFirst();
		Log.d("debag", "SELECT * FROM MISI where id = " + +ID);
		ID = cursor.getInt(0);
		nama = cursor.getString(1);
		deskripsi = cursor.getString(2);
		point = cursor.getInt(3);
		latitude = cursor.getFloat(4);
		longitude = cursor.getFloat(5);
		foto = cursor.getString(6);
		status = cursor.getInt(7);
		misiID = cursor.getInt(8);
		cursor.close();
		db.close();
		tempat = new Tempat(ID, nama, deskripsi, point, latitude, longitude,
				foto, status, misiID);
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
			value1.put(
					"deskripsi",
					"Malioboro adalah jalan yang paling terkenal dan terdapat di jantung kota Jogjakarta. "
							+ "Disepanjang jalan ini terdapat toko-toko yang menjual berbagai macam batik, souvenir dan kuliner khas Jogja."
							+ "Jalan ini sangat ramai dikunjungi oleh turis lokal maupun mancanegara.");
			value1.put("point", 50);
			value1.put("latitude", "-7.791892");
			value1.put("longitude", "110.365731");
			value1.put("Foto", "g1_malioboro");
			value1.put("Status", 0);
			value1.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value1);

			ContentValues value2 = new ContentValues();
			value2.put("id", 2);
			value2.put("nama", "Candi Borobudur");
			value2.put(
					"deskripsi",
					"Candi Borobudur yang megah adalah monumen Budha terbesar di dunia, "
							+ "sebuah situs kuno yang pernah menjadi salah satu dari tujuh keajaiban dunia. "
							+ "Disini dapat dilihat berbagai patung serta ilustrasi kisah-kisah Budha. Dari Borobudur"
							+ "pengunjung dapat menikmati keindahan pemandangan sekitar. ");
			value2.put("point", 50);
			value2.put("latitude", "-7.607212");
			value2.put("longitude", "110.203314");
			value2.put("Foto", "g1_borobudur");
			value2.put("Status", 0);
			value2.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value2);

			ContentValues value3 = new ContentValues();
			value3.put("id", 3);
			value3.put("nama", "Candi Prambanan");
			value3.put(
					"deskripsi",
					"Candi Prambanan merupakan candi Hindu terbesar di Asia Tenggara, "
							+ "kuil cantik dan anggun ini merupakan tontonan megah dan ikon warisan budaya Indonesia. Disini juga sering"
							+ "ditampilkan drama musikal mengenai cerita klasik Ramayana");
			value3.put("point", 50);
			value3.put("latitude", "-7.751901");
			value3.put("longitude", "110.492011");
			value3.put("Foto", "g1_prambanan");
			value3.put("Status", 0);
			value3.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value3);

			ContentValues value4 = new ContentValues();
			value4.put("id", 4);
			value4.put("nama", "Keraton Jogja");
			value4.put(
					"deskripsi",
					"Keraton Jogja merupakan istana Jogjakarta yang mencerminkan kebudayaan dan arsitektur Jawa. Keraton ini berfungsi"
							+ "sebagai rumah Sultan Jogja sekaligus tempat pelaksanaan upacara dan fungsi pengadilan. ");
			value4.put("point", 50);
			value4.put("latitude", "-7.805269");
			value4.put("longitude", "110.364183");
			value4.put("Foto", "g1_keratonjogja");
			value4.put("Status", 0);
			value4.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", 5);
			value5.put("nama", "Pantai Parangtritis");
			value5.put(
					"deskripsi",
					"Pantai parangtritis terkenal dengan kaitannya dengan cerita Nyi Roro Kidul, sehingga dihinggapi "
							+ "suasana mistis. Di pantai ini kita dapat melihat bukit yang hijau dengan latar belakang lautan lepas.");
			value5.put("point", 50);
			value5.put("latitude", "-8.021017");
			value5.put("longitude", "110.31815");
			value5.put("Foto", "g1_parangtritis");
			value5.put("Status", 0);
			value5.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value5);

			ContentValues value6 = new ContentValues();
			value6.put("id", 6);
			value6.put("nama", "Candi Ratu Boko");
			value6.put(
					"deskripsi",
					"Berdiri megah di dataran tinggi lereng bukit dengan latar belakang Gunung Merapi dan Candi Prambanan. "
							+ "Candi Ratu Boko adalah sisa-sisa dari masa kejayaan kerajaan Jawa Kuno. Merupakan situs arkeologi unik"
							+ "perpaduan dari arsitektur Hindu dan Budha");
			value6.put("point", 100);
			value6.put("latitude", "-7.750795");
			value6.put("longitude", "110.49222");
			value6.put("Foto", "g1_boko");
			value6.put("Status", 0);
			value6.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value6);

			ContentValues value7 = new ContentValues();
			value7.put("id", 7);
			value7.put("nama", "Taman Sari");
			value7.put(
					"deskripsi",
					"Taman Sari merupakan sebuah taman yang dibangun oleh Sri Sultan Hamengkubuwono I sebagai tempat beristirahat"
							+ "sejenak. Di taman ini terdapat kolam renang, bangunan kamar dan tentunya taman bunga yang indah");
			value7.put("point", 50);
			value7.put("latitude", "-7.780749");
			value7.put("longitude", "110.409945");
			value7.put("Foto", "g1_tamansari");
			value7.put("Status", 0);
			value7.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value7);

			ContentValues value8 = new ContentValues();
			value8.put("id", 8);
			value8.put("nama", "Gunung Merapi");
			value8.put(
					"deskripsi",
					"Gunung Merapi adalah salah satu gunung aktif di Indonesia. Beberap tahun lalu terjadi letusan vulkano "
							+ "di gunung ini dan menyisakan jejak yang mengerikan dan sekaligus menakjubkan.");
			value8.put("point", 100);
			value8.put("latitude", "-7.539828");
			value8.put("longitude", "110.445371");
			value8.put("Foto", "g1_merapi");
			value8.put("Status", 0);
			value8.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value8);

			ContentValues value9 = new ContentValues();
			value9.put("id", 9);
			value9.put("nama", "Goa Pindul");
			value9.put(
					"deskripsi",
					"Goa pindul merupakan goa yang berada di atas sungai. Wisata yang ditawarkan di Goa pindul ini adalah"
							+ "menulusuri goa melalui sungai bawah tanah yang tenang, rafting, dan off road. Disini kita disuguhi "
							+ "pemandangan goa yang luar biasa dan unik. ");
			value9.put("point", 50);
			value9.put("latitude", "-7.953157");
			value9.put("longitude", "110.597191");
			value9.put("Foto", "g1_guapindul");
			value9.put("Status", 0);
			value9.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value9);

			ContentValues value10 = new ContentValues();
			value10.put("id", 10);
			value10.put("nama", "Kota Gede");
			value10.put(
					"deskripsi",
					"Kota Gede adalah salah satu kota di Jogjakarta yang merupakan pusat kerajinan perak, seperti tea sets, kalung,"
							+ "gelang, bros dan sebagainya");
			value10.put("point", 100);
			value10.put("latitude", "-7.824439");
			value10.put("longitude", "110.39609");
			value10.put("Foto", "g1_kotagede");
			value10.put("Status", 0);
			value10.put("MisiID", 1);
			db.insertOrThrow("TEMPAT", null, value10);

			ContentValues value11 = new ContentValues();
			value11.put("id", 11);
			value11.put("nama", "Universitas Indonesia");
			value11.put(
					"deskripsi",
					"Universitas Indonesia adalah kampus modern, komprehensif, terbuka, "
							+ "multi budaya, dan humanis yang mencakup disiplin ilmu yang luas. UI termasuk salah satu "
							+ "universitas terbaik di Indonesia. ");
			value11.put("point", 50);
			value11.put("latitude", "-6.36452955");
			value11.put("longitude", "106.82902491");
			value11.put("Foto", "g2_ui");
			value11.put("Status", 0);
			value11.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value11);

			ContentValues value12 = new ContentValues();
			value12.put("id", 12);
			value12.put("nama", "Taman Mini Indonesia Indah");
			value12.put(
					"deskripsi",
					"Taman Mini Indonesia Indah merupakan taman yang berisikan miniatur Indonesia, baik itu budaya "
							+ "maupun bentuk wilayah Indonesia. Di taman ini juga terdapat keanekaragaman flora dan fauna, "
							+ "museum, teater, dan replika rumah adat provinsi di Indonesia. ");
			value12.put("point", 50);
			value12.put("latitude", "-6.302438");
			value12.put("longitude", "106.895147");
			value12.put("Foto", "g2_tmii");
			value12.put("Status", 0);
			value12.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value12);

			ContentValues value13 = new ContentValues();
			value13.put("id", 13);
			value13.put("nama", "Kota Tua Batavia");
			value13.put(
					"deskripsi",
					"Kota Tua Batavia pernah dijuluki sebagai Permata Asia dan Ratu dari Timur yang dianggap sebagai pusat  "
							+ "perdagangan untuk benua Asia karena lokasinya yang strategis dan sumber daya melimpah. Sekarang di kota tua "
							+ "Batavia ini dapat dilihat jejak-jejak sejarah Batavia dulu berupa bangunan-bangunan tua dan juga terdapat banyak museum.");
			value13.put("point", 50);
			value13.put("latitude", "-6.133216");
			value13.put("longitude", "106.813173");
			value13.put("Foto", "g2_kotatua");
			value13.put("Status", 0);
			value13.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value13);

			ContentValues value14 = new ContentValues();
			value14.put("id", 14);
			value14.put("nama", "Ancol");
			value14.put("point", 50);
			value14.put(
					"deskripsi",
					"Ancol merupakan taman rekreasi terbesar di Indonesia. Ancol memiliki berbagai pilihan atraksi seperti pantai Ancol, "
							+ "Dunia Fantasi, Seaworld, Marina Ancol, Pasar Seni, serta hotel.");
			value14.put("latitude", "-6.127242");
			value14.put("longitude", "106.845596");
			value14.put("Foto", "g2_ancol");
			value14.put("Status", 0);
			value14.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value14);

			ContentValues value15 = new ContentValues();
			value15.put("id", 15);
			value15.put("nama", "Monumen Nasional");
			value15.put(
					"deskripsi",
					"Monumen Nasional (Monas) merupakan monumen peringatan setinggi 132 meter (433 kaki) yang didirikan untuk  "
							+ "mengenang perlawanan dan perjuangan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial "
							+ "Hindia Belanda. Monumen ini juga berfungsi sebagai museum kemerdekaan.");
			value15.put("point", 50);
			value15.put("latitude", " -6.174774");
			value15.put("longitude", "106.827185");
			value15.put("Foto", "g2_monas");
			value15.put("Status", 0);
			value15.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value15);

			ContentValues value16 = new ContentValues();
			value16.put("id", 16);
			value16.put("nama", "Komplek Gelora Bung Karno");
			value16.put(
					"deskripsi",
					"Komplek Gelora Bung Karno merupakan komplek olahraga serbaguna, mulai dari sepakbola, bulutangkis, basket, "
							+ "tenis, renag, anggar hingga stadion atletik.");
			value16.put("point", 50);
			value16.put("latitude", "-6.218591");
			value16.put("longitude", "106.801693");
			value16.put("Foto", "g2_gbk");
			value16.put("Status", 0);
			value16.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value16);

			ContentValues value17 = new ContentValues();
			value17.put("id", 17);
			value17.put("nama", "Pulau Tidung Kep. Seribu");
			value17.put(
					"deskripsi",
					"Pulau Tidung merupakan pulau terbesar diantara pulau lain di Kep.Seribu. "
							+ "Tidung menawarkan pantai terpencil, malam berbintang dan komunitas ramah, jauh dari keramaian dan hiruk pikuk kota."
							+ "Selain itu, disekita pualu ini juga terdapat hutan mangrove dengan tampilan yang indah.");
			value17.put("point", 100);
			value17.put("latitude", "-5.803193");
			value17.put("longitude", "106.523066");
			value17.put("Foto", "g2_tidung");
			value17.put("Status", 0);
			value17.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value17);

			ContentValues value18 = new ContentValues();
			value18.put("id", 18);
			value18.put("nama", "Pelabuhan Sunda Kelapa");
			value18.put(
					"deskripsi",
					"Dengan menjelajahi Pelabuhan Sunda Kelapa, kita dapat melihat modernitas dan sisa-sisa masa lalu tetap bisa ditemukan.");
			value18.put("point", 100);
			value18.put("latitude", "-6.122633");
			value18.put("longitude", "106.80886");
			value18.put("Foto", "g2_sundakelapa");
			value18.put("Status", 0);
			value18.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value18);

			ContentValues value19 = new ContentValues();
			value19.put("id", 19);
			value19.put("nama", "Museum Fatahillah");
			value19.put(
					"deskripsi",
					"Museum Fatahillah merupakan bekas Balai Kota Jakarta pada zaman dahulu. Sebelumnya museum ini juga merupakan  "
							+ "penjara bagi Pangeran Diponegoro dan Untung Suropati. Saat ini, di Mesum Fatahillah dapat dilihat sejarah Jakarta.");
			value19.put("point", 50);
			value19.put("latitude", "-6.133045");
			value19.put("longitude", "106.813488");
			value19.put("Foto", "g2_fatahillah");
			value19.put("Status", 0);
			value19.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value19);

			ContentValues value20 = new ContentValues();
			value20.put("id", 20);
			value20.put("nama", "Museum Bank Indonesia");
			value20.put(
					"deskripsi",
					"Museum ini memperlihatkan peran Bank Sentral dalam sejarah Indonesia sejak zaman kolonial Belanda. "
							+ "Disini juga diperlihatkan jenis-jenis mata uang Indonesia dari zaman penjajahan sampai sekarang serta mata uang "
							+ "negara-negara yang ada di dunia.");
			value20.put("point", 50);
			value20.put("latitude", "-6.137824");
			value20.put("longitude", "106.813145");
			value20.put("Foto", "g2_bi");
			value20.put("Status", 0);
			value20.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value20);

			ContentValues value21 = new ContentValues();
			value21.put("id", 21);
			value21.put("nama", "Pantai Kuta");
			value21.put(
					"deskripsi",
					"Salah satu pantai yang paling popular menjadi tujuan wisata bagi turis dari seluruh penjuru dunia untuk berselancar "
							+ "berjemur, dan pastinya berenang. Dari pantai ini bisa menyaksikan matahari terbenam yang sangat indah. Pada malam "
							+ "hari suasana pantai ini berubah dipenuhi dengan musik dari bar dan restoran. Selain itu juga terdapat toko-toko "
							+ "yang menjual souvenir, baju dan kerajinan khas Bali.");
			value21.put("point", 50);
			value21.put("latitude", "-8.723236");
			value21.put("longitude", "115.169992");
			value21.put("Foto", "g3_kuta");
			value21.put("Status", 0);
			value21.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value21);

			ContentValues value22 = new ContentValues();
			value22.put("id", 22);
			value22.put("nama", "Ubud Monkey Forest");
			value22.put(
					"deskripsi",
					"Ubud Monkey Forest adalah cagar alam sekaligus komplek candi Ubud yang menjadi rumah bagi kurang lebih 340 ekor "
							+ "Kera Ekor Panjang. Disini pengunjung dapat menikmati atmosfer yang tenang, berbelanja, wisata kuliner dan "
							+ "menyelusuri hutan yang teduh.");
			value22.put("point", 100);
			value22.put("latitude", "-8.518743");
			value22.put("longitude", "115.258552");
			value22.put("Foto", "g3_ubudmonkey");
			value22.put("Status", 0);
			value22.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value22);

			ContentValues value23 = new ContentValues();
			value23.put("id", 23);
			value23.put("nama", "Bali Bird Park");
			value23.put(
					"deskripsi",
					"Bali Bird Park berisi koleksi burung-burung Indonesia serta dari Afrika dan Amerika Selatan. Meliputi dua hektar  "
							+ "lanskap botani, taman memberikan perlindungan kepada hampir 1.000 burung dari 250 spesies yang berbeda.Taman juga "
							+ "menampilkan kumpulan flora yang menakjubkan.");
			value23.put("point", 100);
			value23.put("latitude", "-8.596637");
			value23.put("longitude", "115.251675");
			value23.put("Foto", "g3_birdpark");
			value23.put("Status", 0);
			value23.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value23);

			ContentValues value24 = new ContentValues();
			value24.put("id", 24);
			value24.put("nama", "Pantai Lovina");
			value24.put(
					"deskripsi",
					"Pantai Lovina terkenal dengan banyaknya habitat lumba-lumba yang berlalu-lalang di perairannya. Pengunjung "
							+ "dapat menyewa perahu untuk menyusuri lautan melihat kumpulan makhuk cerdas tersebut.");
			value24.put("point", 100);
			value24.put("latitude", "-8.160347");
			value24.put("longitude", "115.029981");
			value24.put("Foto", "g3_lovina");
			value24.put("Status", 0);
			value24.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value24);

			ContentValues value25 = new ContentValues();
			value25.put("id", 25);
			value25.put("nama", "Danau Batur-Kintamani");
			value25.put(
					"deskripsi",
					"Danau Batur-Kintami berada ditengah-tengah deretan pegunungan Batur menghadirkan keindahan yang menenangkan "
							+ "dan megah dengan udara pegunungan yang menyegarkan.");
			value25.put("point", 50);
			value25.put("latitude", "-8.256682");
			value25.put("longitude", "115.408087");
			value25.put("Foto", "g3_batur");
			value25.put("Status", 0);
			value25.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value25);

			ContentValues value26 = new ContentValues();
			value26.put("id", 26);
			value26.put("nama", "Pantai Sanur");
			value26.put(
					"deskripsi",
					"Di Pantai Sanur pengunjung disuguhi fenomena matahari terbit dibalik horison yang memberikan cahaya "
							+ "indah disekitar pantai, sementara itu pasir putih yang lembut memeluk ombak yang menyapunya. Gambaran pagi yang "
							+ "sempurna dimana alam menyuguhkan fitur yang fantastis.");
			value26.put("point", 50);
			value26.put("latitude", "-8.674748");
			value26.put("longitude", "115.263827");
			value26.put("Foto", "g3_sanur");
			value26.put("Status", 0);
			value26.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value26);

			ContentValues value27 = new ContentValues();
			value27.put("id", 27);
			value27.put("nama", "Nusa Dua");
			value27.put(
					"deskripsi",
					"Nusa Dua dipenuhi dengan resort dan hotel mewah, seperti Grand Hyatt, Westin Resort, "
							+ "Laguna Resort & Spa, Nusa Dua Beach Hotel & Spa, dll. Hotel-hotel ini menawarkan taman-taman indah terawat, "
							+ "pemandangan yang fantastis dari samudera biru, membentang panjang pantai berpasir putih dan pohon palem.");
			value27.put("point", 50);
			value27.put("latitude", "-8.794578");
			value27.put("longitude", "115.216577");
			value27.put("Foto", "g3_nusadua");
			value27.put("Status", 0);
			value27.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value27);

			ContentValues value28 = new ContentValues();
			value28.put("id", 28);
			value28.put("nama", "Tanah Lot");
			value28.put(
					"deskripsi",
					"Di Tanah Lot ini terdapat dua buah pura yang berada di atas batu besar. "
							+ "Satu terletak di atas bongkahan batu dan satunya terletak di atas tebing."
							+ " Pura Tanah Lot merupakan pura laut tempat pemujaan dewa-dewa penjaga laut.");
			value28.put("point", 50);
			value28.put("latitude", "-8.681494");
			value28.put("longitude", "115.205247");
			value28.put("Foto", "g3_tanahlot");
			value28.put("Status", 0);
			value28.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value28);

			ContentValues value29 = new ContentValues();
			value29.put("id", 29);
			value29.put("nama", "Pantai Dreamland");
			value29.put(
					"deskripsi",
					"Pantai Dreamland dikelilingi oleh tebing-tebing yang menjulang tinggi,  dan dikelilingi batu karang yang lumayan besar di sekitar pantai."
							+ " Pantai Dreamland sendiri hampir mirip  dengan pantai Kuta. Pasir putih dan celah karang yang terjal menjadi pemandangan yang begitu memikat "
							+ "mata untuk dipandang. Lokasi berpasir putih bersih di pantai sempit tepat di bawah dinding karang curam  "
							+ "cocok untuk menikmati matahari tenggelam atau sekedar menyaksikan atraksi para peselancar.");
			value29.put("point", 50);
			value29.put("latitude", "-8.779225");
			value29.put("longitude", "115.166702");
			value29.put("Foto", "g3_dreamland");
			value29.put("Status", 0);
			value29.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value29);

			ContentValues value30 = new ContentValues();
			value30.put("id", 30);
			value30.put("nama", "Jimbaran");
			value30.put(
					"deskripsi",
					"Jimbaran merupakan kampung nelayan di Bali. Terdapat juga resort and spa, hotel dan restoran seafood.");
			value30.put("point", 50);
			value30.put("latitude", "-8.789998");
			value30.put("longitude", "115.159278");
			value30.put("Foto", "g3_jimbaran");
			value30.put("Status", 0);
			value30.put("MisiID", 3);
			db.insertOrThrow("TEMPAT", null, value30);

			ContentValues value31 = new ContentValues();
			value31.put("id", 31);
			value31.put("nama", "Jam Gadang");
			value31.put(
					"deskripsi",
					"Jam Gadang merupakan ikon dari kota Bukittinggi dengan tinggi 26 meter. Keunikan dari jam ini adalah "
							+ "angka IV ditulis dengan IIII. Di sekitar Jam Gadang terdapat taman yang biasa dijadikan tempat "
							+ "berkumpul dan istirahat sejenak oleh pengunjung.");
			value31.put("point", 50);
			value31.put("latitude", "-0.30517");
			value31.put("longitude", "100.368408");
			value31.put("Foto", "g4_jamgadang");
			value31.put("Status", 1);
			value31.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value31);

			ContentValues value35 = new ContentValues();
			value35.put("id", 35);
			value35.put("nama", "Pantai Air Manis");
			value35.put(
					"deskripsi",
					"Pantai Air Manis merupakan salah satu pantai di kota Padang. Pantai ini terkenal dengan kaitannya "
							+ "dengan legenda Malin Kundang, dimana serang anak durhaka yang dikutuk menjadi batu. Di pantai ini "
							+ "terdapat batu yang sangat mirip dengan gambaran manusia bersama kapalnya.");
			value35.put("point", 100);
			value35.put("latitude", "-0.60149");
			value35.put("longitude", "100.539901");
			value35.put("Foto", "g4_pantaiairmanis");
			value35.put("Status", 1);
			value35.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value35);

			ContentValues value36 = new ContentValues();
			value36.put("id", 36);
			value36.put("nama", "Danau Singkarak");
			value36.put(
					"deskripsi",
					"Danau singkarak merupakan danau terluas di Sumatera Barat dan danau terbesar kedua di Sumatera setelah Danau Toba."
							+ " Sebuah dunia diam yang tenang, pemandangan menakjubkan dan pemandangan spektakuler menanti di sini. Di sini Anda dapat merangkul  "
							+ "keindahan alam Indonesia yang sangat terkenal. Danau Singkarak terkenal dengan ikan Bilih yang merupakan spesies ikan yang hanya hidup di danau ini.");
			value36.put("point", 50);
			value36.put("latitude", "-0.60149");
			value36.put("longitude", "100.539901");
			value36.put("Foto", "g4_danausingkarak");
			value36.put("Status", 1);
			value36.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value36);

			ContentValues value37 = new ContentValues();
			value37.put("id", 37);
			value37.put("nama", "Istana Pagaruyung");
			value37.put(
					"deskripsi",
					"Istana Pagaruyung merupakan obyek wisata budaya yang terkenal di Sumatera Barat. Istana ini adalah istana dari "
							+ "kerajaan Pagaruyung. Melambangkan budaya dan keindahan arsitektur Minangkabau.");
			value37.put("point", 100);
			value37.put("latitude", "-0.307272");
			value37.put("longitude", "100.200752");
			value37.put("Foto", "g4_istanapagaruyung");
			value37.put("Status", 1);
			value37.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value37);

			ContentValues value38 = new ContentValues();
			value38.put("id", 38);
			value38.put("nama", "Danau Maninjau");
			value38.put(
					"deskripsi",
					"Danau Maninjau merupakan danau yang tenang, cocok untuk pelarian diri dari keramaian dan hiruk pikuk kota. "
							+ "Dengan pemandangan yang inspiratif dan damai mengelilingi, menawarkan beberapa yang terbaik dilihat dari danau.");
			value38.put("point", 50);
			value38.put("latitude", "-0.307272");
			value38.put("longitude", "100.200752");
			value38.put("Foto", "g4_maninjau");
			value38.put("Status", 1);
			value38.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value38);

			ContentValues value40 = new ContentValues();
			value40.put("id", 40);
			value40.put("nama", "Gili Terawangan");
			value40.put(
					"deskripsi",
					"Gili Terawangan adalah pulau terbesar daripada 3 pulau Gili. Ia memiliki kehidupan malam yang semarak "
							+ "untuk menghibur Anda setelah matahari terbenam. Dengan koleksi macam restoran dan bar,  "
							+ "pengunjung tidak akan pernah kekurangan hiburan di sini.");
			value40.put("point", 50);
			value40.put("latitude", "-8.346199");
			value40.put("longitude", "116.038099");
			value40.put("Foto", "g5_gilitrawangan");
			value40.put("Status", 1);
			value40.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value40);

			ContentValues value41 = new ContentValues();
			value41.put("id", 41);
			value41.put("nama", "Gili Meno Island");
			value41.put(
					"deskripsi",
					"Gili Meno adalah pulau terkecil daripada 3 pulau Gili dengan pantai pasir putih, terumbu karang tropis dan "
							+ "air pantai yang hangat.");
			value41.put("point", 50);
			value41.put("latitude", "-8.34501");
			value41.put("longitude", "116.055609");
			value41.put("Foto", "g5_gilimeno");
			value41.put("Status", 1);
			value41.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value41);

			ContentValues value42 = new ContentValues();
			value42.put("id", 42);
			value42.put("nama", "Gili Air Island");
			value42.put(
					"deskripsi",
					"Gili Air adalah pulau yang paling dekat dengan pulau utama dan populasi yang paling besar daripada 3 pulau Gili. "
							+ "Dengan pantai pasir putih, terumbu karang tropis dan air pantai yang hangat.");
			value42.put("point", 50);
			value42.put("latitude", "-8.356814");
			value42.put("longitude", "116.081444");
			value42.put("Foto", "g5_giliair");
			value42.put("Status", 1);
			value42.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value42);

			ContentValues value44 = new ContentValues();
			value44.put("id", 44);
			value44.put("nama", "Pulau Moyo");
			value44.put(
					"deskripsi",
					"Pulau Moyo merupakan lingkungan yang murni dengan garis pantai yang berbatu dan padang rumput rumah  "
							+ "untuk semua jenis satwa liar. Moyo dikelilingi oleh terumbu karang yang indah tak tersentuh yang ideal "
							+ "untuk snorkeling. Berenang dengan hiu karang ujung putih, menyelam di sebelah sengatan sinar terlihat biru,"
							+ "menemukan kumpulan ikan tropis. Fauna daratan juga meramaikan pulau ini, seperti rusa, sapi liar dan burung.");
			value44.put("point", 100);
			value44.put("latitude", "-8.197898");
			value44.put("longitude", "117.578216");
			value44.put("Foto", "g5_pulaumoyo");
			value44.put("Status", 1);
			value44.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value44);

			ContentValues value45 = new ContentValues();
			value45.put("id", 45);
			value45.put("nama", "Gunung Rinjani");
			value45.put(
					"deskripsi",
					"Gunung Rinjani adalah gunung tertinggi kedua di Indonesia. Pendakian ke puncak mungkin tidak mudah, "
							+ "tapi sangat bernilai, dan secara luas dianggap sebagai salah satu pemandangan yang terbaik "
							+ "di negeri ini. Sekitar lereng Rinjani terdapat hutan lebat dan terdapat air terjun. Ditengah gunung ada danau "
							+ "Segera Anak.");
			value45.put("point", 50);
			value45.put("latitude", "-8.396979");
			value45.put("longitude", "116.457031");
			value45.put("Foto", "g5_rinjani");
			value45.put("Status", 1);
			value45.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value45);

			ContentValues value46 = new ContentValues();
			value46.put("id", 46);
			value46.put("nama", "Pantai Senggigi");
			value46.put(
					"deskripsi",
					"Pantai Senggigi adalah resort area tertua dan paling terkenal di Lombok. Tempat yang tepat untuk berelaksasi, dengan  "
							+ "pantai pasir putih dan area berenang yang aman. Tempat ini juga memiliki karang yang warna-warni nan indah, cocok"
							+ "untuk snorkling.");
			value46.put("point", 50);
			value46.put("latitude", "-8.482559");
			value46.put("longitude", "116.047356");
			value46.put("Foto", "g5_senggigi");
			value46.put("Status", 1);
			value46.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value46);

			ContentValues value47 = new ContentValues();
			value47.put("id", 47);
			value47.put("nama", "Pantai Lakey");
			value47.put(
					"deskripsi",
					"Pantai Lakey merupakan pantai yang lebar dengan pasir putih yang panjang dan digawangi oleh karang. Pantai ini tergolong unik, "
							+ "dimana ombaknya menyapu pantai ke arah kiri. Pantai ini sangat disenangi oleh para peselancar.");
			value47.put("point", 100);
			value47.put("latitude", "-8.578305");
			value47.put("longitude", "116.328306");
			value47.put("Foto", "g5_lakey");
			value47.put("Status", 1);
			value47.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value47);

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
		int point = 0;
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
			point = cursor.getInt(3);
			latitude = cursor.getFloat(4);
			longitude = cursor.getFloat(5);
			foto = cursor.getString(6);
			status = cursor.getInt(7);
			misiID = cursor.getInt(8);
			tempat = new Tempat(ID, nama, deskripsi, point, latitude,
					longitude, foto, status, misiID);
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

	public void UpdatePenjelajahUsername(String username, int skor) {
		ContentValues value = new ContentValues();
		SQLiteDatabase db = this.getWritableDatabase();
		value.put("id", 1);
		value.put("Username", username);
		value.put("skor", skor);
		db.update("PENJELAJAH", value, "id=" + "\"" + 1 + "\"", null);
		db.close();

	}

	public void UpdateStatusTempat(int ID, String nama, String deskripsi,
			int point, double latitude, double longitude, String foto, int i,
			int misiID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value1 = new ContentValues();
		value1.put("id", ID);
		value1.put("nama", nama);
		value1.put("deskripsi", deskripsi);
		value1.put("point", point);
		value1.put("latitude", latitude);
		value1.put("longitude", longitude);
		value1.put("Foto", foto);
		value1.put("Status", i);
		value1.put("MisiID", misiID);
		db.update("TEMPAT", value1, "id=" + "\"" + ID + "\"", null);
		db.close();

	}
}