package wam.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wam.server.serialize.Appointment;
import wam.server.serialize.AppointmentDao;

/**
 * Servlet implementation class All_AppointmentServlet
 */
@WebServlet(
		description = "/All_AppointmentServlet",
		urlPatterns = {
				"/All_Appointment",		
				})

public class All_AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public All_AppointmentServlet() {
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
		int idTermine = Integer.parseInt(request.getParameter("idTermine"));
		int idBenutzer = Integer.parseInt(request.getParameter("idBenutzer"));
		String datum = request.getParameter("datum");
		String uhrzeit = request.getParameter("uhrzeit");
		String frisur = request.getParameter("frisur");
		String status = request.getParameter("status");
		
		Appointment b = new Appointment(idTermine, idBenutzer, datum, uhrzeit, frisur, status);	
		
	doGet(request, response);
}
}
