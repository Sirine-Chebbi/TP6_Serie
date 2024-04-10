package dao;

import java.util.List;

import metier.entities.Serie;

public interface ISerieDao {
	
		public Serie save(Serie s);
		public List<Serie> seriesParMC(String motCle);
		public Serie getSerie(Long id);
		public Serie updateSerie(Serie p);
		public void deleteSerie(long id);
		
}


