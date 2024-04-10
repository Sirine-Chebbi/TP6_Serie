package metier.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Genre implements Serializable{
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long idG;
	private String nomG;
	
	@OneToMany (mappedBy="genre")
	private List<Serie> series;
	public Genre() {
	super();
	}
	public Genre(String nomG) {
	super();
	this.nomG = nomG;
	}
	public long getIdG() {
	return idG;
	}
	public void setIdG(long idG) {
	this.idG = idG;
	}
	public String getNomG() {
	return nomG;
	}
	public void setNomG(String nomG) {
	this.nomG = nomG;
	}
	
	public List<Serie> getSeries() {
	return series;
	}
	public void setSeries(List<Serie> series) {
	this.series = series;
	}


}
