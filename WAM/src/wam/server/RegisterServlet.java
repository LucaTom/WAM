package wam.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wam.server.serialize.User;
import wam.server.serialize.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(
		description = "/RegisterServlet",
		urlPatterns = {
				"/Register",		
				})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String adresse = request.getParameter("adresse");
		String mailadresse = request.getParameter("mailadresse");
		String telefonnummer = request.getParameter("telefonnummer");
		String benutzername = request.getParameter("benutzername");
		String passwort = request.getParameter("passwort");
		
		//Überpürfung, ob ausgewählter Benutzername noch verfügbar
		if (UserDao.instance.checkBenutzername(benutzername) == true) 
		{
		//der neue User wird in die Datenbank aufgenommen & auf die Loginseite weitergeleitet
		User u = new User(-1, vorname, nachname, adresse, mailadresse, telefonnummer, benutzername, passwort, 1);	
		UserDao.instance.store(u);
		response.sendRedirect("login.jsp");
		}
		else 
		{
		response.sendRedirect("registrieren.jsp");			
		}
	
	}
}
