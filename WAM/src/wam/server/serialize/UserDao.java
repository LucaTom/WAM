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
	
	//für das Registrieren: 
	//BEVOR neuer Kunde in die DB aufgenommen wird
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
			
			rs= ps.executeQuery();
			rs.next();
			if (rs.getInt(1)==0) return true;
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return false;

	}
	
	
	//für das Registrieren: 
	public void store(User u) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("insert into Benutzer (vorname, nachname, adresse, mailadresse, telefonnummer, benutzername, passwort, usergroupid) "
					+ "values (?,?,?,?,?,?,?,?)"); 
			
			ps.setString(1, u.vorname);
			ps.setString(2, u.nachname);
			ps.setString(3, u.adresse);
			ps.setString(4, u.mailadresse);
			ps.setString(5, u.telefonnummer);
			ps.setString(6, u.benutzername);
			ps.setString(7, u.passwort);
			ps.setInt(8, u.usergroupid);
			
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
	
	//für den Login:
	public boolean auth(String benutzername, String passwort) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			System.out.print("send help");
			ps = con.prepareStatement("SELECT Count(*) FROM Benutzer WHERE benutzername = ? and passwort = ?"); 
		//Anzahl der Zeilen mit dem gewählten benutzername und passwort 
			ps.setString(1, benutzername);
			ps.setString(2, passwort);
			
			rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));
		//stimmt überein wenn Ergebnis = 1
			return rs.getInt(1)==1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		
		return false;
	}
	
	//für den Login:
	public boolean auth_Friseur(User u) {
		
		 return u.usergroupid == 2; 
		
	}
	
	
	public int getid(String benutzername) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT id FROM Benutzer WHERE benutzername = ?"); 
			
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
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(ps!=null) ps.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return 0;
	}
	
	//für Kundenübersicht_Friseur: 
	public ArrayList<User> getAll_Users(){
		ArrayList<User> friseur_users = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Benutzer ORDER by vorname");
			rs = ps.executeQuery();
			while (rs.next()) {
				User a = new User(rs);
				friseur_users.add(a);
			}
			
			return friseur_users;
			
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
	public ArrayList<User> getUser(int id){
		ArrayList<User> friseur_user = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("Select * from Benutzer where id = ?");
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				User a = new User(rs);
				friseur_user.add(a);
			}
			
			return friseur_user;
			
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
	
	//für Terminübersicht_Friseur; Ausgabe des Namens statt der BenutzerID:
	public User User_name(int idBenutzer){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Benutzer JOIN Termine ON Benutzer.id = Termine.idBenutzer WHERE id = ? GROUP BY vorname, nachname");
			ps.setInt(1,idBenutzer);
			
			rs = ps.executeQuery();
			rs.next();
			User u = new User(-1,rs.getString(2),rs.getString(3),"","", "","","",-1);
			return u;
			
			
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

	public User getUserById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("SELECT * FROM Benutzer WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			return new User(rs);
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
