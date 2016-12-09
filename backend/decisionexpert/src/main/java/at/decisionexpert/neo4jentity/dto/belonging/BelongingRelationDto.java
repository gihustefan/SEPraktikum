package at.decisionexpert.neo4jentity.dto.belonging;

import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.relationship.HasBelonging;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BelongingRelationDto<V extends Node> {

    private Long id;

    private Long idAttribute;

    public BelongingRelationDto() {
        super();
    }

    public BelongingRelationDto(HasBelonging<V> hasBelonging) {
        setId(hasBelonging.getId());
        setIdAttribute(hasBelonging.getEndNode().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }
}
