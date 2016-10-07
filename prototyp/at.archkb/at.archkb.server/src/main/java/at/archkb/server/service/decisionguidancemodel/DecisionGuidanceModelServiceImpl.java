package at.archkb.server.service.decisionguidancemodel;

import at.archkb.server.business.decisionguidance.DecisionGuidanceModelBusiness;
import at.archkb.server.neo4jentity.dto.decisionguidance.*;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import at.archkb.server.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@Service
public class DecisionGuidanceModelServiceImpl implements DecisionGuidanceModelService {

    @Autowired
    private DecisionGuidanceModelBusiness decisionGuidanceModelBusiness;

    @Override
    public DecisionGuidanceModelDto getDecisionGuidanceModel(@NotNull Long id) {
        return decisionGuidanceModelBusiness.getDecisionGuidanceModel(id);
    }

    @Override
    public DecisionGuidanceModelPageableDto getNewestDecisionGuidanceModels(@NotNull Integer page, @NotNull Integer size) {
        return decisionGuidanceModelBusiness.getNewestDecisionGuidanceModels(page, size, SecurityUtils.hasRole("ROLE_ADMIN", SecurityContextHolder.getContext().getAuthentication()));
    }

    @Override
    public DecisionGuidanceModelPageableDto getUserDecisionGuidanceModel(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size) {
        return decisionGuidanceModelBusiness.getUserDecisionGuidanceModels(idUser, page, size);
    }

    @Override
    public DecisionGuidanceModelDto createDecisionGuidanceModel(DecisionGuidanceModelChangeRequestDto decisionGuidanceModel) {
        return decisionGuidanceModelBusiness.createDecisionGuidanceModel(decisionGuidanceModel);
    }

    @Override
    public DecisionGuidanceModelDto updateDecisionGuidanceModelProperties(@NotNull Long id, @NotNull DecisionGuidanceModelChangeRequestDto newValues) {
        return decisionGuidanceModelBusiness.updateDecisionGuidanceModelProperties(id, newValues);
    }

    @Override
    public void deleteDecisionGuidanceModel(@NotNull Long id) {
        decisionGuidanceModelBusiness.deleteDecisionGuidanceModel(id);
    }

    @Override
    public <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRealtionDto createRelation(@NotNull Long idDecisionGuidanceModel, DecisionGuidanceModelRealtionDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        return decisionGuidanceModelBusiness.createRelation(idDecisionGuidanceModel, attributeInfo, relationClass, toNodeType);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> DecisionGuidanceModelRealtionDto updateExistingRelationAttribute(
            @NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRealtionDto newValues, Class<T> clazz) {
        return decisionGuidanceModelBusiness.updateExistingRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRelation, newValues, clazz);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation, Class<T> relationClass) {
        decisionGuidanceModelBusiness.deleteRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRelation, relationClass);
    }


    @Override
    public DecisionGuidanceModelRelatedGuidanceModelsDto createGuidanceModelRelation(@NotNull Long idDecisionGuidanceModel, DecisionGuidanceModelRelatedGuidanceModelsDto guidanceModelInfo) {
        return decisionGuidanceModelBusiness.createGuidanceModelRelation(idDecisionGuidanceModel, guidanceModelInfo);
    }

    @Override
    public DecisionGuidanceModelRelatedGuidanceModelsDto updateExistingGuidanceModelRelationAttribute(@NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation, @NotNull DecisionGuidanceModelRelatedGuidanceModelsDto newValues) {
        return decisionGuidanceModelBusiness.updateExistingGuidanceModelRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRelation, newValues);
    }

    @Override
    public void deleteGuidanceModelRelationAttribute(@NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation) {
        decisionGuidanceModelBusiness.deleteGuidanceModelRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRelation);
    }
}
