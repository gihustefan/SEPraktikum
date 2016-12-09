package at.decisionexpert.repository.relationship.vote;

import at.decisionexpert.neo4jentity.relationship.HasVote;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
public interface HasVoteRepository extends GraphRepository<HasVote> {
}
