package at.decisionexpert.business.group;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.controller.user.UserController;
import at.decisionexpert.exception.GroupNotFoundException;
import at.decisionexpert.exception.GroupNotPermittedException;
import at.decisionexpert.neo4jentity.dto.group.GroupChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.dto.group.GroupPageableDto;
import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;
import at.decisionexpert.neo4jentity.node.Group;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.neo4jentity.node.UserAuthority;
import at.decisionexpert.neo4jentity.relationship.HasMember;
import at.decisionexpert.repository.node.group.GroupRepository;
import at.decisionexpert.repository.node.user.UserRepository;
import at.decisionexpert.repository.relationship.group.HasMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@Component
public class GroupBusinessImpl implements GroupBusiness {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HasMemberRepository hasMemberRepository;

    @Override
    public GroupDto createGroup(GroupChangeRequestDto groupValues) {
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        Group group = new Group(user, groupValues.getName(), groupValues.getDescription());

        return new GroupDto(groupRepository.save(group));
    }

    @Override
    public GroupDto updateGroup(Long idGroup, GroupChangeRequestDto groupValues) {
        Assert.notNull(idGroup);
        Group group = groupRepository.findOne(idGroup);
        Assert.notNull(group);

        if (groupValues.getName() != null) {
            group.setName(groupValues.getName());
        }

        if (groupValues.getDescription() != null) {
            group.setDescription(groupValues.getDescription());
        }

        return new GroupDto(groupRepository.save(group));
    }

    @Override
    public void deleteGroup(Long idGroup) {
        Assert.notNull(idGroup);

        Group group = groupRepository.findOne(idGroup);

        if (group == null)
            return;

        groupRepository.delete(group);
    }

    @Override
    public GroupDto getGroup(Long idGroup) {
        if (idGroup < 0) {
            throw new GroupNotFoundException();
        }

        Group group = groupRepository.findOne(idGroup, 1);

        // When not found -> just return an empty DecisionGuidanceModel POJO
        if (group == null) {
            throw new GroupNotFoundException();
        }

        // Fetching the authenticated user
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the user which requested the DecisionGuidanceModel can actually see it
        boolean isCreator;
        boolean isAdmin;
        if(authenticatedUser == null) {
            isCreator = false;
            isAdmin = false;
        } else {
            isCreator = group.getCreator() != null  && authenticatedUser.getId() == group.getCreator().getId();
            isAdmin = authenticatedUser.getAuthorities() != null && authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));
        }

        // Either the DecisionGuidanceModel isPublished, The user is the creator or the user is an admin
        boolean canSee = isCreator || isAdmin;

        if (!canSee)
            throw new GroupNotPermittedException();

        group.getMembers().forEach(hasMember -> hasMember.setEndNode(userRepository.findOne(hasMember.getEndNode().getId(), 1)));

        return new GroupDto(group);
    }

    @Override
    public GroupRelationDto createGroupRelation(Long idGroup, Long idUser) {
        Assert.notNull(idGroup);
        Assert.notNull(idUser);

        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        Group startGroup = groupRepository.findOne(idGroup, 1);
        User endUser = userRepository.findOne(idUser, 1);

        HasMember hasMember = new HasMember(startGroup, endUser);
        hasMember = hasMemberRepository.save(hasMember);

        return new GroupRelationDto(hasMember);
    }

    @Override
    public void deleteGroupRelationAttribute(Long idGroupRelation) {
        Assert.notNull(idGroupRelation);

        HasMember hasMember = hasMemberRepository.findOne(idGroupRelation);
        Assert.notNull(hasMember);

        hasMemberRepository.delete(hasMember);
    }

    @Override
    public GroupPageableDto getGroupsOfUser(Long idUser, Integer page, Integer size, UserController.GroupType type) {
        Assert.notNull(idUser);
        Assert.notNull(page);
        Assert.notNull(size);
        Assert.notNull(type);

        List<GroupDto> groups = null;
        Long countGroups = null;
        if (type == UserController.GroupType.MEMBER) {
            groups = groupRepository.findAllMemberByUserId(idUser, page * size, size);
            countGroups = groupRepository.countAllMemberGroupsOfUser(idUser);
        } else if (type == UserController.GroupType.CREATOR) {
            groups = groupRepository.findAllCreatedByUserId(idUser, page * size, size);
            countGroups = groupRepository.countAllCreatedGroupsOfUser(idUser);
        }

        if (groups == null || groups.size() == 0) {
            return new GroupPageableDto();
        }

        groups.forEach(groupDto -> groupDto.setMembers(hasMemberRepository.findMembersOfGroup(groupDto.getId())));
        return new GroupPageableDto(groups, countGroups);
    }
}
