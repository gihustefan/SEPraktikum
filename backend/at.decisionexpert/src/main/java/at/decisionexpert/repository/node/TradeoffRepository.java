package at.decisionexpert.repository.node;

import at.decisionexpert.neo4jentity.node.Tradeoff;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 07.10.2016.
 */
public interface TradeoffRepository extends GraphRepository<Tradeoff> {

    /**
     * Problem occured here, because in the testdata there were multiple tradeoffs with the same over and under -> should not be possible
     * Limit 1 for making sure that only 1 Tradeoff is within the result set.
     * @param idTradeoffItemOver Which Tradeoffitem Over
     * @param idTradeoffItemUnder Which Tradeoffitem Under
     * @return
     */
    @Query("MATCH (t:Tradeoff)-[:UNDER]->(under:TradeoffItem), (t)-[:OVER]->(over:TradeoffItem) where id(under) = {1} and id(over) = {0} WITH t LIMIT 1 RETURN t")
    Tradeoff findTradeoffByTradeoffItems(Long idTradeoffItemOver, Long idTradeoffItemUnder);

}
