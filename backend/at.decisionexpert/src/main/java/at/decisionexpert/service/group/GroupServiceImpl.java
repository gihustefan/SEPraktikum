package at.decisionexpert.service.group;

import at.decisionexpert.business.group.GroupBusiness;
import at.decisionexpert.controller.user.UserController;
import at.decisionexpert.neo4jentity.dto.group.GroupChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.dto.group.GroupPageableDto;
import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupBusiness groupBusiness;

    @Override
    public GroupDto createGroup(GroupChangeRequestDto groupValues) {
        return groupBusiness.createGroup(groupValues);
    }

    @Override
    public GroupDto updateGroup(@NotNull Long idGroup, @NotNull GroupChangeRequestDto groupValues) {
        return groupBusiness.updateGroup(idGroup, groupValues);
    }

    @Override
    public void deleteGroup(@NotNull Long idGroup) {
        groupBusiness.deleteGroup(idGroup);
    }

    @Override
    public GroupDto getGroup(@NotNull Long idGroup) {
        return groupBusiness.getGroup(idGroup);
    }

    @Override
    public GroupRelationDto createGroupRelation(@NotNull Long idGroup, @NotNull Long idUser) {
        return groupBusiness.createGroupRelation(idGroup, idUser);
    }

    @Override
    public void deleteGroupRelationAttribute(@NotNull Long idGroupRelation) {
        groupBusiness.deleteGroupRelationAttribute(idGroupRelation);
    }

    @Override
    public GroupPageableDto getGroupsOfUser(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size, @NotNull UserController.GroupType type) {
        return groupBusiness.getGroupsOfUser(idUser, page, size, type);
    }
}
