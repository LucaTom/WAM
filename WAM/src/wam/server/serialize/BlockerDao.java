package wam.server.serialize;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import wam.server.ContextListener;

public class BlockerDao {

	public static BlockerDao instance = new BlockerDao();
	
	private BlockerDao() {}
	

	/**
	 * Terminuhrzeit darf nicht im Zeitraum zwischen startzeit und endzeit des Blockers sein 
	 * @param datum des gewünschten Kundentermins
	 * @param uhrzeit des gewünschten Kundentermins
	 * @return true = es gibt kein Blocker zum gewünschten Termin (Termin ist möglich);  
	 * 		   false = es gibt ein Blocker zum gewünschten Termin (Termin ist nicht möglich)
	 */
	//für neuerTermin_Kunde:
	public boolean checkAppointmentForBlocker(String datum, String uhrzeit) {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT COUNT(*) FROM Terminbuchung.Blocker \n" + 
					"WHERE datum =? and Cast(startzeit as Time(0))  <= Cast(? as Time(0)) and Cast(endzeit as Time(0)) >= Cast(? as Time(0));");
		//CAST = die Uhrzeiten von String zu Time ändern um dann die Uhrzeiten miteinander vergleichen zu können 
		
			ps.setString(1, datum);
			ps.setString(2, uhrzeit);
			ps.setString(3, uhrzeit);
			rs= ps.executeQuery();
			rs.next();
			if (rs.getInt(1)==0) return true;
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return false;

	}
	
	//für datumBlocken_Friseur:
	public boolean checkFriseurBlocker(String startzeit, String endzeit) {
		
		Time s = Time.valueOf(startzeit + ":00");
		Time e = Time.valueOf(endzeit + ":00");
		
		return s.compareTo(e) < 0;
		// startzeit MUSS vor der endzeit sein 
	}
			
	
	//für datumBlocken_Friseur:
	public void store(Blocker b) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("insert into Blocker (datum, startzeit, endzeit, idBenutzer) values (?,?,?,?)"); 
			
			ps.setString(1,b.datum);
			ps.setString(2,(b.startzeit+":00"));
			ps.setString(3, (b.endzeit+":00"));
			ps.setInt(4, b.idBenutzer);
			
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
	}
	
	//für datumBlocken_Friseur:
	public ArrayList<Blocker> getBlocker(int idBenutzer){
		ArrayList<Blocker> friseur_blocker = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Blocker WHERE idBenutzer = ? ORDER BY datum DESC, startzeit ASC");
		//nur die vom Friseur gesetzten Blocker übergeben (nicht die automatisch gesetzten Terminblocker)
			ps.setInt(1, idBenutzer);
			rs = ps.executeQuery();
			while (rs.next()) {
				Blocker b = new Blocker(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				friseur_blocker.add(b);
			}
			return friseur_blocker;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return null;
	}
	
	//für datumBlocken_Friseur:
	public void loeschen(int idBlocker) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("delete FROM Blocker WHERE idBlocker = ?");
			 
			ps.setInt(1, idBlocker);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}finally {
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
	}
	
}