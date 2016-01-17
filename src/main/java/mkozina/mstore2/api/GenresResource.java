package mkozina.mstore2.api;

import mkozina.mstore2.domain.Genre;
import mkozina.mstore2.service.GenreStorage;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.print.attribute.standard.Media;

import java.util.List;

@Path("genres")
@Produces(MediaType.APPLICATION_JSON)
public class GenresResource{

	@EJB
	GenreStorage genreStorage;

	@GET
	public List<Genre> getAllGenres(){
		return genreStorage.getAllGenres();
	}

	@GET
	@Path("{id}")
	public Genre get(@PathParam("id") Long id){
		return genreStorage.get(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addGenre(
		@FormParam("name") String name,
		@FormParam("description") String description){
		Genre newGenre = new Genre();
		newGenre.setName(name);
		newGenre.setDescription(description);
		genreStorage.add(newGenre);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id){
		genreStorage.delete(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{id}")
	public void editGenre(
		@PathParam("id") Long id,
		@FormParam("name") String name,
		@FormParam("description") String description){
		Genre genre = genreStorage.get(id);
		if(genre != null){
			genre.setName(name);
			genre.setDescription(description);
			genreStorage.update(genre);
		}
	}

}
