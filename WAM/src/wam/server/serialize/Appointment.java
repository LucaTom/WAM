package wam.server.serialize;

public class Appointment {
	public int idTermine;
	public int idBenutzer;
	public String datum;
	public String frisur;
	public String status;
	
	public Appointment(int idTermine, int idBenutzer, String datum, String frisur, String status) {
		super();
		this.idTermine = idTermine;
		this.idBenutzer = idBenutzer;
		this.datum = datum;
		this.frisur = frisur;
		this.status = status;
	}
}
