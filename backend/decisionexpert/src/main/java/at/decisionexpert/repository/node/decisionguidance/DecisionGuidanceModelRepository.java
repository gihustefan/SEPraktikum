package at.decisionexpert.repository.node.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public interface DecisionGuidanceModelRepository extends GraphRepository<DecisionGuidanceModel> {

    //Models of the User, ordered by creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findNewestPublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by creation date, only unpublished
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = false optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findNewestUnpublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findNewestAllByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by title and creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.name ASC,dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findAlphabetPublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by title and creation date, only unpublished
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = false optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.name ASC,dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findAlphabetUnpublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by title and creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findAlphabetAllByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by + ratings and creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findRatingPublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by + ratings and creation date, only unpublished
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = false optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findRatingUnpublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by + ratings and  creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findRatingAllByUserId(Long idUser, Integer skip, Integer size);

    //Order by creation date, only published
    @Query("MATCH (user:User)<-[:HAS_CREATOR]-(dgm:DecisionGuidanceModel) where dgm.published = true AND dgm.name =~ {2} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findNewestPublishedDecisionGuidanceModels(Integer skip, Integer size, String searchText);

    //Order by creation date
    @Query("MATCH (user:User)<-[:HAS_CREATOR]-(dgm:DecisionGuidanceModel) where dgm.name =~ {2} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findNewestDecisionGuidanceModels(Integer skip, Integer size, String searchText);

    //Order by title and creation date, only published
    @Query("MATCH (user:User)<-[:HAS_CREATOR]-(dgm:DecisionGuidanceModel) where dgm.published = true AND dgm.name =~ {2} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.name ASC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findAlphabetPublishedDecisionGuidanceModels(Integer skip, Integer size, String searchText);

    //Order by title and creation date
    @Query("MATCH (user:User)<-[:HAS_CREATOR]-(dgm:DecisionGuidanceModel) where dgm.name =~ {2} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY dgm.name ASC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findAlphabetDecisionGuidanceModels(Integer skip, Integer size, String searchText);

    //Order by + rating creation date, only published
    @Query("MATCH (user:User)<-[:HAS_CREATOR]-(dgm:DecisionGuidanceModel) where dgm.published = true AND dgm.name =~ {2} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY votePositive DESC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findRatingPublishedDecisionGuidanceModels(Integer skip, Integer size, String searchText);

    //Order by + rating and creation date
    @Query("MATCH (user:User)<-[:HAS_CREATOR]-(dgm:DecisionGuidanceModel) where dgm.name =~ {2} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as votePositive, count(w) as voteNegative ORDER BY votePositive DESC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, votePositive, voteNegative")
    List<DecisionGuidanceModelDto> findRatingDecisionGuidanceModels(Integer skip, Integer size, String searchText);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() where dgm.name =~ {0} RETURN count(dgm)")
    Long countDecisionGuidanceModels(String searchText);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() WHERE dgm.published = true AND dgm.name =~ {0} RETURN count(dgm)")
    Long countPublishedDecisionGuidanceModels(String searchText);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() WHERE dgm.published = false RETURN count(dgm)")
    Long countUnpublishedDecisionGuidanceModels();

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true RETURN count(dgm)")
    Long countPublishedDecisionGuidanceModelsOfUser(Long idUser);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = false RETURN count(dgm)")
    Long countUnpublishedDecisionGuidanceModelsOfUser(Long idUser);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} RETURN count(dgm)")
    Long countAllDecisionGuidanceModelsOfUser(Long idUser);

    @Query("MATCH (dgm:DecisionGuidanceModel) optional Match (dgm)-[v:HAS_POTENTIALREQUIREMENT]->(req:Requirement) where id(req) = {0} WITH count(v) as referencesOnAttribute RETURN referencesOnAttribute")
    int getReferencesOnAttribute(Long idAttribute);
}
