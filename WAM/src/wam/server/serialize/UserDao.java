package wam.server.serialize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wam.server.ContextListener;

public class UserDao {

	public static UserDao instance = new UserDao();
	
	private UserDao() {}
	
	public ArrayList<User> getUsers() {
		return null;
	}
	
	public void store(User u) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("insert into benutzer (vorname, nachname, adresse, mailadresse, telefonnummer, benutzername, passwort) "
					+ "values (?,?,?,?,?,?,?)"); 
			
			ps.setString(1, u.vorname);
			ps.setString(2, u.nachname);
			ps.setString(3, u.adresse);
			ps.setString(4, u.mailadresse);
			ps.setInt(5, u.telefonnummer);
			ps.setString(6, u.benutzername);
			ps.setString(7, u.passwort);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}
	
	public boolean auth(String benutzername, String passwort) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT Count(*) FROM benutzer WHERE benutzername = ? and passwort = ?"); 
			
			ps.setString(1, benutzername);
			ps.setString(2, passwort);
			
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1)==1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public int getid(String benutzername) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT id FROM benutzer WHERE benutzername = ?"); 
			
			ps.setString(1, benutzername);
			
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
