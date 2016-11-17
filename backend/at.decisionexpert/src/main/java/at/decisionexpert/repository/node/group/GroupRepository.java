package at.decisionexpert.repository.node.group;

import at.decisionexpert.neo4jentity.node.Group;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
public interface GroupRepository extends GraphRepository<Group> {
}
