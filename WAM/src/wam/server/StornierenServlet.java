package wam.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wam.server.serialize.StornierenDao;
import wam.server.serialize.UserDao;

/**
 * Servlet implementation class StornierenServlet
 */
@WebServlet(
		description = "/StornierenServlet",
		urlPatterns = {
				"/Stornieren",		
				})
public class StornierenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StornierenServlet() {
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
		for(String s : request.getParameterValues("stornieren")) System.out.println(s);
		

		String stornieren= request.getParameter("stornieren");
		int idTermine = Integer.parseInt((String) request.getParameter("idTermine"));
		System.out.println(idTermine);

			StornierenDao.instance.stornieren(idTermine);

	response.sendRedirect("terminuebersicht_kunde.jsp");
	}
}
