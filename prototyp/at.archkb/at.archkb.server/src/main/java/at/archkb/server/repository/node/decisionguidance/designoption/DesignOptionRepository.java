package at.archkb.server.repository.node.decisionguidance.designoption;

import at.archkb.server.neo4jentity.node.DesignOption;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DesignOptionRepository extends GraphRepository<DesignOption> {
}
