package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.GenreDaoImpl;
import dao.IGenreDao;
import dao.ISerieDao;
import dao.SerieDaoImpl;
import metier.entities.Genre;
import metier.entities.Serie;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
	
	ISerieDao metier;
	IGenreDao metierG;
	
	@Override
	public void init() throws ServletException {
		metier = new SerieDaoImpl();
		metierG = new GenreDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if (path.equals("/index.do"))
		{
			request.getRequestDispatcher("series.jsp").forward(request,response);
		}
		else if (path.equals("/chercher.do"))
		{
			String motCle=request.getParameter("motCle");
			SerieModele model= new SerieModele();
			model.setMotCle(motCle);
			List<Serie> series = metier.seriesParMC(motCle);
			model.setSeries(series);
			request.setAttribute("model", model);
			request.getRequestDispatcher("series.jsp").forward(request,response);
		}
		else if (path.equals("/saisie.do") )
		{
			List<Genre> g = metierG.getAllGenres();
			GenreModel model= new GenreModel();
			model.setGenres(g);
			request.setAttribute("GModel", model);

			request.getRequestDispatcher("saisieSerie.jsp").forward(request,response);
			}
		else if (path.equals("/save.do") && request.getMethod().equals("POST"))
		{
			String nom=request.getParameter("nom");
			long GenreId=Long.parseLong(request.getParameter("genre"));
			double nbS = Double.parseDouble(request.getParameter("nbS"));
			Genre g = metierG.getGenre(GenreId);
			Serie s = metier.save(new Serie(nom,nbS,g));
			request.setAttribute("serie", s);
			response.sendRedirect("chercher.do?motCle="); }
		else if (path.equals("/supprimer.do"))
		{
			Long id= Long.parseLong(request.getParameter("id"));
			metier.deleteSerie(id);
			response.sendRedirect("chercher.do?motCle=");
		}
		else if (path.equals("/editer.do")) {
		    Long id = Long.parseLong(request.getParameter("id"));
		    Serie s = metier.getSerie(id);
		    request.setAttribute("serie", s);
		    
		    List<Genre> g = metierG.getAllGenres();
		    GenreModel model = new GenreModel();
		    model.setGenres(g);
		    request.setAttribute("GModel", model); 
		    request.getRequestDispatcher("editerSerie.jsp").forward(request, response);
		}

		else if (path.equals("/update.do") )
		{
			long id = Long.parseLong(request.getParameter("id"));
			String nom=request.getParameter("nom");
			double nbS = Double.parseDouble(request.getParameter("nbS"));
			Long categorieId=Long.parseLong(request.getParameter("genre"));
			Serie s = new Serie();
			s.setIdS(id);
			s.setNomS(nom);
			s.setNbS(nbS);
			Genre cat = metierG.getGenre(categorieId);
			s.setGenre(cat);
			metier.updateSerie(s);
			response.sendRedirect("chercher.do?motCle=");
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