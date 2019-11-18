package wam.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wam.server.serialize.Appointment;
import wam.server.serialize.AppointmentDao;
import wam.server.serialize.User;
import wam.server.serialize.UserDao;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentServlet() {
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
			int idBenutzer = (Integer)request.getSession().getAttribute("idBenutzer");
			String datum = request.getParameter("datum");
			System.out.println(datum);
			String frisur = request.getParameter("frisur");
			String status = request.getParameter("status");
			
			Appointment a = new Appointment(-1, idBenutzer, datum, frisur, status);	
			AppointmentDao.instance.store(a);
			response.sendRedirect("terminuebersicht_kunde.jsp");
			
		doGet(request, response);
	}

}
