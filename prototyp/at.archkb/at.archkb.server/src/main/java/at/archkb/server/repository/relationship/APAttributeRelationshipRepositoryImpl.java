package at.archkb.server.repository.relationship;

import java.util.HashMap;
import java.util.Map;

import at.archkb.server.neo4jentity.node.CoreData;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;

@Repository
public class APAttributeRelationshipRepositoryImpl implements APAttributeRelationshipRepository {

	@Autowired
	private Neo4jOperations neo4jOperations;

	@Override
	public <T extends APAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
																			  Long idArchProfileRelation) {
		return neo4jOperations.load(clazz, idArchProfileRelation);
	}

	@Override
	public <T extends APAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
																					Long idArchProfile, Integer ordering) {

		String query = "MATCH (start:ArchProfile)-[rel:" + getRelationType(clazz)
				+ "]->() WHERE id(start) = {idArchProfile} and rel.ordering = {ordering} RETURN rel";

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("idArchProfile", idArchProfile);
		parameters.put("ordering", ordering);

		return neo4jOperations.queryForObject(clazz, query, parameters);
	}

	@Override
	public <T extends APAttributeRelationship<? extends CoreData>> void delete(T relation) {
		neo4jOperations.delete(relation);
	}

	@Override
	public <T extends APAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
			Long idArchProfile, Class<T> relationClass) {
		
		String query = "MATCH (start:ArchProfile)-[rel:" + getRelationType(relationClass)
		+ "]->() WHERE id(start) = {idArchProfile} RETURN rel";
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("idArchProfile", idArchProfile);
		
		return neo4jOperations.queryForObjects(relationClass, query, parameters);
	}

	@Override
	public <T extends APAttributeRelationship<? extends CoreData>> T save(T relation) {
		return neo4jOperations.save(relation);
	}

    /**
	 * The Relation will be determined by the Annotation of the given class!
	 * They must be annotated with RelationshipEntity -> type(). Otherwise we
	 * are not able to dynamically set the Relation! Annotation fetched via
	 * Reflection
	 * 
	 * @param relationClass
	 *            The relation Class under investigation
	 * @return the Relation Name based on the relation class
	 */
	private String getRelationType(
			Class<? extends APAttributeRelationship<? extends CoreData>> relationClass) {
		RelationshipEntity relEntity = relationClass.getAnnotation(RelationshipEntity.class);
		Assert.notNull(relEntity);
		return relEntity.type();
	}

}
