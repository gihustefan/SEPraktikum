package at.decisionexpert.repository.relationship.component;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@Repository
public class CompAttributeRelationshipRepositoryImpl implements CompAttributeRelationshipRepository {

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz, Long idComponentRelation) {
        return neo4jOperations.load(clazz, idComponentRelation);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz, Long idComponent, Integer ordering) {
        String query = "MATCH (start:Component)-[rel:" + getRelationType(clazz)
                + "]->() WHERE id(start) = {idComponent} and rel.ordering = {ordering} RETURN rel";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idComponent", idComponent);
        parameters.put("ordering", ordering);

        return neo4jOperations.queryForObject(clazz, query, parameters);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> void delete(T relation) {
        neo4jOperations.delete(relation);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(Long idComponent, Class<T> relationClass) {
        String query = "MATCH (start:Component)-[rel:" + getRelationType(relationClass)
                + "]->() WHERE id(start) = {idComponent} RETURN rel";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idComponent", idComponent);

        return neo4jOperations.queryForObjects(relationClass, query, parameters);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> T save(T relation) {
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
            Class<? extends ComponentAttributeRelationship<? extends CoreData>> relationClass) {
        RelationshipEntity relEntity = relationClass.getAnnotation(RelationshipEntity.class);
        Assert.notNull(relEntity);
        return relEntity.type();
    }
}
