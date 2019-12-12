package wam.server.serialize;

public class Blocker {
	public int idBlocker;
	public String datum;
	public String startzeit;
	public String endzeit;
	public int idBenutzer;

	public Blocker(int idBlocker, String datum, String startzeit, String endzeit, int idBenutzer){
		super();
		this.idBlocker = idBlocker;
		this.datum = datum;
		this.startzeit = startzeit;
		this.endzeit = endzeit;
		this.idBenutzer = idBenutzer;
	}
}
