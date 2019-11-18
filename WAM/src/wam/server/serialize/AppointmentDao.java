package wam.server.serialize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wam.server.ContextListener;

public class AppointmentDao {

	public static AppointmentDao instance = new AppointmentDao();
	
	private AppointmentDao() {}
	
	//public ArrayList<Appointment> getAppointment() {
	//	return null;
	//}
	
	public void store(Appointment a) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("insert into termine (idBenutzer, datum, frisur) values (?,?,?)"); 
			
			ps.setInt(1,a.idBenutzer);
			ps.setString(2,a.datum);
			ps.setString(3,a.frisur);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}
	public ArrayList<Appointment> getAppointments(int idBenutzer) {
		ArrayList<Appointment> all_appointments = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from termine where idBenutzer = ?");
			
			ps.setInt(1,idBenutzer);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Appointment a = new Appointment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				all_appointments.add(a);
			}
			
			return all_appointments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		return null;
	}
}