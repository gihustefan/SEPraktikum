package at.decisionexpert.business.component.technologyoption;

import at.decisionexpert.business.coredata.CreateCoreDataImpl;
import at.decisionexpert.exception.TechnologyOptionNotFoundException;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionDto;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import at.decisionexpert.repository.node.component.technologyoption.TechnologyOptionRepository;
import at.decisionexpert.repository.relationship.component.technologyoption.TOAttributeRelationshipRepository;
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
public class TechnologyOptionBusinessImpl implements TechnologyOptionBusiness {

    @Autowired
    private TechnologyOptionRepository technologyOptionRepository;

    @Autowired
    private NodeAttributeRepository nodeAttributeRepository;

    @Autowired
    private TOAttributeRelationshipRepository toAttributeRelationshipRepository;

    @Autowired
    private CreateCoreDataImpl createCoreDataImpl;

    @Override
    public TechnologyOptionDto getTechnologyOption(Long technologyOptionId) throws TechnologyOptionNotFoundException {
        if (technologyOptionId < 0) {
            throw new TechnologyOptionNotFoundException();
        }

        TechnologyOption technologyOption = technologyOptionRepository.findOne(technologyOptionId, 1);

        // When not found -> just return an empty DecisionGuidanceModel POJO
        if (technologyOption == null) {
            throw new TechnologyOptionNotFoundException();
        }

        return new TechnologyOptionDto(technologyOption);
    }

    @Override
    public <T extends TOAttributeRelationship<A>, A extends CoreData> TechnologyOptionRelationDto createRelation(Long idTechnologyOption, TechnologyOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idTechnologyOption);
        Assert.notNull(relationClass);

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            toAttributeRelationshipRepository.delete(toAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        TechnologyOption technologyOption = technologyOptionRepository.findOne(idTechnologyOption, 0);

        // ArchProfile is not allowed to be null
        Assert.notNull(technologyOption);

        A toNode = nodeAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null)
            toNode = createCoreDataImpl.createCoreData(attributeInfo.getName(), attributeInfo.getDescription(), toNodeType);

        try {

            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(technologyOption);
            newRelationship.setEndNode(toNode);
            newRelationship.setRationale(attributeInfo.getRationale());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // Saving the new Relationship
            newRelationship = toAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new TechnologyOptionRelationDto(newRelationship);

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
    public <T extends TOAttributeRelationship<? extends CoreData>> TechnologyOptionRelationDto updateExistingRelationAttribute(Long idTechnologyOption, Long idTechnologyOptionRelation, TechnologyOptionRelationDto newValues, Class<T> clazz) {
        // Fetch AP Relation from Graph DB
        T loadedRelation = toAttributeRelationshipRepository.findById(clazz, idTechnologyOptionRelation);
        Assert.notNull(loadedRelation);

        // Setting the new value for the description
        if (newValues.getRationale() != null)
            loadedRelation.setRationale(newValues.getRationale());

        // Setting the ordering -> must change the ordering values of other AP
        // Relationships as well
        if (newValues.getOrdering() != null) {

            T loadedRelationSameOrdering = toAttributeRelationshipRepository.findByOrdering(clazz, idTechnologyOption, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());
        }

        // Persist the new Values
        return new TechnologyOptionRelationDto(loadedRelation);
    }

    @Override
    public <T extends TOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(Long idTechnologyOption, Long idTechnologyOptionRelation, Class<T> relationClass) {
        Assert.notNull(idTechnologyOption);
        Assert.notNull(idTechnologyOptionRelation);
        Assert.notNull(relationClass);

        // Fetch the relation that should be deleted
        T technologyOptionRelationToDelete = toAttributeRelationshipRepository.findById(relationClass, idTechnologyOptionRelation);

        if (technologyOptionRelationToDelete == null)
            return;

        toAttributeRelationshipRepository.delete(technologyOptionRelationToDelete);

        // TODO Reorder
    }
}
