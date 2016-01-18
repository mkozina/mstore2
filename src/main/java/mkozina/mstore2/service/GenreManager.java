package mkozina.mstore2.service;

import mkozina.mstore2.domain.Genre;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class GenreManager{

	@PersistenceContext
	EntityManager em;

	public void add(Genre genre){
		genre.setId(null);
		em.persist(genre);
	}

	public Genre get(Long id){
		return em.find(Genre.class, id);
	}

	public void delete(Genre genre){
		genre = em.find(Genre.class, genre.getId());
		em.remove(genre);
	}

	public void update(Genre genre, String name, String description){
		genre = em.find(Genre.class, genre.getId());
		genre.setName(name);
		genre.setDescription(description);
		em.merge(genre);
	}

	public List<Genre> getAllGenres(){
		return em.createNamedQuery("genre.all").getResultList();
	}

}
