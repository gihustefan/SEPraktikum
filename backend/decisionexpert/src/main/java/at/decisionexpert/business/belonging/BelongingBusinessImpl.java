package at.decisionexpert.business.belonging;

import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.Group;
import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.relationship.HasBelonging;
import at.decisionexpert.repository.node.decisiondocumentation.DecisionDocumentationRepository;
import at.decisionexpert.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.decisionexpert.repository.node.group.GroupRepository;
import at.decisionexpert.repository.relationship.belonging.HasBelongingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
@Component
public class BelongingBusinessImpl implements BelongingBusiness {

    @Autowired
    private DecisionGuidanceModelRepository decisionGuidanceModelRepository;

    @Autowired
    private DecisionDocumentationRepository decisionDocumentationRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private HasBelongingRepository hasBelongingRepository;

    @Override
    public <A extends Node> BelongingRelationDto createBelongingRelation(Long idModel, Long idGroup, Class<A> toNodeType) {
        Assert.notNull(idModel);
        Assert.notNull(idGroup);

        A startModel = null;
        if (toNodeType == DecisionGuidanceModel.class) {
            startModel = (A) decisionGuidanceModelRepository.findOne(idModel);
        } else if (toNodeType == DecisionDocumentationModel.class) {
            startModel = (A) decisionDocumentationRepository.findOne(idModel);
        }
        Assert.notNull(startModel);

        Group endGroup = groupRepository.findOne(idGroup);

        HasBelonging hasBelonging = new HasBelonging(startModel, endGroup);
        hasBelonging = hasBelongingRepository.save(hasBelonging);

        return new BelongingRelationDto(hasBelonging);
    }

    @Override
    public void deleteBelongingRelation(Long idModel, Long idBelongingRelation) {
        Assert.notNull(idModel);
        Assert.notNull(idBelongingRelation);

        HasBelonging hasBelonging = hasBelongingRepository.findOne(idBelongingRelation);
        Assert.notNull(hasBelonging);

        hasBelongingRepository.delete(hasBelonging);
    }
}
