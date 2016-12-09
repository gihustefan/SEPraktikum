package at.decisionexpert.service.belonging;

import at.decisionexpert.business.belonging.BelongingBusiness;
import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;
import at.decisionexpert.neo4jentity.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
@Service
public class BelongingServiceImpl implements BelongingService {

    @Autowired
    private BelongingBusiness belongingBusiness;

    @Override
    public <A extends Node> BelongingRelationDto createBelongingRelation(@NotNull Long idModel, @NotNull Long idGroup, @NotNull Class<A> toNodeType) {
        return belongingBusiness.createBelongingRelation(idModel, idGroup, toNodeType);
    }

    @Override
    public void deleteBelongingRelation(@NotNull Long idModel, @NotNull Long idCommentRelation) {
        belongingBusiness.deleteBelongingRelation(idModel, idCommentRelation);
    }
}
