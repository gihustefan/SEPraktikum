package at.archkb.server.neo4jentity.dto;

import java.util.ArrayList;
import java.util.List;

import at.archkb.server.neo4jentity.node.ArchProfile;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class ArchProfilePageableDto {
	
	private List<ArchProfileDto> archProfiles;
	
	private Long countTotal;
	
	public ArchProfilePageableDto(List<ArchProfile> archProfiles, Long countTotal) {
		super();

		this.archProfiles = new ArrayList<>();

		archProfiles.forEach(ap -> {
			this.archProfiles.add(new ArchProfileDto(ap));
		});
		
		this.countTotal = countTotal;
	}

    public ArchProfilePageableDto(Long countTotal, List<ArchProfileDto> archProfiles) {
        super();
        this.archProfiles = archProfiles;
        this.countTotal = countTotal;
    }

	public ArchProfilePageableDto() {
		super();
	}

	public List<ArchProfileDto> getArchProfiles() {
		return archProfiles;
	}

	public void setArchProfiles(List<ArchProfileDto> archProfiles) {
		this.archProfiles = archProfiles;
	}

	public Long getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(Long countTotal) {
		this.countTotal = countTotal;
	}
}
