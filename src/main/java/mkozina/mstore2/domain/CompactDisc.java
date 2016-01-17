package mkozina.mstore2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name = "compactDisc.all", query = "Select p from CompactDisc p")
})
public class CompactDisc{

	private Long id;
	private String artist = "unknown";
	private String album = "untitled";
	private int year = 1900;
	private int tracks = 99;
	private Genre genre;

	public CompactDisc(){
	}

	public CompactDisc(String artist, String album, int year, int tracks, Genre genre){
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.tracks = tracks;
		this.genre = genre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	@Size(min = 1, max = 50)
	public String getArtist(){
		return artist;
	}
	public void setArtist(String artist){
		this.artist = artist;
	}

	@Size(min = 1, max = 50)
	public String getAlbum(){
		return album;
	}
	public void setAlbum(String album){
		this.album = album;
	}

	@Min(1900)
	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year = year;
	}

	@Min(1)
	@Max(99)
	public int getTracks(){
		return tracks;
	}
	public void setTracks(int tracks){
		this.tracks = tracks;
	}

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	public Genre getGenre(){
		return genre;
	}
	public void setGenre(Genre genre){
		this.genre = genre;
	}

}
