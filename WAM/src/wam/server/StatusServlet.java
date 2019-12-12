package wam.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Value;

import jdk.jshell.Snippet.Status;
//import wam.server.serialize.Status;
import wam.server.serialize.StatusDao;
import wam.server.serialize.UserDao;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet(
		description = "/StatusServlet",
		urlPatterns = {
				"/Status",		
				})
		
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet() {
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
		

		String status= request.getParameter("action");
		int idTermine = Integer.parseInt((String) request.getParameter("idTermine"));

		if (status.equals("angenommen")) {
			StatusDao.instance.approve(idTermine);
		}else {
			StatusDao.instance.decline(idTermine);
		}
		response.sendRedirect("terminuebersicht_friseur.jsp");	
	}

}
