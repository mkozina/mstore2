package mkozina.mstore2.api;

import mkozina.mstore2.domain.Genre;
import mkozina.mstore2.service.GenreManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.print.attribute.standard.Media;

import java.util.List;

@Stateless
@Path("genres")
@Produces(MediaType.APPLICATION_JSON)
public class GenresResource{

	@EJB
	GenreManager genreManager;

	@GET
	public List<Genre> getAllGenres(){
		return genreManager.getAllGenres();
	}

	@GET
	@Path("{id}")
	public Genre get(@PathParam("id") Long id){
		return genreManager.get(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void add(
		@FormParam("name") String name,
		@FormParam("description") String description){
		Genre newGenre = new Genre();
		newGenre.setName(name);
		newGenre.setDescription(description);
		genreManager.add(newGenre);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id){
		genreManager.delete(genreManager.get(id));
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void edit(
		@PathParam("id") Long id,
		@FormParam("name") String name,
		@FormParam("description") String description){
		Genre genre = genreManager.get(id);
		if(genre != null){
			genreManager.update(genre, name, description);
		}
	}

}
