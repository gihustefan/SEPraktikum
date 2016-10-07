package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
@QueryResult
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDocumentationModelDto {
    private Serializable id;

    private String title;

    private Serializable ownerId;

    private String ownerName;

    private String description;

    private LocalDateTime created;

    private LocalDateTime modified;

    private Boolean published;

    private List<DecisionDocumentationModelRelationDto> designDecisions;

    private List<DecisionDocumentationModelRelationDto> addressedRequirements;

    private List<DecisionDocumentationModelEffectedDocumentationModelDto> effectedDocumentationModels;

    private List<DecisionDocumentationModelTradeoffRelationDto> tradeoffs;

    private List<DecisionDocumentationModelRelationDto> implications;

    private List<DecisionDocumentationModelRelationDto> requiredComponents;

    public DecisionDocumentationModelDto () {
        super();
    }

    /**
     * Initialize DTO based on a given Neo4J DecisionDocumentationModel node
     *
     * @param decisionDocumentationModel Neo4J DecisionDocumentationModel node
     */
    public DecisionDocumentationModelDto (DecisionDocumentationModel decisionDocumentationModel) {
        setId(decisionDocumentationModel.getId());
        setTitle(decisionDocumentationModel.getTitle());

        if (decisionDocumentationModel.getCreator() != null) {
            setOwnerId(decisionDocumentationModel.getCreator().getId());
            setOwnerName(decisionDocumentationModel.getCreator().getOriginalUsername());
        }

        setDescription(decisionDocumentationModel.getDescription());
        setCreated(LocalDateTime.ofInstant(Instant.ofEpochMilli(decisionDocumentationModel.getCreationDate()),
                TimeZone.getDefault().toZoneId()));
        setModified(LocalDateTime.ofInstant(Instant.ofEpochMilli(decisionDocumentationModel.getLastModified()),
                TimeZone.getDefault().toZoneId()));

        setPublished(decisionDocumentationModel.isPublished());

        // Design Decisions
        setDesignDecisions(getNeo4JRelationDto(decisionDocumentationModel.getDesignDecisions()));
        // AddressedRequirements
        setAddressedRequirements(getNeo4JRelationDto(decisionDocumentationModel.getAddressedRequirements()));
        // Implications
        setImplications(getNeo4JRelationDto(decisionDocumentationModel.getImplications()));
        // RequiredComponents
        setRequiredComponents(getNeo4JRelationDto(decisionDocumentationModel.getRequiredComponents()));


        // EffectedDocumentationModels
        List<DecisionDocumentationModelEffectedDocumentationModelDto> effectedDocumentationModels = new ArrayList<>();
        decisionDocumentationModel.getEffectedDocumentationModels().forEach(to -> {
            effectedDocumentationModels.add(new DecisionDocumentationModelEffectedDocumentationModelDto(to));
        });

        setEffectedDocumentationModels(effectedDocumentationModels);


        // Tradeoffs
        List<DecisionDocumentationModelTradeoffRelationDto> tradeoffs = new ArrayList<>();
        decisionDocumentationModel.getTradeoffs().forEach(to -> {
            tradeoffs.add(new DecisionDocumentationModelTradeoffRelationDto(to));
        });

        setTradeoffs(tradeoffs);

    }

    /**
     * Simple Help method for converting Neo4J DecisionDocumentationModel Attribute
     * Relationships into DecisionDocumentationModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<DecisionDocumentationModelRelationDto> getNeo4JRelationDto(
            Collection<? extends DDMAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<DecisionDocumentationModelRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new DecisionDocumentationModelRelationDto(item));
        });
        return result;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Serializable getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Serializable ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public List<DecisionDocumentationModelRelationDto> getDesignDecisions() {
        return designDecisions;
    }

    public void setDesignDecisions(List<DecisionDocumentationModelRelationDto> designDecisions) {
        this.designDecisions = designDecisions;
    }

    public List<DecisionDocumentationModelRelationDto> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(List<DecisionDocumentationModelRelationDto> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }

    public List<DecisionDocumentationModelEffectedDocumentationModelDto> getEffectedDocumentationModels() {
        return effectedDocumentationModels;
    }

    public void setEffectedDocumentationModels(List<DecisionDocumentationModelEffectedDocumentationModelDto> effectedDocumentationModels) {
        this.effectedDocumentationModels = effectedDocumentationModels;
    }

    public List<DecisionDocumentationModelTradeoffRelationDto> getTradeoffs() {
        return tradeoffs;
    }

    public void setTradeoffs(List<DecisionDocumentationModelTradeoffRelationDto> tradeoffs) {
        this.tradeoffs = tradeoffs;
    }

    public List<DecisionDocumentationModelRelationDto> getImplications() {
        return implications;
    }

    public void setImplications(List<DecisionDocumentationModelRelationDto> implications) {
        this.implications = implications;
    }

    public List<DecisionDocumentationModelRelationDto> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<DecisionDocumentationModelRelationDto> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }
}
