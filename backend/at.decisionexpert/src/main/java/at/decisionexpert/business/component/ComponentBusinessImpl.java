package at.decisionexpert.business.component;

import at.decisionexpert.business.coredata.CreateCoreDataImpl;
import at.decisionexpert.exception.ComponentNotFoundException;
import at.decisionexpert.neo4jentity.dto.component.ComponentDto;
import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import at.decisionexpert.repository.node.component.ComponentRepository;
import at.decisionexpert.repository.relationship.component.CompAttributeRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@Component
public class ComponentBusinessImpl implements ComponentBusiness{

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private NodeAttributeRepository nodeAttributeRepository;

    @Autowired
    private CompAttributeRelationshipRepository compAttributeRelationshipRepository;

    @Autowired
    private CreateCoreDataImpl createCoreDataImpl;

    @Override
    public ComponentDto getComponent(Long componentId) throws ComponentNotFoundException {
        if (componentId < 0) {
            throw new ComponentNotFoundException();
        }

        at.decisionexpert.neo4jentity.node.Component component = componentRepository.findOne(componentId, 1);

        // When not found -> just return an empty DecisionGuidanceModel POJO
        if (component == null) {
            throw new ComponentNotFoundException();
        }

        return new ComponentDto(component);
    }

    @Override
    public <T extends ComponentAttributeRelationship<A>, A extends CoreData> ComponentRelationDto createRelation(Long idComponent, ComponentRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idComponent);
        Assert.notNull(relationClass);

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            compAttributeRelationshipRepository.delete(compAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        at.decisionexpert.neo4jentity.node.Component component = componentRepository.findOne(idComponent, 0);

        // ArchProfile is not allowed to be null
        Assert.notNull(component);

        A toNode = nodeAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null)
            toNode = createCoreDataImpl.createCoreData(attributeInfo.getName(), attributeInfo.getDefinition(), toNodeType);

        try {

            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(component);
            newRelationship.setEndNode(toNode);
            // Setting the definition of the Relation to the same as the toNode (than we dont have to worry about changes in the toNode -> users can alter the definition of the properties themselves!)
            newRelationship.setDefinition(toNode.getDefinition());
            newRelationship.setDescription(attributeInfo.getDescription());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // Saving the new Relationship
            newRelationship = compAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new ComponentRelationDto(newRelationship);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> ComponentRelationDto updateExistingRelationAttribute(Long idComponent, Long idComponentRelation, ComponentRelationDto newValues, Class<T> clazz) {
        // Fetch AP Relation from Graph DB
        T loadedRelation = compAttributeRelationshipRepository.findById(clazz, idComponentRelation);
        Assert.notNull(loadedRelation);

        // Setting the new value for the description
        if (newValues.getDescription() != null)
            loadedRelation.setDescription(newValues.getDescription());

        // Setting the new value for the definition
        if (newValues.getDefinition() != null)
            loadedRelation.setDefinition(newValues.getDefinition());

        // Setting the ordering -> must change the ordering values of other AP
        // Relationships as well
        if (newValues.getOrdering() != null) {

            T loadedRelationSameOrdering = compAttributeRelationshipRepository.findByOrdering(clazz, idComponent, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());
        }

        // Persist the new Values
        return new ComponentRelationDto(loadedRelation);
    }

    @Override
    public <T extends ComponentAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(Long idComponent, Long idComponentRelation, Class<T> relationClass) {
        Assert.notNull(idComponent);
        Assert.notNull(idComponentRelation);
        Assert.notNull(relationClass);

        // Fetch the relation that should be deleted
        T componentRelationToDelete = compAttributeRelationshipRepository.findById(relationClass, idComponentRelation);

        if (componentRelationToDelete == null)
            return;

        compAttributeRelationshipRepository.delete(componentRelationToDelete);

        // TODO Reorder
    }
}
