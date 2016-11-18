package at.decisionexpert.repository.node.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.decisionexpert.neo4jentity.node.DesignOption;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DesignOptionRepository extends GraphRepository<DesignOption> {

    //@Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (do)-[w:HAS_VOTE]->(:User) where w.vote=false WITH do, count(v) as voteTrue, count(w) as voteFalse RETURN id(do) as id, do.name as name, do.description as description, do.lastModified as lastModified, do.creationDate as creationDate, voteTrue, voteFalse")
    //@Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (do)-[w:HAS_VOTE]->(:User) where w.vote=false WITH do, count(v) as voteTrue, count(w) as voteFalse RETURN do, voteTrue, voteFalse")
    //DesignOption findOneDO(Long idDO);

    @Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=true WITH count(v) as voteTrue RETURN voteTrue")
    int getVoteTrueForDO(Long idDO);

    @Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=false WITH count(v) as voteFalse RETURN voteFalse")
    int getVoteFalseForDO(Long idDO);
}
