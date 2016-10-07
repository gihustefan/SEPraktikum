package at.decisionexpert.repository.node.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelDto;
import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationRepository  extends GraphRepository<DecisionDocumentationModel> {

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND ddm.published = true WITH ddm, user ORDER BY ddm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(ddm) as id, ddm.title as title, ddm.description as description, ddm.creationDate as created, ddm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionDocumentationModelDto> findPublishedByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} WITH ddm, user ORDER BY ddm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(ddm) as id, ddm.published as published, ddm.title as title, ddm.description as description, ddm.creationDate as created, ddm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionDocumentationModelDto> findAllByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->(user:User) WHERE ddm.published = true WITH ddm, user ORDER BY ddm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(ddm) as id, ddm.published as published, ddm.title as title, ddm.description as description, ddm.creationDate as created, ddm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionDocumentationModelDto> findNewestPublishedDecisionDocumentationModels(Integer skip, Integer size);

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->(user:User) WITH ddm, user ORDER BY ddm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(ddm) as id, ddm.published as published, ddm.title as title, ddm.description as description, ddm.creationDate as created, ddm.lastModified as modified, user.originalUsername as ownerName")
    List<DecisionDocumentationModelDto> findNewestDecisionDocumentationModels(Integer skip, Integer size);

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->() RETURN count(ddm)")
    Long countDecisionDocumentationModels();

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->() WHERE ddm.published = true RETURN count(ddm)")
    Long countPublishedDecisionDocumentationModels();

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND ddm.published = true RETURN count(ddm)")
    Long countPublishedDecisionDocumentationModelsOfUser(Long idUser);

    @Query("MATCH (ddm:DecisionDocumentationModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} RETURN count(ddm)")
    Long countAllDecisionDocumentationModelsOfUser(Long idUser);
}
