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
					"Jogjakarta has been known as The Neverending Asia. Many say that a single visit to Jogja is never enough. "
							+ "There are many list of things that can be experienced in Jogjakarta,  ranging from natural splendors, art and "
							+ "tradition and heritages to culinary adventure. This is why Jogja is the second most visited destination in"
							+ " Indonesia, next to Bali.");
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
					"Jakarta is the Capital City of Republic Indonesia. Jakarta is a huge and sprawling metropolitan city. "
							+ "Jakarta not only the seat of national government, but also the center of finance, business, modern music,"
							+ " and others creative industry. Therefore, that whatever happens in Jakarta is national interest and it is for"
							+ " these reasons that Jakarta is the hub of Indonesia’s modern history and modern life.");
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
					"Bali adalah tujuan wisata favorit wisatawan lokal maupun mancanegara. Pulau indah ini terkenal karena"
							+ " memiliki pantai yang indah, pemandangan yang menakjubkan, souvenir yang menarik, serta adat dan kebudayaan "
							+ "yang menawan.");
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
					"West Sumatera lies on the west coast of the island Sumatera. The prime tourist attractions of West Sumatra "
							+ "are the natural environment, the culture, history of the Minangkabau and unique culinary. Natural attractions "
							+ "of the mainland include the tropical forests, mountains, volcanos, islands, lakes, valleys, rivers  & waterfalls "
							+ "in the highlands, the fauna and flora, and the beaches around Padang.");
			value4.put("lokasi", "Sumatera Barat,Indonesia");
			value4.put("foto", "g4_istanapagaruyung");
			value4.put("status", 0);
			value4.put("badge", "badge_sumbar");
			value4.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value4);

			ContentValues value5 = new ContentValues();
			value5.put("id", 5);
			value5.put("nama", "Explore West Nusa Tenggara");
			value5.put(
					"deskripsi",
					"West Nusa Tenggara lies in The Wallace Line, marks as a point of transition between the flora and "
							+ "fauna of Western and Eastern Indonesia and full of wonderful scenery and have unique floras and faunas. "
							+ "It has beautiful islands, lake, savannas forest, mountainous with tall tress and shrubs. It is a right place "
							+ "for escaping from the hectic life and bustling city. ");
			value5.put("lokasi", "NTB, Indonesia");
			value5.put("foto", "g5_rinjani");
			value5.put("status", 0);
			value5.put("badge", "badge_ntb");
			value5.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value5);

			ContentValues value6 = new ContentValues();
			value6.put("id", 6);
			value6.put("nama", "Explore Malang");
			value6.put(
					"deskripsi",
					"Malang is the second largest city in East Java province, Indonesia. "
							+ "It has an ancient history dating back to the Mataram Kingdom. Malang is a trade"
							+ " center of agricultural area in which sugar, rice, coffee, tea, corn, peanuts, cassava,"
							+ " and cinchona bark are grown. Malang city has its Interesting Places that can attract the"
							+ " visitor to visit this city. This cool and fresh city also has been equipped with such kind"
							+ " of tourism accomodation including hotel, restaurant, etc. In Malang, visitor can visit Mount"
							+ " Bromo, Batu Night Square, Mount Semeru,etc.");
			value6.put("lokasi", "East Java, Indonesia");
			value6.put("foto", "g6_bromo");
			value6.put("status", 0);
			value6.put("badge", "badge_malang");
			value6.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value6);

			ContentValues value7 = new ContentValues();
			value7.put("id", 7);
			value7.put("nama", "Explore Surabaya");
			value7.put(
					"deskripsi",
					"Surabaya is the official capital of the province, Surabaya is "
							+ "also the second largest city after Jakarta. Looking at the historical background, "
							+ "Surabaya was build based on some phases of time. The first phase was coming from the"
							+ " time before the Dutch colonialism. Surabaya was once a gate toMajapahit Kingdom, which"
							+ " was located at the edge of Kali Mas. The second phase was coming from Islam era and last"
							+ " phase was coming from Dutch East Indies era. The most population in Surabaya is Javanese "
							+ "people, but there are also some ethnic that live in Surabaya such as, Madura, Tionghoa and"
							+ " Arab, and some others are the expatriates. While, Islam is the major religion that being "
							+ "held by most inhabitants. The inhabitants are mostly speak in Javanese language with particular accent that different with"
							+ " common Javanese language. In Surabaya, visitor can visit Tugu Pahlawan, Cheng Hoo Mosque,Jembatan"
							+ " Suramadu, Kenjeran Beach, etc.");
			value7.put("lokasi", "East Java, Indonesia");
			value7.put("foto", "g7_kenjeran");
			value7.put("status", 0);
			value7.put("badge", "badge_surabaya");
			value7.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value7);

			ContentValues value8 = new ContentValues();
			value8.put("id", 8);
			value8.put("nama", "Explore Bandung");
			value8.put(
					"deskripsi",
					"Bandung, City of Flowers, is the provincial capital of West Java and Indonesia' s third largest city."
							+ " Known in colonial times as the Paris of Java because of its European ambiance and sophistication, "
							+ "Bandung shares with Miami a fine legacy of Tropical Deco architecture dating from the 1920's. "
							+ "Situated on a plateau in the beautiful Parahayangan mountains, Bandung's pleasant climate and "
							+ "lush surroundings have offered an escape from the heat of the lowlands since the mid 19th century "
							+ "when it was the heart of the region's most prosperous plantation area. Host to the historic Asia "
							+ "Africa conference in 1955. Bandung is also renowned for its shopping, particularly for shoes, textiles,"
							+ " clothing and denims which are found in the colourful Jeans street . Easily reached from Jakarta by train "
							+ ", road or air (the scenic train trip is recommended), Bandung, with its cool mountain air is a popular second"
							+ " stop for those "
							+ "visiting the national capital and an essential stay-over for travellers enroute through Java.");
			value8.put("lokasi", "West Java, Indonesia");
			value8.put("foto", "g8_kawahputih");
			value8.put("status", 0);
			value8.put("badge", "badge_bandung");
			value8.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value8);

			ContentValues value9 = new ContentValues();
			value9.put("id", 9);
			value9.put("nama", "Explore Medan");
			value9.put(
					"deskripsi",
					"Medan is the fourth largest city in Indonesia after Jakarta, "
							+ "Surabaya, and Bandung. With a population of about 2 million people it is the "
							+ "largest city outside of Java. There is no single ethnic group forming a majority;"
							+ " the largest ethnic groups are the Chinese, Javanese, Toba-Batak, Minangkabau, "
							+ "Mandailing Batak, Karo Batak, Southern Indians, and Northern Indians, and there are"
							+ " many more ethnic minorities.Each ethnic group contributes to thousands of tasty, mouth"
							+ "-watering dishes found in every corner of Medan. They offer to locals and tourists alike"
							+ " dozens of food streets and hawker centres to suit their appetite at any time of the day."
							+ "Medan isn't really a tourist destination, but it's a convenient stepping stone to other places"
							+ " like the 'weekend getaway' Berastagi, the island of Penang, Malaysia and, of course the ever "
							+ "popular, beautiful crater lake,Lake Toba.");
			value9.put("lokasi", "North Sumatra, Indonesia");
			value9.put("foto", "g9_brastagi");
			value9.put("status", 0);
			value9.put("badge", "badge_medan");
			value9.put("penjelajahID", 0);
			db.insertOrThrow("MISI", null, value9);
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
					"Malioboro is the most famous street in Jogjakarta, located in the heart of Jogja. Along this street, "
							+ "there are many  stores that sell numerous of batik (Traditional cloth of Indonesia), handicrafts, and "
							+ "typical culinary of Jogja.There are hotels and inns for who want to stay here to suit all budget. ");
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
					"The magnificent Borobudur temple is the world’s biggest Buddhist monument, an ancient site widely "
							+ "considered to be one of the world’s seven wonders. The temple is decorated with stone carvings in bas-relief "
							+ "representing images from the life of Buddha. When reaching the top of the temple, spend some time to rest and"
							+ " marvel at the magnificent view and on the way to the top visitors can see the stone displays in the wall.");
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
					"Prambanan Temple as the largest Hindu temple in Southeast Asia, the beautiful and graceful temple of Prambanan "
							+ "is a magnificent spectacle and an icon of Indonesia’s cultural heritage. The relief’s inside the temple show the epic "
							+ "story of Ramayana. Also, The classic Javanese  Ramayana ballet or dance drama  (hyperlink) is performed here by more than "
							+ "250 dancers in an outdoor open stage   with as its backdrop the full moon dramatically rising over the temples.");
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
					"The palace of Yogyakarta, known as the Keraton (also spelled kraton or karaton), is a grand complex that was meticulously "
							+ "planned to reflect  the Javanese cosmos.This splendid example of traditional Javanese architecture has no equal. "
							+ "Today, the Keraton is a piece of living history and tradition. Visitors can sightseeing around the palace and  "
							+ "take a look at the royal carriages on display. ");
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
					"Escape from the city for a while and take the short drive from Yogya to the mystical Parangtritis beach where dramatic "
							+ "jagged cliffs meet a glistening volcanic black sand beach. This is the ideal place to come for a day trip to "
							+ "take a break out of the city and soak up the roar of the waves and the astounding atmosphere. Here visitors will "
							+ "see some incredibly green lush hills set against the backdrop of a raging ocean.");
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
					"Candi Ratu Boko standing majestically on a hillside plateau overlooking the magnificent Candi Prambanan and the mystical Mount Merapi "
							+ "as its backdrop. The Ratu Boko Palace Complex is a unique archeological site which features a meld of Hindu and Buddhist architecture.");
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
					"Tamansari as know as The Water Castle is a garden and rest house that builded by Sultan Hamengkubuwono I as a place "
							+ "to rest with his wife and concubines, and there can be a place for sport and entertainment, and meditation. Here, "
							+ "the visitors can do sightseeing into the castles complex and buy some souvenirs at Pasar Ngasem.");
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
					"Mt Merapi is one of the most active volcanoes on earth. Despite its frequent eruptions, Mt. Merapi is very "
							+ "central to the lives of the Javanese people and kings. Visitors can climb this mount until the top or just on the edge and "
							+ "see the extraordinary view with spectacular volcanic landscape and amazing sunrise.");
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
					"Wading across a river using b is somethinoatg common, but if the river is located inside a cave, of course it will be an "
							+ "enjoyable yet challenging experience. Pindul Cave is a cave that becomes part of the group of seven caves with "
							+ "underground river flowing inside. visitors will be invited to go along the river in the darkness of the bowels of the earth "
							+ "with 300 meters length using lifebelt, but ofcourse with guidance of the guide. This is an activity combining body rafting and "
							+ "caving, and later called cave tubing.");
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
					"Kota Gede is a suburb of the city of Jogjakarta and a great place to come and take a slow wander round. "
							+ "This city is the cetre of Jogja's silver industry. There are a number of workshops where visitors can stop by "
							+ "and watch silversmiths at work and see the remarkable way they can transform a piece of silver into a beautiful work of art.");
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
					"Universitas Indonesia is a modern, comprehensive, open-minded, multi culture, "
							+ "and humanism campus in the Capital of Indonesia. Universitas Indonesia is on of The Green Campus"
							+ "in Indonesia. Visitor can sightseeing this campus with Bis Kuning (Bikun) and Sepeda Kuning (Spekun)."
							+ "In this campus, visitors can find the biggest library in ASEAN, and can enjoy the green lake and forests. ");
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
					"Taman Mini Indonesia Indah or Beautiful Indonesia in Miniature Park is an extensive culture-based park, the prime "
							+ "showcase of Indonesia’s rich cultural and natural diversity. In this park, there are many floras and faunas,"
							+ "replicas of traditional houses of Indonesian archipelago, museums, theatre, and accomodation facilities. ");
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
					"Batavia was known as “The Queen of the East” and “the Jewel of Asia”.Today many of the original buildings are still intact, "
							+ "The government has designated the Old Batavia as conservation area and plans are afoot to revive the entire neighbourhood into a "
							+ "tourst destination. There are many old building, museums and scene of the old day Batavia. ");
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
					"Ancol is the largest and most popular recreation park. Ancol has many list of attraction, such as beach, Dunia Fantasi, "
							+ "Seaworld, Marina Ancol, Pasar Seni, art center, and hotels. Visitor can choose as they like according to their preference.");
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
			value15.put("Status", 1);
			value15.put("MisiID", 2);
			db.insertOrThrow("TEMPAT", null, value15);

			ContentValues value16 = new ContentValues();
			value16.put("id", 16);
			value16.put("nama", "Gelanggang Olahraga Bung Karno");
			value16.put(
					"deskripsi",
					"Bung Karno Sports Complex (Gelanggang Olahraga Bung Karno) is a comprehensive complex for international competition from  "
							+ "footbal, badminton, swimming, teniss to golf. Not only for sport, here is also Jakarta International Convention Center "
							+ "complete with adjacent supporting hotels and Indonesia’s National Television station, TVRI.");
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
					"Tidung Island is the most largest island in Thausand Island (Pulau Seribu) and the most "
							+ "popular among the other islands. Tidung offers secluded beaches, starry nights and an amiable "
							+ "community, just a stone’s throw away from the capital. In this islands, visitor won't have to battle with the traffic, so can enjoy the holiday peacefully.");
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
					"The older parts of Jakarta offer the chance to view the sceneries of the past. "
							+ "Explore Sunda Kelapa Harbor and other places of interest to the fullest, where modernity and remnants of the past can still be found.");
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
					"The Jakarta History Museum (also known as Fatahillah Museum) is housed in the former City Hall located in the old "
							+ "part of the city now known as Jakarta Kota. This museum displays the history of Jakarta from prehistoric days to "
							+ "the founding of the town of Jayakarta in1527 by Prince Fatahillah of Banten,  and through Dutch colonization from the 16th. "
							+ "Century onwards until Indonesia’s Independence in 1945.");
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
					"The Bank of Indonesia Museum displays the role of the Central Bank in the history of the Indonesian archipelago "
							+ "since Dutch  colonial days. There is a numismatic collection of coins and old legal tender used in a number of ancient"
							+ " Indonesian kingdoms  and during the Dutch colonial era and from the other countries.");
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
					"Once a sleepy village with a quiet, beautiful sweep of beach, Kuta today has become a popular beach destination in its"
							+ " own right,  alive with tourists from all over the world, swimming, surfing or sunbathing by the beach. On the beach, people "
							+ "enjoy parasailing, banana boat trips or swimming Women offer traditional Indonesian massage on the beach, others are seen plaiting"
							+ " hair. Before sunset,crowds rush to the beach waiting to watch Kuta’s legendary sunsets.");
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
					"The Ubud Monkey Forest is a nature reserve and temple complex in Ubud, Bali. "
							+ "It houses approximately 340 monkeys which are known as long-tailed Macaques (Macaca fascicularis)."
							+ " Walking around Ubud is an experience of its own. Visitor can also rent  a bicycle or a motorbike to help them enjoy the atmosphere and beautiful landscape.");
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
					"Bali Bird Park have largest and finest collection of Indonesian Birds in the world plus fantastic birds from Africa & South America. "
							+ "The park accommodates an amazing display of flora with more then 2000 tropical plants including 50 varieties of palms alone and "
							+ "attracting numerous butterflies.");
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
					"Lining up of busy losmen and hotels with the depleted shady sea makes "
							+ "Lovina one of the most stirring beaches in Bali. Tranquility, incredible"
							+ " under water world and popular attraction is the dolphin watching. "
							+ "According to the news, theres  about 500 - 1000 dolphins here in Lovina Beach");
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
					"Wonderful mountain air and dizzying views in all directions, as well as several important temples, are what makes  "
							+ "Kintamani one of the most memorable stops on the Bali tourist itinerary. Present the beautiful and elegant scenery, "
							+ "and fresh air that city haven't.");
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
					"Greet the sun as she gradually rises from below the horizon radiating the most brilliant colors over Pantai Sanur, while "
							+ "the soft white sands embrace the oncoming waves: a perfect picture of a beautiful sunny morning, where nature displays her "
							+ "fascinating features at the tranquil. Pantai Sanur is a long stretch of beautiful white sand lined with palm trees that shade "
							+ "dreamy footpaths, market stalls and relaxed beach cafes.");
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
					"Nusa Dua  is a special, self contained tourism estate, built comprising a large Bali Convention Centre and "
							+ "complemented  with super-de-luxe resorts. Here you will find some of the world’s best known hotel names."
							+ "The sprawling hotels offer beautifully manicured gardens, fantastic panoramic views of the blue ocean beyond, long stretches of white beaches and swaying palm trees.");
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
					"Tanah Lot is a rock formation in Bali and a popular tourist and cultural icon for"
							+ " photography and general exoticism. Here, the temple sits on a large offshore rock "
							+ "which has been shaped continuously over the years by the ocean tide. On the mainland cliff tops, restaurants have also been provided for tourists.");

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
					"Pantai Dreamland has a vast stretch of soft pearly white sand, clear blue sky over "
							+ "the enchanting blue waters, and nothing  but the fascinating sounds of waves hitting "
							+ "the shores; no, this is not a set from a dream, this is where nature interprets  your dream."
							+ " The beach offers tranquility and much more exciting waves perfectly fit for advanced surfers.");
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
					"Overlooking the spectacular Jimbaran Bay, The Spa at Jimbaran offers head-to-toe all natural spa treatments that uses "
							+ "sea salt crystals, seaweed and aromatics. Jimbaran is also have resort and spa, hotels and seafood restaurants.");
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
					"Jam Gadang is an icon of Bukittinggi.In the Indonesian language, "
							+ "Jam Gadang means big clock and big it certainly is! One unique thing "
							+ "about this clock is its IV, as it is written as ‘IIII’. Jam Gadang located within a 100 meter."
							+ " Visitors can see the clock while relaxing in the shade of the park. You can take a bendi "
							+ "(horse-drawn carriage) to travel  around the clock.");
			value31.put("point", 50);
			value31.put("latitude", "-0.30517");
			value31.put("longitude", "100.368408");
			value31.put("Foto", "g4_jamgadang");
			value31.put("Status", 0);
			value31.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value31);

			ContentValues value35 = new ContentValues();
			value35.put("id", 35);
			value35.put("nama", "Pantai Air Manis");
			value35.put(
					"deskripsi",

					"Pantai Air Manis is one of the famous beach in Sumatera Barat, closely related to the legend of Malin Kundang in Sumatera Barat. "
							+ "By the beach, there is a Malin Kundang stone and several pieces of equipment from his ship, which are also stones. "
							+ "Pantai Air Manis is a favorite tourist site for local and foreign tourists because it has low waves and beautiful views of Mount Padang. "
							+ "There are many kiosks selling various souvenirs such as shirts, clothes, bags and other handicrafts.");
			value35.put("point", 100);
			value35.put("latitude", "-0.60149");
			value35.put("longitude", "100.539901");
			value35.put("Foto", "g4_pantaiairmanis");
			value35.put("Status", 0);
			value35.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value35);

			ContentValues value36 = new ContentValues();
			value36.put("id", 36);
			value36.put("nama", "Danau Singkarak");
			value36.put(
					"deskripsi",
					"The majestic Lake Singkarak (Danau Singkarak) is an enormous crater lake set within a dramatic volcanic landscape. "
							+ "It is the widest lake in Sumatra and the second biggest lake on the island, after Lake Toba. A world of silent calm, "
							+ "breathtaking vistas and  spectacular views awaits here. Here you can embrace the remote natural beauty that Indonesia is so famous for. "
							+ "Locally Lake Singkarak is famous for its Bilih fish which is a species of fish that only lives in this lake.");
			value36.put("point", 50);
			value36.put("latitude", "-0.60149");
			value36.put("longitude", "100.539901");
			value36.put("Foto", "g4_danausingkarak");
			value36.put("Status", 0);
			value36.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value36);

			ContentValues value37 = new ContentValues();
			value37.put("id", 37);
			value37.put("nama", "Istana Pagaruyung");
			value37.put(
					"deskripsi",
					"Istana Pagaruyung is a culture heritage of Minangkabau. This place is the palace of Pagaruyung Kingdom and"
							+ "show how great the architecture of Minangkabau and their unique culture.");
			value37.put("point", 100);
			value37.put("latitude", "-0.307272");
			value37.put("longitude", "100.200752");
			value37.put("Foto", "g4_istanapagaruyung");
			value37.put("Status", 0);
			value37.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value37);

			ContentValues value38 = new ContentValues();
			value38.put("id", 38);
			value38.put("nama", "Danau Maninjau");
			value38.put(
					"deskripsi",
					"The splendid Danau Maninjau in Sumatera, is one of the calmest places in Indonesia. Come to this ancient lake, set within  "
							+ "a volcanic crater, to escape from the crowds and hustle and bustle of the rest of the country. With inspiring scenery and peacedul "
							+ "surrounds, this lake is an impressive natural wonder and a place to come when you’re after a peaceful and relaxing place to unwind  "
							+ "and absorb a sleepy pace of life. ");
			value38.put("point", 50);
			value38.put("latitude", "-0.307272");
			value38.put("longitude", "100.200752");
			value38.put("Foto", "g4_maninjau");
			value38.put("Status", 0);
			value38.put("MisiID", 4);
			db.insertOrThrow("TEMPAT", null, value38);

			ContentValues value40 = new ContentValues();
			value40.put("id", 40);
			value40.put("nama", "Gili Terawangan");
			value40.put(
					"deskripsi",
					"Gili Trewangan is the largest and most popular of the three islands. It has a vibrant nightlife to keep you  "
							+ "entertained once the sun goes down. With a wide collection of restaurants and bars, you’ll never be short "
							+ "of entertainment here.");
			value40.put("point", 50);
			value40.put("latitude", "-8.346199");
			value40.put("longitude", "116.038099");
			value40.put("Foto", "g5_gilitrawangan");
			value40.put("Status", 0);
			value40.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value40);

			ContentValues value41 = new ContentValues();
			value41.put("id", 41);
			value41.put("nama", "Gili Meno Island");
			value41.put(
					"deskripsi",
					"Gili Meno is the smallest island and, with only a few hundred permanent residents, it has the most remote feel to it.  "
							+ "Gili Meno has White sandy beaches, Tropical coral reefs and Warm inviting waters.");
			value41.put("point", 50);
			value41.put("latitude", "-8.34501");
			value41.put("longitude", "116.055609");
			value41.put("Foto", "g5_gilimeno");
			value41.put("Status", 0);
			value41.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value41);

			ContentValues value42 = new ContentValues();
			value42.put("id", 42);
			value42.put("nama", "Gili Air Island");
			value42.put(
					"deskripsi",
					"Gili Air is closest to the mainland and is the most populated of the Gili’s. "
							+ "The perfect escape for anyone wanting to get away to beautiful, natural and relaxing surrounds. ");
			value42.put("point", 50);
			value42.put("latitude", "-8.356814");
			value42.put("longitude", "116.081444");
			value42.put("Foto", "g5_giliair");
			value42.put("Status", 0);
			value42.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value42);

			ContentValues value44 = new ContentValues();
			value44.put("id", 44);
			value44.put("nama", "Pulau Moyo");
			value44.put(
					"deskripsi",
					"For lovers of nature and the underwater world, Moyo Island, a pristine environment with rugged coastlines and "
							+ "a grassy savannah home to all sorts of wildlife. Moyo is surrounded by beautiful untouched coral reefs which "
							+ "are ideal for snorkeling. Swim with white tip reef sharks, dive next to blue spotted sting rays, encounter "
							+ "a school of tropical fish. Back on land, two thirds of the island is a game reserve. Wildlife here includes deer, "
							+ "feral cattle and a variety of different types of birds.");
			value44.put("point", 100);
			value44.put("latitude", "-8.197898");
			value44.put("longitude", "117.578216");
			value44.put("Foto", "g5_pulaumoyo");
			value44.put("Status", 0);
			value44.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value44);

			ContentValues value45 = new ContentValues();
			value45.put("id", 45);
			value45.put("nama", "Gunung Rinjani");
			value45.put(
					"deskripsi",
					"The mighty Rinjani mountain of Gunung Rinjani is a massive volcano which towers over the island of Lombok.  "
							+ "The climb to the top may not be easy but it’s worth it, and is widely regarded as one of the best views in the country.  "
							+ "Around the slopes of Rinjani there are lush forests sprinkled with waterfalls and surrounded by stunning scenery. "
							+ "Within the mountain is a crescent shaped lake, the breathtaking Segara Anak.");
			value45.put("point", 50);
			value45.put("latitude", "-8.396979");
			value45.put("longitude", "116.457031");
			value45.put("Foto", "g5_rinjani");
			value45.put("Status", 0);
			value45.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value45);

			ContentValues value46 = new ContentValues();
			value46.put("id", 46);
			value46.put("nama", "Pantai Senggigi");
			value46.put(
					"deskripsi",
					"A perfect place to relax, Senggigi boasts a series of white sandy beaches and safe swimming areas. The point at central "
							+ "Senggigi has good waves for surfers. This place has a colourful reef which provides shelter to a variety of marine life  "
							+ "and exquisitely shapped coral and makes it a perfect place to snorkle. In the dry season, there is an interesting variety  "
							+ "of boats moored in the bay.");
			value46.put("point", 50);
			value46.put("latitude", "-8.482559");
			value46.put("longitude", "116.047356");
			value46.put("Foto", "g5_senggigi");
			value46.put("Status", 0);
			value46.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value46);

			ContentValues value47 = new ContentValues();
			value47.put("id", 47);
			value47.put("nama", "Pantai Lakey");
			value47.put(
					"deskripsi",
					"Pantai Lakey is a long, wide, palm-lined, white-sand stretch of beach fronted by reef. Much of the bottom is rocky, but farther up "
							+ "the beach it's sandy and good for swimming. The waves here appear to sweep to the left in direction, instead of right, as is common. "
							+ "The uniqueness of this fact, with the added, natural beauty of the beach has become a sensation, especially among surfing enthusiasts. "
							+ "Aside from that, the sea breeze is fairly strong as well which supports the activities of surfing, wind surfing, or kite surfing.");
			value47.put("point", 100);
			value47.put("latitude", "-8.578305");
			value47.put("longitude", "116.328306");
			value47.put("Foto", "g5_lakey");
			value47.put("Status", 0);
			value47.put("MisiID", 5);
			db.insertOrThrow("TEMPAT", null, value47);

			ContentValues value70 = new ContentValues();
			value70.put("id", 70);
			value70.put("nama", "Gunung Bromo");
			value70.put(
					"deskripsi",
					"This National Park is one of the most beautiful places of interest in East Java. The beauties of mountain covered, give a special and characteristic green plants, arousing great interest. Tenggerese traditional farming also makes this famous place being more interesting and attractive. In addition, cool and breezy wind always blows freshly giving ever visitor special deep impression a unforgettable memories. Mount Bromo will remember us about The Story of Offering Kasada Ceremony");
			value70.put("point", 100);
			value70.put("latitude", "-7.926635");
			value70.put("longitude", "112.948959");
			value70.put("Foto", "g6_bromo");
			value70.put("Status", 0);
			value70.put("MisiID", 6);
			db.insertOrThrow("TEMPAT", null, value70);

			ContentValues value71 = new ContentValues();
			value71.put("id", 71);
			value71.put("nama", "Batu Night Square");
			value71.put(
					"deskripsi",
					"Batu Night Spectacular (BNS) is a tourist resort that can found at night. Your travel will completed with this resort. BNS is located in Batu-East Java-Indonesia. It is about 30 minutes from Malang city. Batu Night Spectacula is designed for night recreation. You can enjoy the game adventure like rollercoaster, lampion garden, and the biggest show of fireworks. From all around Batu Night Spectacular, you will see the beautiful night Malang city from the top, like you in the top of hill. You can ride by cycle and will see the shining light.");
			value71.put("point", 50);
			value71.put("latitude", "-7.89655");
			value71.put("longitude", "112.534567");
			value71.put("Foto", "g6_bns");
			value71.put("Status", 0);
			value71.put("MisiID", 6);
			db.insertOrThrow("TEMPAT", null, value71);

			ContentValues value72 = new ContentValues();
			value72.put("id", 72);
			value72.put("nama", "Gunung Semeru");
			value72.put(
					"deskripsi",
					"A mountainous pleasure on the slope of the highest mount in Java, Mount Semeru. The beautiful and attractive panorama or special atmosphere can be found here beside its cool and fresh air. In this area we find a good place for Para-gliding and the Dutch Colonial Remnant.This object lies on Argo Yuwono village, Ampelgading district, about 7 km to the North from Ampelgading.The mount climbers are often visit this mount to enjoy its nature scenery and beautiful sunset, look from the peak of the mount.");
			value72.put("point", 100);
			value72.put("latitude", "-8.075546");
			value72.put("longitude", "112.920012");
			value72.put("Foto", "g6_semeru");
			value72.put("Status", 0);
			value72.put("MisiID", 6);
			db.insertOrThrow("TEMPAT", null, value72);

			ContentValues value73 = new ContentValues();
			value73.put("id", 73);
			value73.put("nama", "Jawa Timur Park 2");
			value73.put(
					"deskripsi",
					"The town of Batu, about 100 kilometres south of Surabaya, has long been popular as a relaxing holiday destination for people from Surabaya and beyond, mostly because of its cool mountain weather. Jawa Timur Park 2 consists of three establishments located within the one location - Batu Secret Zoo, Pohon Inn, and Museum Satwa. Jatim Park 2 is nice family holiday destination over Jawa Timur.");
			value73.put("point", 50);
			value73.put("latitude", "-7.870864");
			value73.put("longitude", "112.525985");
			value73.put("Foto", "g6_jatimpark2");
			value73.put("Status", 0);
			value73.put("MisiID", 6);
			db.insertOrThrow("TEMPAT", null, value73);

			ContentValues value74 = new ContentValues();
			value74.put("id", 74);
			value74.put("nama", "Cheng Hou Mosque");
			value74.put(
					"deskripsi",
					"The Architecture of Muhammad Cheng Hoo Mosque, which located in Gading Street Surabaya, is enough artistic. It is built by allying Islam culture, Java, and Chinese that predominated by green color, turned yellow, and squeezed. The form of the building is typical of Chinese with 'Joglo' Java. This is show tightly between Chinese cultures and Java, which have intertwined since former. Cheng Hoo is also inspiration name of 'Admiral Cheng Hoo', which become a Moslem when admission to Majapahit empire. The Chinese Moslem community had built this mosque wish to remind again that Chinese also propagates Islam taught.");
			value74.put("point", 100);
			value74.put("latitude", "-8.270612");
			value74.put("longitude", "113.98854");
			value74.put("Foto", "g7_chenghou");
			value74.put("Status", 0);
			value74.put("MisiID", 7);
			db.insertOrThrow("TEMPAT", null, value74);

			ContentValues value75 = new ContentValues();
			value75.put("id", 75);
			value75.put("nama", "Kenjeran Beach");
			value75.put(
					"deskripsi",
					"Kenjeran beach is located in Kenjeran district in the east top Surabaya, which is ± 9 km from Surabaya. Kenjeran beach has divided into two beaches; they are Old Kenjeran Beach and New Kenjeran Beach.The activities that can do in Old Kenjeran Beach are enjoying the panorama of the beach, fishing, sailing, and buying sea fishes. While the activities in New Kenjeran Beach are more sport activities, such as; Tennis, Horseracing, Motorbike, Race, Swimming, Fishing, Playground, and places of worship.");
			value75.put("point", 100);
			value75.put("latitude", "-7.218245");
			value75.put("longitude", "112.768894");
			value75.put("Foto", "g7_kenjeran");
			value75.put("Status", 0);
			value75.put("MisiID", 7);
			db.insertOrThrow("TEMPAT", null, value75);

			ContentValues value100 = new ContentValues();
			value100.put("id", 100);
			value100.put("nama", "Tugu Pahlawan");
			value100.put(
					"deskripsi",
					"The warrior monument is located in Tembaan Street. It had built for the agenda of giving high respect to all warriors that had killed during big encounter to fight against ally army being hitchhiked by NICA, which wish to occupy Surabaya in 10 Novembers 1945. It is located in front of luxury Gubenur office. The Heroes Monument is a warrior spirit symbol of “arek-arek Suroboyo” (Surabaya's People) in faced the colonist. The gateway towards the Heroes Monument area, has built to looks like Bentar Temple, which is then called as Bentar Gateway. From front of side, we can look around eight relieves that depict about the development of Surabaya city. Landing story of Tar-Tar army in Hujung Galuh, the development of Kalimas estuary as the Surabaya genesis.");
			value100.put("point", 50);
			value100.put("latitude", "-7.245492");
			value100.put("longitude", "112.737824");
			value100.put("Foto", "g6_bromo");
			value100.put("Status", 0);
			value100.put("MisiID", 7);
			db.insertOrThrow("TEMPAT", null, value100);

			ContentValues value76 = new ContentValues();
			value76.put("id", 76);
			value76.put("nama", "Suramadu Bridge");
			value76.put(
					"deskripsi",
					"The Suramadu Bridge (Indonesian: Jembatan Suramadu), also known as the Surabaya–Madura Bridge, is cable-stayed bridge between Surabaya on the island of Java and the town of Bangkalan on the island of Madura in Indonesia. This bridge is the longest bridge in Indonesia today.");
			value76.put("point", 50);
			value76.put("latitude", "-7.050468");
			value76.put("longitude", "112.793487");
			value76.put("Foto", "g7_suramadu");
			value76.put("Status", 0);
			value76.put("MisiID", 7);
			db.insertOrThrow("TEMPAT", null, value76);

			ContentValues value77 = new ContentValues();
			value77.put("id", 77);
			value77.put("nama", "Tangkuban Perahu ");
			value77.put(
					"deskripsi",
					"Tangkuban Perahu (spelt Tangkuban Parahu in the local Sundanese dialect) is a dormant volcano 30 km north of the city of Bandung, the provincial capital of West Java, Indonesia. When seen from Bandung, Mt. Tangkuban Perahu has a distinctive shape, like an upside down boat. Tangkuban Perahu means, in fact, 'up-turned boat' This peculiar shape has stimulated the fantasy of the Sundanese people from early times as expressed in the Legend of Sangkuriang. It is a popular tourist attraction where tourists can hike or ride to the edge of the crater to view the hot water springs and boiling mud up close, and buy eggs cooked on the hot surface.");
			value77.put("point", 50);
			value77.put("latitude", "-6.768986");
			value77.put("longitude", "107.599833");
			value77.put("Foto", "g8_tangkuban");
			value77.put("Status", 0);
			value77.put("MisiID", 8);
			db.insertOrThrow("TEMPAT", null, value77);

			ContentValues value78 = new ContentValues();
			value78.put("id", 78);
			value78.put("nama", "Wisata Kawah Putih Ciwidey Bandung");
			value78.put(
					"deskripsi",
					"Kawah Putih (English: White Crater) is a striking crater lake and tourist spot in a volcanic crater about 50 km south of Bandung in West Java in Indonesia. Kawah Putih can easy be reached by car all the way to the rim and then it is a 100 metre walk to the lake it self. It is a very nice colorful lake. Far to the south of Bandung, the hill becomes greener. The fresh air flows through the bamboo trees marching along the way. Kawah Putih, The White Crater, is waiting for who want to contemplate there. Kawah putih is relatively easy to be reached. But, driving bu visi owntor car is recommended, it's convenience because along the way, visitor can stop by to just take a rest, or buy strawberry, which can be easily found in this area. Fresh and considerably cheap.");
			value78.put("point", 100);
			value78.put("latitude", "-7.137004");
			value78.put("longitude", "107.397339");
			value78.put("Foto", "g8_kawahputih");
			value78.put("Status", 0);
			value78.put("MisiID", 8);
			db.insertOrThrow("TEMPAT", null, value78);

			ContentValues value79 = new ContentValues();
			value79.put("id", 79);
			value79.put("nama", "Gedung Sate");
			value79.put(
					"deskripsi",
					"Gedung Sate is one of the old heritage bilding located in Bandung, West Java. Gedung Sate is a monumental work of architect Ir. Gerber. This building has a mix of traditional architecture andconstruction techniques of Western and Indonesia, which is often referred to as Indo Eropeesche Architectuurstijln. Ornaments six pole with sphere-like satay skewers  placed on theroof top batch, as a symbol of Gedung Sate. Gedung Sate is one of the favorite tourist attraction of foreign tourists in Bandung, since it has strong relationship of emotion and history of this building. The linkage of emotion and history will probably feel more complete if you climb the stairs one at a time that is available to the tower Gedung Sate. There are six steps that must be passed by each of 10 steps that must be climbed.");
			value79.put("point", 50);
			value79.put("latitude", "-6.902228");
			value79.put("longitude", "107.618829");
			value79.put("Foto", "g8_gedungsate");
			value79.put("Status", 0);
			value79.put("MisiID", 8);
			db.insertOrThrow("TEMPAT", null, value79);

			ContentValues value80 = new ContentValues();
			value80.put("id", 80);
			value80.put("nama", "Saung Angklung Mang Udjo");
			value80.put(
					"deskripsi",
					"Saung Angklung Mang Udjo offer a tourist attraction with Angklung performances and demonstrations featuring a variety of other Sundanese arts brought by a group of local children. Come and enjoy performances and learn how to play the Angklung in this special place, which is always ready to welcome and give room for any appreciation of you and other visitors. Do not miss also see how the musical instrument Angklung, which is as interesting as watching the show itself. ");
			value80.put("point", 50);
			value80.put("latitude", "-6.900865");
			value80.put("longitude", "107.653912");
			value80.put("Foto", "g8_udjo");
			value80.put("Status", 0);
			value80.put("MisiID", 8);
			db.insertOrThrow("TEMPAT", null, value80);

			ContentValues value81 = new ContentValues();
			value81.put("id", 81);
			value81.put("nama", "Brastagi");
			value81.put(
					"deskripsi",
					"Berastagi, a tourist town, is another lovely town located in Karo highlands. The town is known for its plantations and various kinds of flowers, vegetables and fruit, most famous which is Marquisa passion fruit. It's 66 km southwest of Medan and is 4.594 feet above sea level. From this city, the visitors will enjoy charming scenery to the active mountainside, which are Sibayak Mountain and Sinabung Mountain. The atmosphere of green nature from the reflection of the trees from the slope of Rangkap Sibayak Mountain (well known as Sibayak mountain) made the Berastagi city had flooded by tourists.");
			value81.put("point", 50);
			value81.put("latitude", "3.591077");
			value81.put("longitude", "98.654757");
			value81.put("Foto", "g9_brastagi");
			value81.put("Status", 0);
			value81.put("MisiID", 9);
			db.insertOrThrow("TEMPAT", null, value81);

			ContentValues value82 = new ContentValues();
			value82.put("id", 82);
			value82.put("nama", "Danau Toba");
			value82.put(
					"deskripsi",
					"The world famous crater lake of Danau (Lake) Toba is the third biggest tourist destination of Indonesia. The island in the huge lake, Pulau Samosir, attracts many tourists. Lake Toba is actually more like an ocean. Lake Toba is the largest lake in Southeast Asia. There are of course legends on how Lake Toba was formed. The following is a Batak Toba legend.");
			value82.put("point", 50);
			value82.put("latitude", "2.860749");
			value82.put("longitude", "98.618345");
			value82.put("Foto", "g9_danautoba");
			value82.put("Status", 0);
			value82.put("MisiID", 9);
			db.insertOrThrow("TEMPAT", null, value82);

			ContentValues value83 = new ContentValues();
			value83.put("id", 83);
			value83.put("nama", "Jangga Village");
			value83.put(
					"deskripsi",
					"Come and experience traditional Batak life in a village that remains largely untouched by the modern world. Located in the picturesque hillside, visitors come to Jangga village to meet native Batak people and see how their unique culture continues to thrive today. Jangga is most famous for the beautiful ulos cloths which are produced here. Watch the women of the community weave these intricate cloths from inside their booths. In Jangga you will also find rows of traditional houses.  There are cultural and historical attractions too, such as the remains left by Batak kings centuries ago including King Tambun and King Ma nurung monuments.");
			value83.put("point", 100);
			value83.put("latitude", "3.625812");
			value83.put("longitude", "98.640318");
			value83.put("Foto", "g9_jangga");
			value83.put("Status", 0);
			value83.put("MisiID", 9);
			db.insertOrThrow("TEMPAT", null, value83);

			ContentValues value84 = new ContentValues();
			value84.put("id", 84);
			value84.put("nama", "Sipiso-piso Waterfall");
			value84.put(
					"deskripsi",
					"Set in the beautiful highlands of North Sumatra, the stunning Sipiso-piso waterfall is located on the North side of Lake Toba, 24 kms from Kabanjahe. This long but narrow waterfall drops 120 meters into an impressive gorge below.This thundering waterfal is popular with visitors who come to photograph this magnificent natural wonder. Surrounded by a lush green mountainscape and with a rainbow forming at the base of the waterfall, it’s hard to imagine a more impressive subject for a photograph.The waterfall can be viewed from gazebos near the food and souvenir stands.");
			value84.put("point", 100);
			value84.put("latitude", "2.927012");
			value84.put("longitude", "98.534782");
			value84.put("Foto", "g9_sipisopiso");
			value84.put("Status", 0);
			value84.put("MisiID", 9);
			db.insertOrThrow("TEMPAT", null, value84);
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