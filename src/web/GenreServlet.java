package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.GenreDaoImpl;
import dao.IGenreDao;
import metier.entities.Genre;

@WebServlet(name="gServ", urlPatterns={"/genres", "/saisieGenre", "/saveGenre", "/supprimerG", "/editerG", "/updateG"})
public class GenreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IGenreDao metier;

    @Override
    public void init() throws ServletException {
        metier = new GenreDaoImpl();
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String path=request.getServletPath();
		System.out.println("PATH "+path);
		if (path.equals("/genres"))
		{
			GenreModel model= new GenreModel();
			List<Genre> g = metier.getAllGenres();
			model.setGenres(g);
			request.setAttribute("model", model);
			request.getRequestDispatcher("genres.jsp").forward(request,response);
		}
		else if (path.equals("/saisieGenre") )
		{
			request.getRequestDispatcher("saisieGenre.jsp").forward(request,response);
		}
		else if (path.equals("/saveGenre") &&

			request.getMethod().equals("POST"))

			{
				String nom=request.getParameter("nom");
				Genre g = metier.save(new Genre(nom));
				request.setAttribute("genre", g);
				response.sendRedirect("genres");
				}
				else if (path.equals("/supprimerG"))
				{
					long id= Long.parseLong(request.getParameter("id"));
					metier.deleteGenre(id);
					response.sendRedirect("genres");
				}
				else if (path.equals("/editerG") )
				{
					long id= Long.parseLong(request.getParameter("id"));
					Genre cat = metier.getGenre(id);
					request.setAttribute("genre", cat);
					request.getRequestDispatcher("editerGenre.jsp").forward(request,response
							);
				}
				else if (path.equals("/updateG") )
				{
					int id = (int) Long.parseLong(request.getParameter("id"));
					String nom=request.getParameter("nom");
					Genre g = new Genre();
					g.setIdG(id);
					g.setNomG(nom);
					metier.updateGenre(g);
					response.sendRedirect("genres");
				}
				else
				{
					response.sendError(Response.SC_NOT_FOUND);
				}
			}
	
    		@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request,response);
			}
}
