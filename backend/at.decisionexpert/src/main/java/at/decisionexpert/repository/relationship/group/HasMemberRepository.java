package at.decisionexpert.repository.relationship.group;

import at.decisionexpert.neo4jentity.relationship.HasMember;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
public interface HasMemberRepository extends GraphRepository<HasMember> {
}
