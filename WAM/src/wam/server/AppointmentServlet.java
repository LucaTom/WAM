package wam.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wam.server.serialize.Appointment;
import wam.server.serialize.AppointmentDao;
import wam.server.serialize.BlockerDao;
import wam.server.serialize.User;
import wam.server.serialize.UserDao;

/**
 * Servlet implementation class AppointmentServlet
 */@WebServlet(
			description = "/AppointmentServlet",
			urlPatterns = {
					"/Appointment",		
					})
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
			String uhrzeit = request.getParameter("uhrzeit");
			String frisur = request.getParameter("frisur");
			String status = request.getParameter("status");
			
			
			
			if (AppointmentDao.instance.checkAppointment(datum, uhrzeit) == true && BlockerDao.instance.checkAppointmentForBlocker(datum, uhrzeit) == true)
			{
				Appointment a = new Appointment(-1, idBenutzer, datum, uhrzeit, frisur, status);	
				AppointmentDao.instance.store(a);
				response.sendRedirect("terminuebersicht_kunde.jsp");
			}
			else 
			{
				request.setAttribute("error_msg", "Dieser Termin ist leider schon vergeben - Wähle einen anderen!");
				request.getRequestDispatcher("neuerTermin_kunde.jsp").forward(request, response);
			}
			
			
			

	}
	
}
