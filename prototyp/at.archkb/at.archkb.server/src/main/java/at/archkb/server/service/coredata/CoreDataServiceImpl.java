package at.archkb.server.service.coredata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.archkb.server.business.coredata.CoreDataBusiness;
import at.archkb.server.neo4jentity.dto.APAttributeAdminDto;
import at.archkb.server.neo4jentity.dto.ApAttributeDetailAdminDto;
import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.CoreData;

@Service
public class CoreDataServiceImpl implements CoreDataService {
	
	@Autowired
	private CoreDataBusiness coreDataBusiness;
	

	@Override
	public <T extends CoreData> List<ArchProfileRelationDto> getCoreData(String titlePartial,
																		 Class<T> coreDataClass) {
		return coreDataBusiness.getCoreData(titlePartial, coreDataClass);
	}


	@Override
	public Iterable<APAttributeAdminDto> getAllOfType(String type) {
		return coreDataBusiness.getAllOfType(type);
	}


	@Override
	public ApAttributeDetailAdminDto getById(Long id) {
		return coreDataBusiness.getById(id);
	}


	@Override
	public ApAttributeDetailAdminDto addToCore(Long id) {
		return coreDataBusiness.addToCore(id);
	}


	@Override
	public ApAttributeDetailAdminDto removeFromCore(Long id) {
		return coreDataBusiness.removeFromCore(id);
	}


	@Override
	public ApAttributeDetailAdminDto updateAPAttribute(ApAttributeDetailAdminDto apat) {
		return coreDataBusiness.updateAPAttribute(apat);
	}


	@Override
	public ApAttributeDetailAdminDto createAPAttribute(ApAttributeDetailAdminDto apat) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return coreDataBusiness.createAPAttribute(apat);
	}

	@Override
	public <T extends CoreData> T createArchProfileAttribute(ArchProfileRelationDto attributeValues, Class<T> attributeType) {
		return coreDataBusiness.createCoreData(attributeValues.getName(), attributeValues.getDefinition(), attributeType);
	}

}
