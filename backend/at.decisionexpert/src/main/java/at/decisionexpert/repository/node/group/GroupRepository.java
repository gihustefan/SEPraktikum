package at.decisionexpert.repository.node.group;

import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.node.Group;
import at.decisionexpert.neo4jentity.node.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
public interface GroupRepository extends GraphRepository<Group> {

    @Query("MATCH (g:Group)-[:HAS_MEMBER]->(user:User) WHERE id(user) = {0} WITH g, user ORDER BY g.name ASC SKIP {1} LIMIT {2} RETURN id(g) as id, g.name as name, g.description as description")
    List<GroupDto> findAllMemberByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (g:Group)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} WITH g, user ORDER BY g.name ASC SKIP {1} LIMIT {2} RETURN id(g) as id, g.name as name, g.description as description")
    List<GroupDto> findAllCreatedByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (g:Group)-[:HAS_MEMBER]->(user:User) WHERE id(user) = {0}  RETURN count(g)")
    Long countAllMemberGroupsOfUser(Long idUser);

    @Query("MATCH (g:Group)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0}  RETURN count(g)")
    Long countAllCreatedGroupsOfUser(Long idUser);

}