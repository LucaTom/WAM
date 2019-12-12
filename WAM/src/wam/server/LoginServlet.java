package wam.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Value;

import wam.server.serialize.User;
import wam.server.serialize.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(
		description = "/LoginServlet",
		urlPatterns = {
				"/Login",		
				})

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logout= request.getParameter("logout").toLowerCase().trim();
		if ("1".equals(logout)) {
			request.getSession().setAttribute("idBenutzer", null);
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
			//response.getWriter().append("See you!");
		}else {
			//response.getWriter().append("Use the FORM");
			response.sendRedirect("login.jsp");
			} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String benutzername = request.getParameter("benutzername");
		String passwort = request.getParameter("passwort");
		
		if (UserDao.instance.auth(benutzername, passwort)) {
			User u = UserDao.instance.getUserById(UserDao.instance.getid(benutzername));
			request.getSession().setAttribute("idBenutzer",u.id);
			request.getSession().setAttribute("usergroupid",u.usergroupid);
			
			
			if (UserDao.instance.auth_Friseur(u)){
				
				response.sendRedirect("terminuebersicht_friseur.jsp");
			}else {
				response.sendRedirect("terminuebersicht_kunde.jsp");
			}
		} else {
			request.setAttribute("error_msg", "Benutzername oder Passwort sind falsch - Versuche es erneut!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
	}
}
