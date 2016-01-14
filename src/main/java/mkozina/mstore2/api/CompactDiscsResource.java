package mkozina.mstore2.api;

import mkozina.mstore2.domain.CompactDisc;
import mkozina.mstore2.service.CompactDiscStorage;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.print.attribute.standard.Media;

import java.util.List;

@Path("cds")
@Produces(MediaType.APPLICATION_JSON)
public class CompactDiscsResource{

	@EJB
	CompactDiscStorage compactDiscStorage;

	@GET
	public List<CompactDisc> getAllCompactDiscs(){
		return compactDiscStorage.getAllCompactDiscs();
	}

	@GET
	@Path("{id}")
	public CompactDisc get(@PathParam("id") Long id){
		return compactDiscStorage.get(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addCompactDisc(
		@FormParam("artist") String artist,
		@FormParam("album") String album,
		@FormParam("year") int year,
		@FormParam("tracks") int tracks){
		CompactDisc newCompactDisc = new CompactDisc();
		newCompactDisc.setArtist(artist);
		newCompactDisc.setAlbum(album);
		newCompactDisc.setYear(year);
		newCompactDisc.setTracks(tracks);
		compactDiscStorage.add(newCompactDisc);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id){
		compactDiscStorage.delete(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{id}")
	public void editCompactDisc(
		@PathParam("id") Long id,
		@FormParam("artist") String artist,
		@FormParam("album") String album,
		@FormParam("year") int year,
		@FormParam("tracks") int tracks){
		CompactDisc compactDisc = compactDiscStorage.get(id);
		if(compactDisc != null){
			compactDisc.setArtist(artist);
			compactDisc.setAlbum(album);
			compactDisc.setYear(year);
			compactDisc.setTracks(tracks);
			compactDiscStorage.update(compactDisc);
		}
	}

}
