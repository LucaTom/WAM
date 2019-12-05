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
	
	public boolean checkBenutzername (String benutzername) {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT COUNT(*) FROM Benutzer WHERE benutzername = ?");
			ps.setString(1, benutzername);
	//die Datenbank gibt aus wieviele benutzer schon den selben Benutzernamen habe. 
	//Optimalerweise ist das Ergebnis = 0; andernfalls muss der neue Benutzer einen anderen Benutzer auswählen
			
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
			try {rs.close(); } catch(SQLException e) {};
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
		}
		return false;

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
			ps.setString(5, u.telefonnummer);
			ps.setString(6, u.benutzername);
			ps.setString(7, u.passwort);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
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
		}finally {
			try {rs.close(); } catch(SQLException e) {};
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
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
		}finally {
			try {rs.close(); } catch(SQLException e) {};
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
		}
		return 0;
	}
	
	public ArrayList<User> getAll_Users(){
		ArrayList<User> friseur_users = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from benutzer ORDER by vorname");
			rs = ps.executeQuery();
			while (rs.next()) {
				User a = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
				friseur_users.add(a);
			}
			
			return friseur_users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}finally {
			try {rs.close(); } catch(SQLException e) {};
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
		}
		return null;
	}
	
	public ArrayList<User> getUser(int id){
		ArrayList<User> friseur_user = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from benutzer where id = ?");
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				User a = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
				friseur_user.add(a);
			}
			
			return friseur_user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}finally {
			try {rs.close(); } catch(SQLException e) {};
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
		}
		return null;
	}
	
	
	public ArrayList<User> User_name(int idBenutzer){
		ArrayList<User> user = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM benutzer JOIN termine ON benutzer.id = termine.idBenutzer WHERE id = ? GROUP BY vorname, nachname");
			ps.setInt(1,idBenutzer);
			
			rs = ps.executeQuery();
			rs.next();
			User u = new User(-1,rs.getString(2),rs.getString(3),"","", "","","");
			user.add(u);
			return user;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}finally {
			try {rs.close(); } catch(SQLException e) {};
			try {ps.close(); } catch(SQLException e) {};
			try {con.close(); } catch(SQLException e) {};
		}
		return null;
	}

	
}
