package mkozina.mstore2.service;

import mkozina.mstore2.domain.Genre;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class GenreStorage{

	private List<Genre> genres = new ArrayList<Genre>();
	private Long counter = (long) 0;

	public void add(Genre genre){
		genre.setId(counter);
		genres.add(genre);
		counter++;
	}

	public Genre get(Long id){
		for(Genre genre : genres){
			if(genre.getId().equals(id)){
				return genre;
			}
		}
		return null;
	}

	public void delete(Long id){
		for(Genre genre : genres){
			if(genre.getId().equals(id)){
				genres.remove(genre);
				return;
			}
		}
	}

	public void update(Genre genre){
		genres.set(genres.indexOf(genre), genre);
	}

	public List<Genre> getAllGenres(){
		return genres;
	}

}
