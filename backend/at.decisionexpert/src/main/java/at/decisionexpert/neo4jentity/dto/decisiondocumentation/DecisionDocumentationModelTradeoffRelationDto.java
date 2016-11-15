package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasTradeoff;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDocumentationModelTradeoffRelationDto {

    private String nameUnder;

    private String nameOver;

    private String descriptionUnder;

    private String descriptionOver;

    private Long idAttributeUnder;

    private Long idAttributeOver;

    private Long id;

    private Integer ordering;

    public DecisionDocumentationModelTradeoffRelationDto() {
    }

    public DecisionDocumentationModelTradeoffRelationDto(HasTradeoff hasTradeoff) {

        if(hasTradeoff.getEndNode() != null) {
            if(hasTradeoff.getEndNode().getTradeoffItemOver() != null) {

                setNameOver(hasTradeoff.getEndNode().getTradeoffItemOver().getName());
                setIdAttributeOver(hasTradeoff.getEndNode().getTradeoffItemOver().getId());
            }
            if(hasTradeoff.getEndNode().getTradeoffItemUnder() != null) {

                setNameUnder(hasTradeoff.getEndNode().getTradeoffItemUnder().getName());
                setIdAttributeUnder(hasTradeoff.getEndNode().getTradeoffItemUnder().getId());
            }
        }

        setDescriptionUnder(hasTradeoff.getDescriptionUnder());
        setDescriptionOver(hasTradeoff.getDescriptionOver());
        setId(hasTradeoff.getId());
        setOrdering(hasTradeoff.getOrdering());
    }

    public String getNameUnder() {
        return nameUnder;
    }

    public void setNameUnder(String nameUnder) {
        this.nameUnder = nameUnder;
    }

    public String getNameOver() {
        return nameOver;
    }

    public void setNameOver(String nameOver) {
        this.nameOver = nameOver;
    }

    public String getDescriptionUnder() {
        return descriptionUnder;
    }

    public void setDescriptionUnder(String descriptionUnder) {
        this.descriptionUnder = descriptionUnder;
    }

    public String getDescriptionOver() {
        return descriptionOver;
    }

    public void setDescriptionOver(String descriptionOver) {
        this.descriptionOver = descriptionOver;
    }

    public Long getIdAttributeUnder() {
        return idAttributeUnder;
    }

    public void setIdAttributeUnder(Long idAttributeUnder) {
        this.idAttributeUnder = idAttributeUnder;
    }

    public Long getIdAttributeOver() {
        return idAttributeOver;
    }

    public void setIdAttributeOver(Long idAttributeOver) {
        this.idAttributeOver = idAttributeOver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
