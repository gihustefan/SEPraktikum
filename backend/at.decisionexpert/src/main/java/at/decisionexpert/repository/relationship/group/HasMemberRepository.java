package at.decisionexpert.repository.relationship.group;

import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;
import at.decisionexpert.neo4jentity.relationship.HasMember;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
public interface HasMemberRepository extends GraphRepository<HasMember> {

    @Query("MATCH (g:Group)-[hm:HAS_MEMBER]->(user:User) WHERE id(g) = {0} WITH user, hm RETURN id(hm) as id, id(user) as idAttribute")
    List<GroupRelationDto> findMembersOfGroup(Long groupId);
}
