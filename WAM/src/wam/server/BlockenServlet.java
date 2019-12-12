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
import wam.server.serialize.StornierenDao;


/**
 * Servlet implementation class BlockenServlet
 */
@WebServlet(
		description = "/BlockenServlet",
		urlPatterns = {
				"/Blocken",		
				})
public class BlockenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockenServlet() {
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
		String datum = request.getParameter("datum");
		String startzeit = request.getParameter("startzeit");
		String endzeit = request.getParameter("endzeit");
		int idBenutzer = (Integer)request.getSession().getAttribute("idBenutzer");
		
		
		if (BlockerDao.instance.checkFriseurBlocker(startzeit, endzeit)){
			Blocker b = new Blocker(-1, datum, startzeit, endzeit,idBenutzer);	
			BlockerDao.instance.store(b);
			response.sendRedirect("datumBlocken_friseur.jsp");
		}else {
			request.setAttribute("error_msg", "Versuche es nochmal!");
			request.getRequestDispatcher("datumBlocken_friseur.jsp").forward(request, response);
		}
		
		
	}

}
