package at.decisionexpert.business.coredata;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelEffectedDocumentationModelDto;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.Tradeoff;

import java.util.List;

/**
 * Created by stefanhaselboeck on 07.10.16.
 */
public interface DDMCoreDataBusiness{

    <T extends CoreData> List<DecisionDocumentationModelRelationDto> getCoreData(String titlePartial, Class<T> coreDataClass);

    <T extends CoreData> List<DecisionDocumentationModelEffectedDocumentationModelDto> getEffectedDocumentationModels(String titlePartial);

    /**
     * Getting a Tradeoff by the given Tradeoff items -> if it is not found -> a new one will be created!
     *
     * @param idTradeoffItemOver  Which Tradeoffitem over
     * @param idTradeoffItemUnder Which Tradeoffitem under
     * @return The Tradeoff with the tradeoffitem over and under
     */
    Tradeoff getTradeoffByTradeoffItems(Long idTradeoffItemOver, Long idTradeoffItemUnder);
}
