package project.TheExplorer.Model;

public class Tempat {

	private int ID;
	private String nama;
	private String deskripsi;
	private String titikPoint;
	private String foto;
	private String status;
	private int MisiID;

	public Tempat(int ID, String nama, String deskripsi, String titikPoint,
			String foto, String status, int misiID) {
		this.ID = ID;
		this.nama = nama;
		this.deskripsi = deskripsi;
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

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String Deskripsi) {
		this.deskripsi = Deskripsi;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String Status) {
		this.status = Status;
	}

	public int getMisiID() {
		return MisiID;
	}

	public void setMisiID(int MisiID) {
		this.MisiID = MisiID;
	}
}
