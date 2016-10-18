package at.decisionexpert.service.component.technologyoption;

import at.decisionexpert.business.component.technologyoption.TechnologyOptionBusiness;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionDto;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@Service
public class TechnologyOptionServiceImpl implements TechnologyOptionService {

    @Autowired
    TechnologyOptionBusiness technologyOptionBusiness;

    @Override
    public TechnologyOptionDto getTechnologyOption(@NotNull Long componentId) {
        return technologyOptionBusiness.getTechnologyOption(componentId);
    }

    @Override
    public <T extends TOAttributeRelationship<A>, A extends CoreData> TechnologyOptionRelationDto createRelation(@NotNull Long idTechnologyOption, TechnologyOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        return technologyOptionBusiness.createRelation(idTechnologyOption, attributeInfo, relationClass, toNodeType);
    }

    @Override
    public <T extends TOAttributeRelationship<? extends CoreData>> TechnologyOptionRelationDto updateExistingRelationAttribute(@NotNull Long idTechnologyOption, @NotNull Long idTechnologyOptionRelation, TechnologyOptionRelationDto newValues, Class<T> clazz) {
        return technologyOptionBusiness.updateExistingRelationAttribute(idTechnologyOption, idTechnologyOptionRelation, newValues, clazz);
    }

    @Override
    public <T extends TOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(@NotNull Long idTechnologyOption, @NotNull Long idTechnologyOptionRelation, Class<T> relationClass) {
        technologyOptionBusiness.deleteRelationAttribute(idTechnologyOption, idTechnologyOptionRelation, relationClass);
    }
}
