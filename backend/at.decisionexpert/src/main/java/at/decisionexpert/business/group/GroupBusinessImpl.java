package at.decisionexpert.business.group;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.dto.group.GroupChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;
import at.decisionexpert.neo4jentity.node.Group;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.neo4jentity.relationship.HasMember;
import at.decisionexpert.repository.node.group.GroupRepository;
import at.decisionexpert.repository.node.user.UserRepository;
import at.decisionexpert.repository.relationship.group.HasMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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

        Group group = new Group(user);
        if (group != null) {
            group.setName(groupValues.getName());
            group.setDescription(groupValues.getDescription());
        }

        return new GroupDto(groupRepository.save(group));
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

        if (hasMember == null)
            return;

        hasMemberRepository.delete(hasMember);
    }
}
