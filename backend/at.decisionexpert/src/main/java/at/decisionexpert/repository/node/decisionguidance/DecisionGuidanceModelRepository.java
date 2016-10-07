package at.decisionexpert.repository.node.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public interface DecisionGuidanceModelRepository extends GraphRepository<DecisionGuidanceModel> {

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true WITH dgm, user ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.title as title, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionGuidanceModelDto> findPublishedByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} WITH dgm, user ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.title as title, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionGuidanceModelDto> findAllByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE dgm.published = true WITH dgm, user ORDER BY dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.title as title, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionGuidanceModelDto> findNewestPublishedDecisionGuidanceModels(Integer skip, Integer size);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WITH dgm, user ORDER BY dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.title as title, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionGuidanceModelDto> findNewestDecisionGuidanceModels(Integer skip, Integer size);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() RETURN count(dgm)")
    Long countDecisionGuidanceModels();

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() WHERE dgm.published = true RETURN count(dgm)")
    Long countPublishedDecisionGuidanceModels();

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true RETURN count(dgm)")
    Long countPublishedDecisionGuidanceModelsOfUser(Long idUser);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} RETURN count(dgm)")
    Long countAllDecisionGuidanceModelsOfUser(Long idUser);
}
