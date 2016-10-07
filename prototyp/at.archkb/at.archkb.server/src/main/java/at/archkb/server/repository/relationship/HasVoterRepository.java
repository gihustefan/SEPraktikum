package at.archkb.server.repository.relationship;

import org.springframework.data.neo4j.repository.GraphRepository;

import at.archkb.server.neo4jentity.relationship.HasVoter;

public interface HasVoterRepository extends GraphRepository<HasVoter> {

}
