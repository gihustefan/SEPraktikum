package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;

public interface ArchProfileRelationController {
	
	ArchProfileRelationDto create(Long idArchProfile, ArchProfileRelationDto value);
	
	ArchProfileRelationDto updateAttributes(Long idArchProfile, Long idRelation, ArchProfileRelationDto newValues);
	
	void delete(Long idArchProfile, Long idRelation);
}
