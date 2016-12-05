package at.decisionexpert.service.decisionguidancemodel;

import at.decisionexpert.business.decisionguidance.DecisionGuidanceModelBusiness;
import at.decisionexpert.controller.decisionguidance.DecisionGuidanceModelController;
import at.decisionexpert.neo4jentity.dto.decisionguidance.*;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import at.decisionexpert.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    public DecisionGuidanceModelPageableDto getDecisionGuidanceModels(@NotNull Integer page, @NotNull Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType type) {
        return decisionGuidanceModelBusiness.getDecisionGuidanceModels(page, size, SecurityUtils.hasRole("ROLE_ADMIN", SecurityContextHolder.getContext().getAuthentication()), type);
    }

    @Override
    public DecisionGuidanceModelPageableDto getUserDecisionGuidanceModel(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType type) {
        return decisionGuidanceModelBusiness.getUserDecisionGuidanceModels(idUser, page, size, type);
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
    public List<DecisionGuidanceModelRelationDto> getPotentialRequirements(@NotNull Long idDecisionGuidanceModel) {
        return decisionGuidanceModelBusiness.getPotentialRequirements(idDecisionGuidanceModel);
    }

    @Override
    public <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRelationDto createRelation(@NotNull Long idDecisionGuidanceModel, DecisionGuidanceModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        return decisionGuidanceModelBusiness.createRelation(idDecisionGuidanceModel, attributeInfo, relationClass, toNodeType);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> DecisionGuidanceModelRelationDto updateExistingRelationAttribute(
            @NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRelationDto newValues, Class<T> clazz) {
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

    @Override
    public DecisionGuidanceModelDesignOptionRelationDto createDesignOptionRelation(@NotNull Long idDecisionGuidanceModel, DecisionGuidanceModelDesignOptionRelationDto designOptionInfo) {
        return decisionGuidanceModelBusiness.createDesignOptionRelation(idDecisionGuidanceModel, designOptionInfo);
    }

    @Override
    public DecisionGuidanceModelDesignOptionRelationDto updateExistingDesignOptionRelationAttribute(@NotNull Long idDecisionGuidanceModel, @NotNull Long idDesignOptionRelation, @NotNull DecisionGuidanceModelDesignOptionRelationDto newValues) {
        return decisionGuidanceModelBusiness.updateExistingDesignOptionRelationAttribute(idDecisionGuidanceModel, idDesignOptionRelation, newValues);
    }

    @Override
    public void deleteDesignOptionRelationAttribute(@NotNull Long idDecisionGuidanceModel, @NotNull Long idDesignOptionRelation) {
        decisionGuidanceModelBusiness.deleteDesignOptionRelationAttribute(idDecisionGuidanceModel, idDesignOptionRelation);
    }
}
