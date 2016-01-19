package mkozina.mstore2.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
	@NamedQuery(name = "genre.all", query = "Select p from Genre p")
})
public class Genre{

	private Long id;
	private String name = "";
	private String description = "";

	private List<CompactDisc> cds = new ArrayList<CompactDisc>();

	public Genre(){
	}

	public Genre(String name, String description){
		this.name = name;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	@Size(min = 2, max = 20)
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "genre")
	@JsonIgnore
	public List<CompactDisc> getCompactDiscs() {
		return cds;
	}
	public void setCompactDiscs(List<CompactDisc> cds) {
		this.cds = cds;
	}

}
