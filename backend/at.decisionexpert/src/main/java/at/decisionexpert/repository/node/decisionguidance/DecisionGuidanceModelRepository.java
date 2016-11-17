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

    //Models of the User, ordered by creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findNewestPublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findNewestAllByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by title and creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.name ASC,dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findAlphabetPublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by title and creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findAlphabetAllByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by + ratings and creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findRatingPublishedByUserId(Long idUser, Integer skip, Integer size);

    //Models of the User, ordered by + ratings and  creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {1} LIMIT {2} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findRatingAllByUserId(Long idUser, Integer skip, Integer size);


    //Order by creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) where dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findNewestPublishedDecisionGuidanceModels(Integer skip, Integer size);

    //Order by creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findNewestDecisionGuidanceModels(Integer skip, Integer size);

    //Order by title and creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) where dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.name ASC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findAlphabetPublishedDecisionGuidanceModels(Integer skip, Integer size);

    //Order by title and creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY dgm.name ASC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findAlphabetDecisionGuidanceModels(Integer skip, Integer size);

    //Order by + rating creation date, only published
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) where dgm.published = true optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY voteTrue DESC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findRatingPublishedDecisionGuidanceModels(Integer skip, Integer size);

    //Order by + rating and creation date
    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) optional Match (dgm)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (dgm)-[w:HAS_VOTE]->(:User) where w.vote=false WITH dgm, user, count(v) as voteTrue, count(w) as voteFalse ORDER BY voteTrue DESC, dgm.creationDate DESC SKIP {0} LIMIT {1} RETURN id(dgm) as id, dgm.published as published, dgm.name as name, dgm.description as description, dgm.creationDate as created, dgm.lastModified as modified, user.originalUsername as ownerName, voteTrue, voteFalse")
    List<DecisionGuidanceModelDto> findRatingDecisionGuidanceModels(Integer skip, Integer size);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() RETURN count(dgm)")
    Long countDecisionGuidanceModels();

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->() WHERE dgm.published = true RETURN count(dgm)")
    Long countPublishedDecisionGuidanceModels();

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND dgm.published = true RETURN count(dgm)")
    Long countPublishedDecisionGuidanceModelsOfUser(Long idUser);

    @Query("MATCH (dgm:DecisionGuidanceModel)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} RETURN count(dgm)")
    Long countAllDecisionGuidanceModelsOfUser(Long idUser);
}
