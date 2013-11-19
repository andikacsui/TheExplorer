package project.TheExplorer.Model;

public class Tempat {

	private int ID;
	private String nama;
	private String alamat;
	private String titikPoint;
	private String foto;
	private int status;
	private int MisiID;

	public Tempat(int ID, String nama, String alamat, String titikPoint,
			String foto, int status, int misiID) {
		this.ID = ID;
		this.nama = nama;
		this.alamat = alamat;
		this.titikPoint = titikPoint;
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

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTitikPoint() {
		return titikPoint;
	}

	public void setTitikPoint(String TitikPoint) {
		this.titikPoint = TitikPoint;
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
