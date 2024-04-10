package dao;
	
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Genre;
import util.JpaUtil;

public class GenreDaoImpl implements IGenreDao{

	
	private EntityManager entityManager=JpaUtil.getEntityManager("TP5_JEE_S");
	
	@Override
	public Genre save(Genre g ) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(g);
		tx.commit();
		return g;
	}
	

	@Override
	public Genre getGenre(long id) {
	    return entityManager.find(Genre.class, id);
	}

	
	@Override
	public Genre updateGenre(Genre g) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(g);
		tx.commit();
		return g;
	}
	
	@Override
	public void deleteGenre(int id) {
		Genre genre = entityManager.find(Genre.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(genre);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public List<Genre> getAllGenres() {
		List<Genre> genres = entityManager.createQuery("select g from Genre g").getResultList();
		return genres;
	}
}
