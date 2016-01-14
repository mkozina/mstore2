package mkozina.mstore2.domain;

public class CompactDisc{

	private Long id;
	private String artist = "unknown";
	private String album = "untitled";
	private int year = 1900;
	private int tracks = 99;

	public CompactDisc(){
	}

	public CompactDisc(String artist, String album, int year, int tracks){
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.tracks = tracks;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getArtist(){
		return artist;
	}

	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public String getAlbum(){
		return album;
	}

	public void setAlbum(String album){
		this.album = album;
	}

	public int getYear(){
		return year;
	}

	public void setYear(int year){
		this.year = year;
	}
	
	public int getTracks(){
		return tracks;
	}

	public void setTracks(int tracks){
		this.tracks = tracks;
	}

}
