package at.decisionexpert.service.group;

import at.decisionexpert.controller.user.UserController;
import at.decisionexpert.neo4jentity.dto.group.GroupChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.dto.group.GroupPageableDto;
import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;
import javax.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
public interface GroupService {

    @PreAuthorize("hasRole('ROLE_USER')")
    GroupDto createGroup(GroupChangeRequestDto groupValues);

    @PreAuthorize("hasRole('ROLE_USER')")
    GroupDto updateGroup(@NotNull Long idGroup, @NotNull GroupChangeRequestDto groupValues);

    @PreAuthorize("hasRole('ROLE_USER')")
    void deleteGroup(@NotNull Long idGroup);

    @PreAuthorize("hasRole('ROLE_USER')")
    GroupDto getGroup(@NotNull Long idGroup);

    @PreAuthorize("hasRole('ROLE_USER')")
    GroupRelationDto createGroupRelation(@NotNull Long idGroup, @NotNull Long idUser);

    @PreAuthorize("hasRole('ROLE_USER')")
    void deleteGroupRelationAttribute(@NotNull Long idGroupRelation);

    @PreAuthorize("hasRole('ROLE_USER')")
    GroupPageableDto getGroupsOfUser(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size, @NotNull UserController.GroupType type);
}
