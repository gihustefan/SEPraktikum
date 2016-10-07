package at.archkb.server.repository.relationship;

import at.archkb.server.neo4jentity.relationship.HasTradeoff;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by Rainer on 23.05.2016.
 */
public interface HasTradeoffRepository extends GraphRepository<HasTradeoff> {

    @Query("MATCH (ap:ArchProfile)-[rel:HAS_TRADEOFF]->() WHERE id(ap) = {0} RETURN rel")
    List<HasTradeoff> findAllArchProfileTradeoffs(Long idArchProfile);

    @Query("MATCH (ap:ArchProfile)->[rel:HAS_TRADEOFF]->() WHERE id(ap) = {0} and rel.ordering = {1} RETURN rel")
    HasTradeoff findByOrdering(Long idArchProfile, int ordering);
}
