package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Serie;

public class SerieModele {
	
	private String motCle;
	List<Serie> series = new ArrayList<>();

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<Serie> getSeries() {
		return series;
	}	

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

}