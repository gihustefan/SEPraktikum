package at.decisionexpert.repository.relationship.vote;

import at.decisionexpert.neo4jentity.relationship.HasVote;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
public interface HasVoteRepository extends GraphRepository<HasVote> {

    @Query("MATCH (dgm:DecisionGuidanceModel)-[votes:HAS_VOTE]->(u:User) WHERE id(dgm) = {0} and id(u) = {1} WITH count(votes) as votes RETURN votes")
    int dgmVoteAlreadyExists(Long idDGM, Long idUser);
}
