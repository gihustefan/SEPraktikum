package at.decisionexpert.service.decisiondocumentation;

import at.decisionexpert.business.decisiondocumentation.DecisionDocumentationBusiness;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.*;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;
import at.decisionexpert.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@Service
public class DecisionDocumentationServiceImpl implements DecisionDocumentationService{

    @Autowired
    private DecisionDocumentationBusiness decisionDocumentationBusiness;

    @Override
    public DecisionDocumentationModelDto getDecisionDocumentationModel(@NotNull Long id) {
        return decisionDocumentationBusiness.getDecisionDocumentationModel(id);
    }

    @Override
    public DecisionDocumentationModelPageableDto getNewestDecisionDocumentationModels(@NotNull Integer page, @NotNull Integer size) {
        return decisionDocumentationBusiness.getNewestDecisionDocumentationModels(page, size, SecurityUtils.hasRole("ROLE_ADMIN", SecurityContextHolder.getContext().getAuthentication()));
    }

    @Override
    public DecisionDocumentationModelPageableDto getUserDecisionDocumentationModel(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size) {
        return decisionDocumentationBusiness.getUserDecisionDocumentationModels(idUser, page, size);
    }

    @Override
    public DecisionDocumentationModelDto createDecisionDocumentationModel(DecisionDocumentationModelChangeRequestDto decisionDocumentationModel) {
        return decisionDocumentationBusiness.createDecisionDocumentationModel(decisionDocumentationModel);
    }

    @Override
    public DecisionDocumentationModelDto updateDecisionDocumentationModelProperties(@NotNull Long id, @NotNull DecisionDocumentationModelChangeRequestDto newValues) {
        return decisionDocumentationBusiness.updateDecisionDocumentationModelProperties(id, newValues);
    }

    @Override
    public void deleteDecisionDocumentationModel(@NotNull Long id) {
        decisionDocumentationBusiness.deleteDecisionDocumentationModel(id);
    }

    @Override
    public <T extends DDMAttributeRelationship<A>, A extends CoreData> DecisionDocumentationModelRelationDto createRelation(@NotNull Long idDecisionDocumentationModel, DecisionDocumentationModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        return decisionDocumentationBusiness.createRelation(idDecisionDocumentationModel, attributeInfo, relationClass, toNodeType);
    }

    @Override
    public <T extends DDMAttributeRelationship<? extends CoreData>> DecisionDocumentationModelRelationDto updateExistingRelationAttribute(@NotNull Long idDecisionDocumentationModel, @NotNull Long idDecisionDocumentationModelRelation, DecisionDocumentationModelRelationDto newValues, Class<T> clazz) {
        return decisionDocumentationBusiness.updateExistingRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationModelRelation, newValues, clazz);
    }

    @Override
    public <T extends DDMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(@NotNull Long idDecisionDocumentationModel, @NotNull Long idDecisionDocumentationModelRelation, Class<T> relationClass) {
        decisionDocumentationBusiness.deleteRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationModelRelation, relationClass);
    }

    @Override
    public DecisionDocumentationModelEffectedDocumentationModelDto createDocumentationModelRelation(@NotNull Long idDecisionDocumentation, DecisionDocumentationModelEffectedDocumentationModelDto effectedDocumentationModelInfo) {
        return decisionDocumentationBusiness.createEffectedDocumentationModelRelation(idDecisionDocumentation, effectedDocumentationModelInfo);
    }

    @Override
    public DecisionDocumentationModelEffectedDocumentationModelDto updateExistingDocumentationModelRelationAttribute(@NotNull Long idDecisionDocumentation, @NotNull Long idEffectedDecisionDocumentationRelation, @NotNull DecisionDocumentationModelEffectedDocumentationModelDto newValues) {
        return decisionDocumentationBusiness.updateExistingEffectedDocumentationModelRelationAttribute(idDecisionDocumentation, idEffectedDecisionDocumentationRelation, newValues);
    }

    @Override
    public void deleteDocumentationModelRelationAttribute(@NotNull Long idDecisionDocumentation, @NotNull Long idDecisionDocumentationRelation) {
        decisionDocumentationBusiness.deleteEffectedDocumentationModelRelationAttribute(idDecisionDocumentation, idDecisionDocumentationRelation);
    }

    @Override
    public DecisionDocumentationModelTradeoffRelationDto createTradeoffRelation(@NotNull Long idDecisionDocumentation, DecisionDocumentationModelTradeoffRelationDto tradeoffInfo) {
        return decisionDocumentationBusiness.createTradeoffRelation(idDecisionDocumentation, tradeoffInfo);
    }

    @Override
    public DecisionDocumentationModelTradeoffRelationDto updateExistingTradeoffRelationAttribute(@NotNull Long idDecisionDocumentation, @NotNull Long idTradeoffRelation, @NotNull DecisionDocumentationModelTradeoffRelationDto newValues) {
        return decisionDocumentationBusiness.updateExistingTradeoffRelationAttribute(idDecisionDocumentation, idTradeoffRelation, newValues);
    }

    @Override
    public void deleteDecisionDocumentationTradeoffRelation(@NotNull Long idDecisionDocumentation, @NotNull Long idTradeoffRelation) {
        decisionDocumentationBusiness.deleteTradeoffRelationAttribute(idDecisionDocumentation, idTradeoffRelation);
    }
}
