package wam.server;

import wam.server.serialize.User;
import wam.server.serialize.UserDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;


/**
 * Application Lifecycle Listener implementation class DbPmPlusContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	private static DataSource dataSource;

	/**
	 * Default constructor. 
	 */
	public ContextListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)  { 
		System.out.println("und tschuess...");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)  { 
		System.getProperties().setProperty("Dorg.apache.cxf.stax.allowInsecureParser", "1");
		System.out.println("sys prop set");

		try{
			dataSource = getDBConnection();
		}catch (Throwable t) {
			System.out.println("nevermind");
			t.printStackTrace();
		}
	}
	
	private static DataSource getDBConnection() {
		DataSource ds = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/Terminbuchung");
			System.out.println("Database Connection created successfully");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("Databases connection failed");
		}
		return ds;

	}

	public static DataSource getDataSource() {
		return dataSource;
	}
	

}
