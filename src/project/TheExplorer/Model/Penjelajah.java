package project.TheExplorer.Model;

public class Penjelajah {
	private int ID;
	private String username;
	private String password;
	private int skor;
	private String LastCheckIn;

	int asd;

	public Penjelajah(int iD2, String username2, String password2,
			int skor2, String lastCheckIn2) {
		this.ID = iD2;
		this.username = username2;
		this.password = password2;
		this.skor = skor2;
		this.LastCheckIn = lastCheckIn2;

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setTwitter(String password) {
		this.password = password;
	}
	public int getSkor() {
		return skor;
	}

	public void setSkor(int skor) {
		this.skor = skor;
	}

	public String getLastCheckIn() {
		return LastCheckIn;
	}

	public void setSkor(String LastCheckIn) {
		this.LastCheckIn = LastCheckIn;
	}

}
