package wam.server.serialize;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment {
	public int idTermine;
	public int idBenutzer;
	public String datum;
	public String uhrzeit;
	public String frisur;
	public String status;
	public int idBlocker;
	
	public Appointment(int idTermine, int idBenutzer, String datum, String uhrzeit, String frisur, String status) {
		super();
		this.idTermine = idTermine;
		this.idBenutzer = idBenutzer;
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.frisur = frisur;
		this.status = status;
	}
	
	public Appointment(ResultSet rs) {
		super();
		try {
			this.idTermine = rs.getInt(1);
			this.idBenutzer = rs.getInt(2);
			this.datum = rs.getString(3);
			this.uhrzeit = rs.getString(4);
			this.frisur = rs.getString(5);
			this.status = rs.getString(6);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
