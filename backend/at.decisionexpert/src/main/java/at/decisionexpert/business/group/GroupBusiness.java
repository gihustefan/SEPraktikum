package at.decisionexpert.business.group;

import at.decisionexpert.controller.user.UserController;
import at.decisionexpert.neo4jentity.dto.group.GroupChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.dto.group.GroupPageableDto;
import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
public interface GroupBusiness {

    GroupDto createGroup(GroupChangeRequestDto groupValues);

    void deleteGroup(Long idGroup);

    GroupDto getGroup(Long idGroup);

    GroupRelationDto createGroupRelation(Long idGroup, Long idUser);

    void deleteGroupRelationAttribute(Long idGroupRelation);

    GroupPageableDto getGroupsOfUser(Long idUser, Integer page, Integer size, UserController.GroupType type);
}
