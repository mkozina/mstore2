package mkozina.mstore2.service;

import mkozina.mstore2.domain.CompactDisc;
import mkozina.mstore2.domain.Genre;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CompactDiscManager{

	@PersistenceContext
	EntityManager em;

	public void add(CompactDisc compactDisc){
		compactDisc.setId(null);
		em.persist(compactDisc);
	}

	public CompactDisc get(Long id){
		return em.find(CompactDisc.class, id);
	}

	public void delete(CompactDisc compactDisc){
		compactDisc = em.find(CompactDisc.class, compactDisc.getId());
		em.remove(compactDisc);
	}

	public void update(CompactDisc compactDisc, String artist, String album, int year, int tracks, Genre genre){
		compactDisc = em.find(CompactDisc.class, compactDisc.getId());
		compactDisc.setArtist(artist);
		compactDisc.setAlbum(album);
		compactDisc.setYear(year);
		compactDisc.setTracks(tracks);
		compactDisc.setGenre(genre);
		em.merge(compactDisc);
	}

	public List<CompactDisc> getAllCompactDiscs(){
		return em.createNamedQuery("compactDisc.all").getResultList();
	}

}
