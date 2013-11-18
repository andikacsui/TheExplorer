package project.TheExplorer.Model;

public class Misi {
	private int ID;
	private String nama;
	private String deskripsi;
	private String lokasi;
	private String foto;
	private String status;
	private String badge;
	private int penjelajahID;

	public Misi(int ID, String nama, String deskripsi, String lokasi,
			String foto, String status, String Badge, int penjelajahID) {
		this.ID = ID;
		this.nama = nama;
		this.deskripsi = deskripsi;
		this.lokasi = lokasi;
		this.foto = foto;
		this.status = status;
		this.badge = Badge;
		this.penjelajahID = penjelajahID;

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String Nama) {
		this.nama = Nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String Deskripsi) {
		this.deskripsi = Deskripsi;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String Lokasi) {
		this.lokasi = Lokasi;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String Badge) {
		this.badge = Badge;
	}

	public int penjelajahID() {
		return penjelajahID;
	}

	public void setPenjelajahID(int penjelajahID) {
		this.penjelajahID = penjelajahID;
	}

}
