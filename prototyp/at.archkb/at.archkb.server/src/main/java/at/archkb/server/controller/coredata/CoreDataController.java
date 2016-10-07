package at.archkb.server.controller.coredata;

import java.util.List;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;

public interface CoreDataController {

	/**
	 * Getting a list of core data Values based on a partial title
	 * 
	 * TODO: Maybe evaluate if ArchProfileRealationDto is the right Type (we
	 * would only need title and definition)
	 * 
	 * @param titlePartial
	 *            the partial title of the core data attributes
	 * @return a List of core data values filtered by the partial title
	 */
	List<ArchProfileRelationDto> getCoreData(String titlePartial);
}
