package wam.server.serialize;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import wam.server.ContextListener;

public class AppointmentDao {

	public static AppointmentDao instance = new AppointmentDao();
	
	private AppointmentDao() {}
	
	/*public ArrayList<Appointment> getAppointment() {
		return null;
	}*/
	
	//für neuerTermin_Kunde:
	//BEVOR der Termin in die DB übergeben wird
	public boolean checkAppointment(String datum, String uhrzeit) {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT COUNT(*) FROM Termine WHERE datum =? and uhrzeit=? and NOT status = 'abgelehnt'");
			ps.setString(1, datum);
			ps.setString(2, (uhrzeit +":00"));
			
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
	
	//für neuerTermin_Kunde:
	public void store(Appointment a) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("insert into Termine (idBenutzer, datum, uhrzeit, frisur) values (?,?,?,?)"); 
			
			ps.setInt(1,a.idBenutzer);
			ps.setString(2,a.datum);
			ps.setString(3,(a.uhrzeit+":00"));
			ps.setString(4,a.frisur);
			
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
	
	//für Terminübersicht_Kunde:
	public ArrayList<Appointment> getAppointments(int idBenutzer) {
		ArrayList<Appointment> kunde_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Termine where idBenutzer = ? and NOT status = 'abgelehnt' ORDER BY datum DESC, uhrzeit ASC");
			
			ps.setInt(1,idBenutzer);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment a = new Appointment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				kunde_appointments.add(a);
			}
			
			return kunde_appointments;
			
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
	
	//für Terminübersicht_Friseur:
	public ArrayList<Appointment> getAll_Appointments(){
		ArrayList<Appointment> friseur_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Termine WHERE NOT status = 'abgelehnt' ORDER BY datum DESC, uhrzeit ASC");
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment b = new Appointment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));
				friseur_appointments.add(b);
			}
			
			return friseur_appointments;
			
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
	

	//für Einzelkundenübersicht_Friseur:
	public ArrayList<Appointment> einzelkunde_appointments(int idBenutzer) {
		ArrayList<Appointment> einzelkunde_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Termine WHERE idBenutzer = ? ORDER BY datum DESC, uhrzeit ASC");
			
			
			ps.setInt(1,idBenutzer);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment a = new Appointment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				einzelkunde_appointments.add(a);
			}
			
			return einzelkunde_appointments;
			
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

	//für abgelehnteTermine_Kunde:
	public ArrayList<Appointment> abgelehnte_appointments(int idBenutzer) {
		ArrayList<Appointment> kunde_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Termine where idBenutzer = ? and status = 'abgelehnt' ORDER BY datum DESC, uhrzeit ASC");
			
			ps.setInt(1,idBenutzer);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment a = new Appointment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				kunde_appointments.add(a);
			}
			
			return kunde_appointments;
			
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
	
	//für abgelehnteTermine_Friseur:
	public ArrayList<Appointment> abgelehnte_Allappointments() {
		ArrayList<Appointment> friseur_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Termine WHERE status = 'abgelehnt' ORDER BY datum DESC, uhrzeit ASC");
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment b = new Appointment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));
				friseur_appointments.add(b);
			}
			
			return friseur_appointments;
			
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
	
	//für Appointment:
	public Appointment getTerminById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Termine WHERE idTermine = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			return new Appointment(rs);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return null;
	}
	
	//für Terminübersicht_Friseur:
	public ArrayList<Appointment> getTerminByDate(String datum_filter) {
		ArrayList<Appointment> filter_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Termine WHERE datum = ?");
			ps.setString(1,datum_filter);
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment b = new Appointment(rs);
				filter_appointments.add(b);
			}
			return filter_appointments;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return null;
	}
}



