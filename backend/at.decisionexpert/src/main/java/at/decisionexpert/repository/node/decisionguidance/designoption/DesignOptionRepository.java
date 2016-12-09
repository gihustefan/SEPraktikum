package at.decisionexpert.repository.node.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.decisionexpert.neo4jentity.node.DesignOption;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DesignOptionRepository extends GraphRepository<DesignOption> {

    //@Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (do)-[w:HAS_VOTE]->(:User) where w.vote=false WITH do, count(v) as votePositive, count(w) as voteNegative RETURN id(do) as id, do.name as name, do.description as description, do.lastModified as lastModified, do.creationDate as creationDate, votePositive, voteNegative")
    //@Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=true optional Match (do)-[w:HAS_VOTE]->(:User) where w.vote=false WITH do, count(v) as votePositive, count(w) as voteNegative RETURN do, votePositive, voteNegative")
    //DesignOption findOneDO(Long idDO);

    @Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=true WITH count(v) as votePositive RETURN votePositive")
    int getVotePositiveForDO(Long idDO);

    @Query("MATCH (do:DesignOption) WHERE id(do) = {0} optional Match (do)-[v:HAS_VOTE]->(:User) where v.vote=false WITH count(v) as voteNegative RETURN voteNegative")
    int getVoteNegativeForDO(Long idDO);
}
