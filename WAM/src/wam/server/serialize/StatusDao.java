package wam.server.serialize;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import wam.server.ContextListener;

public class StatusDao {

	public static StatusDao instance = new StatusDao();
	
	private StatusDao() {}
	
	//für Terminübersicht_Kunde & Friseur:
	public void approve(int idTermine) {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement blocker = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("update Terminbuchung.Termine t inner join Terminbuchung.Blocker b on t.datum = b.datum and t.uhrzeit = b.startzeit \n" + 
					"set t.status = 'angenommen', t.idBlocker = b.idBlocker where t.idTermine = ?"); 
			
			Appointment a = AppointmentDao.instance.getTerminById(idTermine);
			blocker = con.prepareStatement("insert into Blocker(datum, startzeit, endzeit) values (?,?,?)");
			
			ps.setInt(1, idTermine);
			
			Time t = Time.valueOf(a.uhrzeit);
			blocker.setString(1, a.datum);
			blocker.setString(2, t.toString());
			blocker.setString(3, t.toLocalTime().plusMinutes(60).toString()+ ":00");
			
			blocker.execute();
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}finally {
			try { if(blocker!=null) blocker.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
	}
	
	
	//für Terminübersicht_Kunde & Friseur:
	public void decline(int idTermine) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("update Termine SET status = 'abgelehnt' WHERE idTermine = ?"); 
			ps.setInt(1, idTermine);
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
