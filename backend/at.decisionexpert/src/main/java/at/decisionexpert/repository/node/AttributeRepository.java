package at.decisionexpert.repository.node;

import at.decisionexpert.neo4jentity.AttributeAdminDto;
import at.decisionexpert.neo4jentity.AttributeDetailAdminDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AttributeRepository extends GraphRepository<CoreData> {

	@Query("match(n) where {0} IN labels(n) return id(n) as id, n.name as name, n.coreAdded as coreAdded")
	Iterable<AttributeAdminDto> getAllofType(String type);

	@Query("match (n) where id(n)={0} optional match (n)-[:HAS_CREATOR]->(u:User) return id(n) as id,  n.name as name, n.definition as definition, n.coreAdded as coreAdded, n.creationDate as creationDate, n.lastModified as lastModified,  labels(n)[0] as type, id(u) as creatorId, u.email as creatorEmail")
	AttributeDetailAdminDto getById(Long id);
	
	@Query("match (n) where id(n)={0} optional match (n)-[:HAS_CREATOR]->(u:User) set n.coreAdded=timestamp() return id(n) as id,  n.name as name, n.definition as definition, n.coreAdded as coreAdded, n.creationDate as creationDate, n.lastModified as lastModified, labels(n)[0] as type, id(u) as creatorId, u.email as creatorEmail")
	AttributeDetailAdminDto addToCore(Long id);
	
	@Query("match (n) where id(n)={0} optional match (n)-[:HAS_CREATOR]->(u:User) set n.coreAdded=null return id(n) as id,  n.name as name, n.definition as definition, n.coreAdded as coreAdded, n.creationDate as creationDate, n.lastModified as lastModified, labels(n)[0] as type, id(u) as creatorId, u.email as creatorEmail")
	AttributeDetailAdminDto removeFromCore(Long id);

}
