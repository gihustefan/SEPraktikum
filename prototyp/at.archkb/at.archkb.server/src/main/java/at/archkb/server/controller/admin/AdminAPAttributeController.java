package at.archkb.server.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.archkb.server.neo4jentity.dto.APAttributeAdminDto;
import at.archkb.server.neo4jentity.dto.ApAttributeDetailAdminDto;
import at.archkb.server.service.coredata.CoreDataService;

@RestController
@RequestMapping("api/admin/apattribute")
@ResponseBody
public class AdminAPAttributeController {
	
	@Autowired
	CoreDataService coreDataService;
	
	@RequestMapping(value = "{type}", method = RequestMethod.GET)
	Iterable<APAttributeAdminDto>getAllOfType(@PathVariable String type){
		return coreDataService.getAllOfType(type);
	}
	
	@RequestMapping(value = "/byid/{id}", method = RequestMethod.GET)
	ApAttributeDetailAdminDto getById(@PathVariable Long id){
		return coreDataService.getById(id);
	}
	
	@RequestMapping(value = "/addCore/{id}", method = RequestMethod.POST)
	ApAttributeDetailAdminDto addToCore(@PathVariable Long id){
		return coreDataService.addToCore(id);
	}
	
	@RequestMapping(value = "/removeCore/{id}", method = RequestMethod.POST)
	ApAttributeDetailAdminDto removeFromCore(@PathVariable Long id){
		return coreDataService.removeFromCore(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ApAttributeDetailAdminDto updateAPAttribute(@RequestBody ApAttributeDetailAdminDto apat){
		return coreDataService.updateAPAttribute(apat);
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	ApAttributeDetailAdminDto createAPAttribute(@RequestBody ApAttributeDetailAdminDto apat) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		return coreDataService.createAPAttribute(apat);
	}

}
