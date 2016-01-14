package mkozina.mstore2.service;

import mkozina.mstore2.domain.CompactDisc;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CompactDiscStorage{

	private List<CompactDisc> cds = new ArrayList<CompactDisc>();
	private Long counter = (long) 0;

	public void add(CompactDisc compactDisc){
		compactDisc.setId(counter);
		cds.add(compactDisc);
		counter++;
	}

	public CompactDisc get(Long id){
		for(CompactDisc compactDisc : cds){
			if(compactDisc.getId().equals(id)){
				return compactDisc;
			}
		}
		return null;
	}

	public void delete(Long id){
		for(CompactDisc compactDisc : cds){
			if(compactDisc.getId().equals(id)){
				cds.remove(compactDisc);
				return;
			}
		}
	}

	public void update(CompactDisc compactDisc){
		cds.set(cds.indexOf(compactDisc), compactDisc);
	}

	public List<CompactDisc> getAllCompactDiscs(){
		return cds;
	}

}
