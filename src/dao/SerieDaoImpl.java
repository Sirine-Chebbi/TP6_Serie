package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Serie;
import util.JpaUtil;

public class SerieDaoImpl implements ISerieDao {
	private EntityManager entityManager= JpaUtil.getEntityManager("TP5_JEE_S");		
	@Override
	public Serie save(Serie s) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(s);
		tx.commit();
		return s;
	}
	
	@Override
	public List<Serie> seriesParMC(String mc) {
		List<Serie> Series = entityManager.createQuery("select s from Serie s where s.nomS like :mc").setParameter("mc", "%" + mc + "%").getResultList();
		return Series;
	}
	
	@Override
	public Serie getSerie(Long id) {
		return entityManager.find(Serie.class, id);
	}
	
	
	@Override
	public Serie updateSerie(Serie s) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(s);
		tx.commit();
		return s;
	}
	@Override
	public void deleteSerie(long id) {
		Serie serie = entityManager.find(Serie.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(serie);
		entityManager.getTransaction().commit();
	}
	
}
