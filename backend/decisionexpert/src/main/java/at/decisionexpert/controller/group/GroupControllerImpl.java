package at.decisionexpert.controller.group;

import at.decisionexpert.neo4jentity.dto.group.GroupChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.group.GroupDto;
import at.decisionexpert.neo4jentity.dto.group.GroupRelationDto;
import at.decisionexpert.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@RestController
@RequestMapping("api/group")
@ResponseBody
public class GroupControllerImpl implements GroupController {

    @Autowired
    private GroupService groupService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public GroupDto createGroup(@RequestBody GroupChangeRequestDto groupValues) {
        return groupService.createGroup(groupValues);
    }

    @Override
    @RequestMapping(value = "/{idGroup}",method = RequestMethod.PATCH)
    public GroupDto updateGroup(@PathVariable Long idGroup, @RequestBody GroupChangeRequestDto groupValues) {
        return groupService.updateGroup(idGroup, groupValues);
    }

    @Override
    @RequestMapping(value = "/{idGroup}",method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable Long idGroup) {
        groupService.deleteGroup(idGroup);
    }

    @Override
    @RequestMapping(value = "/{idGroup}", method = RequestMethod.GET)
    public GroupDto getGroup(@PathVariable Long idGroup) {
        return groupService.getGroup(idGroup);
    }

    @Override
    @RequestMapping(value = "/{idGroup}/{idUser}",method = RequestMethod.POST)
    public GroupRelationDto createGroupRelation(@PathVariable Long idGroup, @PathVariable Long idUser) {
        return groupService.createGroupRelation(idGroup, idUser);
    }

    @Override
    @RequestMapping(value = "/user/{idGroupRelation}",method = RequestMethod.DELETE)
    public void deleteGroupRelationAttribute(@PathVariable Long idGroupRelation) {
        groupService.deleteGroupRelationAttribute(idGroupRelation);
    }
}
