package at.archkb.server.repository.node;

import at.archkb.server.neo4jentity.dto.ArchProfileDto;
import at.archkb.server.neo4jentity.dto.ArchProfilePageableDto;
import at.archkb.server.neo4jentity.node.ArchProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ArchProfileRepository extends GraphRepository<ArchProfile> {

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND ap.published = true WITH ap, user ORDER BY ap.creationDate DESC SKIP {1} LIMIT {2} RETURN id(ap) as id, ap.title as title, ap.description as description, ap.creationDate as created, ap.lastModified as modified, user.originalUsername as ownerName")
    List<ArchProfileDto> findPublishedByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} WITH ap, user ORDER BY ap.creationDate DESC SKIP {1} LIMIT {2} RETURN id(ap) as id, ap.published as published, ap.title as title, ap.description as description, ap.creationDate as created, ap.lastModified as modified, user.originalUsername as ownerName")
    List<ArchProfileDto> findAllByUserId(Long idUser, Integer skip, Integer size);

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->(user:User) WHERE ap.published = true WITH ap, user ORDER BY ap.creationDate DESC SKIP {0} LIMIT {1} RETURN id(ap) as id, ap.published as published, ap.title as title, ap.description as description, ap.creationDate as created, ap.lastModified as modified, user.originalUsername as ownerName")
    List<ArchProfileDto> findNewestPublishedArchProfiles(Integer skip, Integer size);

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->(user:User) WITH ap, user ORDER BY ap.creationDate DESC SKIP {0} LIMIT {1} RETURN id(ap) as id, ap.published as published, ap.title as title, ap.description as description, ap.creationDate as created, ap.lastModified as modified, user.originalUsername as ownerName")
    List<ArchProfileDto> findNewestArchProfiles(Integer skip, Integer size);

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->() WHERE ap.published = true RETURN count(ap)")
    Long countPublishedArchProfiles();

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->() RETURN count(ap)")
    Long countArchProfiles();

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} AND ap.published = true RETURN count(ap)")
    Long countPublishedArchProfilesOfUser(Long idUser);

    @Query("MATCH (ap:ArchProfile)-[:HAS_CREATOR]->(user:User) WHERE id(user) = {0} RETURN count(ap)")
    Long countAllArchProfilesOfUser(Long idUser);
}
