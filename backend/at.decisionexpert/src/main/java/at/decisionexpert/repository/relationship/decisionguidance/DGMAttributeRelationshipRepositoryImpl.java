package at.decisionexpert.repository.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
@Repository
public class DGMAttributeRelationshipRepositoryImpl implements DGMAttributeRelationshipRepository {

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz, Long idDecisionGuidanceModelRelation) {
        return neo4jOperations.load(clazz, idDecisionGuidanceModelRelation);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz, Long idDecisionGuidanceModel, Integer ordering) {
        String query = "MATCH (start:DecisionGuidanceModel)-[rel:" + getRelationType(clazz)
                + "]->() WHERE id(start) = {idDecisionGuidanceModel} and rel.ordering = {ordering} RETURN rel";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idDecisionGuidanceModel", idDecisionGuidanceModel);
        parameters.put("ordering", ordering);

        return neo4jOperations.queryForObject(clazz, query, parameters);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> void delete(T relation) {
        neo4jOperations.delete(relation);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(Long idDecisionGuidanceModel, Class<T> relationClass) {
        String query = "MATCH (start:DecisionGuidanceModel)-[rel:" + getRelationType(relationClass)
                + "]->() WHERE id(start) = {idDecisionGuidanceModel} RETURN rel";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idDecisionGuidanceModel", idDecisionGuidanceModel);

        return neo4jOperations.queryForObjects(relationClass, query, parameters);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> T save(T relation) {
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
            Class<? extends DGMAttributeRelationship<? extends CoreData>> relationClass) {
        RelationshipEntity relEntity = relationClass.getAnnotation(RelationshipEntity.class);
        Assert.notNull(relEntity);
        return relEntity.type();
    }
}
