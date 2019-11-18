package wam.server.serialize;

public class User {
	public int id;
	public String vorname;
	public String nachname;
	public String adresse;
	public String mailadresse;
	public int telefonnummer;
	public String benutzername;
	public String passwort;
	
	public User(int id, String vorname, String nachname, String adresse, String mailadresse, int telefonnummer,
			String benutzername, String passwort) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.mailadresse = mailadresse;
		this.telefonnummer = telefonnummer;
		this.benutzername = benutzername;
		this.passwort = passwort;
	}
}
