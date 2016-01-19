package mkozina.mstore2.api;

import mkozina.mstore2.domain.CompactDisc;
import mkozina.mstore2.service.CompactDiscManager;
import mkozina.mstore2.service.GenreManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.print.attribute.standard.Media;

import java.util.List;

@Stateless
@Path("cds")
@Produces(MediaType.APPLICATION_JSON)
public class CompactDiscsResource{

	@EJB
	CompactDiscManager compactDiscManager;

	@EJB
	GenreManager genreManager;

	@GET
	public List<CompactDisc> getAllCompactDiscs(){
		return compactDiscManager.getAllCompactDiscs();
	}

	@GET
	@Path("{id}")
	public CompactDisc get(@PathParam("id") Long id){
		return compactDiscManager.get(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void add(
		@FormParam("artist") String artist,
		@FormParam("album") String album,
		@FormParam("year") int year,
		@FormParam("tracks") int tracks,
		@FormParam("genreId") Long genreId){
		CompactDisc newCompactDisc = new CompactDisc();
		newCompactDisc.setArtist(artist);
		newCompactDisc.setAlbum(album);
		newCompactDisc.setYear(year);
		newCompactDisc.setTracks(tracks);
		newCompactDisc.setGenre(genreManager.get(genreId));
		compactDiscManager.add(newCompactDisc);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id){
		compactDiscManager.delete(compactDiscManager.get(id));
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void edit(
		@PathParam("id") Long id,
		@FormParam("artist") String artist,
		@FormParam("album") String album,
		@FormParam("year") int year,
		@FormParam("tracks") int tracks,
		@FormParam("genreId") Long genreId){
		CompactDisc compactDisc = compactDiscManager.get(id);
		if(compactDisc != null){
			compactDiscManager.update(compactDisc, artist, album, year, tracks, genreManager.get(genreId));
		}
	}

}
