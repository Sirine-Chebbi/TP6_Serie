package dao;

import java.util.List;

import metier.entities.Serie;

public class TestDao {
	
	public static void main(String[] args) {
		
		SerieDaoImpl sdao= new SerieDaoImpl();
		Serie srod= sdao.save(new Serie("My Demon"));
		
		System.out.println(srod);
		
		List<Serie> series =sdao.seriesParMC("B");		
		for (Serie s : series)
			System.out.println(s);
		}


}
