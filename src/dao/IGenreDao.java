package dao;

import java.util.List;
import metier.entities.Genre;

public interface IGenreDao {
	
	public Genre save(Genre g);
	public Genre updateGenre(Genre cat);
	public void deleteGenre(int id);
	public List<Genre> getAllGenres();
	public Genre getGenre(long id);

}
