package project.TheExplorer.Model;

public class Tempat {

	private int ID;
	private String nama;
	private String deskripsi;
	private double latitude;
	private double longitude;
	private String foto;
	private int status;
	private int MisiID;

	public Tempat(int ID, String nama, String deskripsi, double latitude,
			double longitude, String foto, int status,
			int misiID) {
		this.ID = ID;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.latitude = latitude;
		this.longitude = longitude;
		this.foto = foto;
		this.status = status;
		this.MisiID = misiID;

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public void setNama(String Nama) {
		this.nama = Nama;
	}

	public String getNama() {
		return nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String Foto) {
		this.foto = Foto;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int Status) {
		this.status = Status;
	}

	public int getMisiID() {
		return MisiID;
	}

	public void setMisiID(int MisiID) {
		this.MisiID = MisiID;
	}
}
