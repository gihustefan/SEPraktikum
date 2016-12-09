package at.decisionexpert.business.coredata;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelEffectedDocumentationModelDto;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.decisionexpert.neo4jentity.node.*;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import at.decisionexpert.repository.node.TradeoffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 07.10.16.
 */
@Component
public class DDMCoreDataBusinessImpl implements DDMCoreDataBusiness {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    TradeoffRepository tradeoffRepository;

    @Autowired
    NodeAttributeRepository nodeAttributeRepository;


    @Override
    public <T extends CoreData> List<DecisionDocumentationModelRelationDto> getCoreData(String titlePartial, Class<T> coreDataClass) {
        Assert.notNull(titlePartial);
        Assert.notNull(coreDataClass);

        List<DecisionDocumentationModelRelationDto> result = new ArrayList<>();

        nodeAttributeRepository.findAllByTitle(titlePartial, coreDataClass).forEach(core -> {
            result.add(new DecisionDocumentationModelRelationDto(core));
        });

        return result;
    }

    @Override
    public <T extends CoreData> List<DecisionDocumentationModelEffectedDocumentationModelDto> getEffectedDocumentationModels(String titlePartial) {
        Assert.notNull(titlePartial);

        List<DecisionDocumentationModelEffectedDocumentationModelDto> result = new ArrayList<>();

        nodeAttributeRepository.findNodeByTitle(titlePartial, DecisionDocumentationModel.class).forEach(node -> {
            result.add(new DecisionDocumentationModelEffectedDocumentationModelDto(node));
        });

        return result;
    }

    @Override
    public Tradeoff getTradeoffByTradeoffItems(Long idTradeoffItemOver, Long idTradeoffItemUnder) {
        Assert.notNull(idTradeoffItemOver);
        Assert.notNull(idTradeoffItemUnder);


        Tradeoff tradeoff = tradeoffRepository.findTradeoffByTradeoffItems(idTradeoffItemOver, idTradeoffItemUnder);

        if (tradeoff != null)
            return tradeoff;

        // If tradeoff does not exist -> create a new one.
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        TradeoffItem over = nodeAttributeRepository.findById(idTradeoffItemOver, TradeoffItem.class);
        Assert.notNull(over);

        TradeoffItem under = nodeAttributeRepository.findById(idTradeoffItemUnder, TradeoffItem.class);
        Assert.notNull(under);

        return tradeoffRepository.save(new Tradeoff(user, over, under));
    }
}
