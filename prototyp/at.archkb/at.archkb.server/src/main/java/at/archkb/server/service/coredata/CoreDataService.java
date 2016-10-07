package at.archkb.server.service.coredata;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.access.prepost.PreAuthorize;

import at.archkb.server.neo4jentity.dto.APAttributeAdminDto;
import at.archkb.server.neo4jentity.dto.ApAttributeDetailAdminDto;
import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.CoreData;

public interface CoreDataService {

	/**
	 * Fetching the List of fitting CoreData Values
	 * 
	 * @param titlePartial
	 *            filtered by a partial title
	 * @param coreDataClass
	 *            which core data class (e.g. Attribute). Needed for querying
	 *            the right node
	 * @return A list of Core Data Values filtered by the partial title
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	<T extends CoreData> List<ArchProfileRelationDto> getCoreData(@Size(min = 3) String titlePartial,
																  @NotNull Class<T> coreDataClass);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Iterable<APAttributeAdminDto> getAllOfType(String type);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ApAttributeDetailAdminDto getById(Long id);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ApAttributeDetailAdminDto addToCore(Long id);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ApAttributeDetailAdminDto removeFromCore(Long id);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ApAttributeDetailAdminDto updateAPAttribute(ApAttributeDetailAdminDto apat);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ApAttributeDetailAdminDto createAPAttribute(ApAttributeDetailAdminDto apat) throws ClassNotFoundException, InstantiationException, IllegalAccessException;


	/**
	 * Creating a new ArchProfile Attribute based on Relation Values
	 * @param attributeValues The Values for the Attribute (Just used Relation DTO for this, because it already has all the necessary properties).
	 * @param attributeType Attribute Type (e.g. TradeoffItem, QualityAttribute, ...)
	 * @param <T> Some Class that extends CoreData
     * @return The created Attribute
     */
	@PreAuthorize("hasRole('ROLE_USER')")
	<T extends CoreData> T createArchProfileAttribute(ArchProfileRelationDto attributeValues, Class<T> attributeType);
}
