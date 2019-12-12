package wam.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wam.server.serialize.AppointmentDao;
import wam.server.serialize.Blocker;
import wam.server.serialize.BlockerDao;


/**
 * Servlet implementation class BlockerLoeschen
 */
@WebServlet(
		description = "/BlockerLoeschenServlet",
		urlPatterns = {
				"/BlockerLoeschen",		
				})
public class BlockerLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockerLoeschenServlet() {
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

		int idBlocker = Integer.parseInt((String) request.getParameter("loeschen"));
		
			BlockerDao.instance.loeschen(idBlocker);

	response.sendRedirect("datumBlocken_friseur.jsp");
	}

}
