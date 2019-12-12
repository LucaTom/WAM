package wam.server.serialize;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	public int id;
	public String vorname;
	public String nachname;
	public String adresse;
	public String mailadresse;
	public String telefonnummer;
	public String benutzername;
	public String passwort;
	public int usergroupid;
	
	public User(int id, String vorname, String nachname, String adresse, String mailadresse, String telefonnummer,
			String benutzername, String passwort, int usergroupid) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.mailadresse = mailadresse;
		this.telefonnummer = telefonnummer;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.usergroupid = usergroupid;
	}
	
	public User (ResultSet rs) {
		super();
		try {
			this.id = rs.getInt(1);
			this.vorname = rs.getString(2);
			this.nachname = rs.getString(3);
			this.adresse = rs.getString(4);
			this.mailadresse = rs.getString(5);
			this.telefonnummer = rs.getString(6);
			this.benutzername = rs.getString(7);
			this.passwort = rs.getString(8);
			this.usergroupid = rs.getInt(9);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
