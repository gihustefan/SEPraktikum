package at.decisionexpert.service.component;

import at.decisionexpert.business.component.ComponentBusiness;
import at.decisionexpert.neo4jentity.dto.component.ComponentDto;
import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentBusiness componentBusiness;

    @Override
    public ComponentDto getComponent(@NotNull Long componentId) {
        return componentBusiness.getComponent(componentId);
    }


    @Override
    public <T extends ComponentAttributeRelationship<A>, A extends CoreData> ComponentRelationDto createRelation(@NotNull Long idComponent, ComponentRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        return componentBusiness.createRelation(idComponent, attributeInfo, relationClass, toNodeType);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> ComponentRelationDto updateExistingRelationAttribute(@NotNull Long idComponent, @NotNull Long idComponentRelation, ComponentRelationDto newValues, Class<T> clazz) {
        return componentBusiness.updateExistingRelationAttribute(idComponent, idComponentRelation, newValues, clazz);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(@NotNull Long idComponent, @NotNull Long idComponentRelation, Class<T> relationClass) {
        componentBusiness.deleteRelationAttribute(idComponent, idComponentRelation, relationClass);
    }
}
