package wam.server.serialize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wam.server.ContextListener;

public class StornierenDao {

	public static StornierenDao instance = new StornierenDao();
	
	private StornierenDao() {}
	
	//für Terminübersicht_Kunde:
	public void stornieren(int idTermine) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = ContextListener.getDataSource().getConnection();
			ps = con.prepareStatement("delete t, b FROM Termine t JOIN Blocker b on t.idBlocker = b.idBlocker WHERE idTermine= ?");
			 
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