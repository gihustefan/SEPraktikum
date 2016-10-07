package at.archkb.server.neo4jentity.node;

import at.archkb.server.neo4jentity.relationship.decisiondocumentation.*;
import at.archkb.server.neo4jentity.relationship.decisiondocumentation.HasTradeoff;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
@NodeEntity
public class DecisionDocumentationModel extends Node {

    @Relationship(type = "HAS_DESIGNDECISION", direction = Relationship.OUTGOING)
    private Set<HasDesignDecision> designDecisions = new HashSet<>(0);

    @Relationship(type = "HAS_ADDRESSEDREQUIREMENT", direction = Relationship.OUTGOING)
    private Set<HasAddressedRequirement> addressedRequirements = new HashSet<>(0);

    @Relationship(type = "HAS_EFFECTEDDOCUMENTATIONMODEL", direction = Relationship.OUTGOING)
    private Set<HasEffectedDocumentationModel> effectedDocumentationModels = new HashSet<>(0);

    @Relationship(type = "HAS_TRADEOFF", direction = Relationship.OUTGOING)
    private Set<HasTradeoff> tradeoffs = new HashSet<>(0);

    @Relationship(type = "HAS_IMPLICATION", direction = Relationship.OUTGOING)
    private Set<HasImplication> implications = new HashSet<>(0);

    @Relationship(type = "HAS_REQUIREDCOMPONENT", direction = Relationship.OUTGOING)
    private Set<HasRequiredComponent> requiredComponents = new HashSet<>(0);

    private String title;

    private String description;

    private Boolean published;

    public DecisionDocumentationModel (User creator) {
        super(creator);
    }

    public DecisionDocumentationModel () {
        super();
    }

    public Set<HasDesignDecision> getDesignDecisions() {
        return designDecisions;
    }

    public void setDesignDecisions(Set<HasDesignDecision> designDecisions) {
        this.designDecisions = designDecisions;
    }

    public Set<HasAddressedRequirement> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(Set<HasAddressedRequirement> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }

    public Set<HasEffectedDocumentationModel> getEffectedDocumentationModels() {
        return effectedDocumentationModels;
    }

    public void setEffectedDocumentationModels(Set<HasEffectedDocumentationModel> effectedDocumentationModels) {
        this.effectedDocumentationModels = effectedDocumentationModels;
    }

    public Set<HasTradeoff> getTradeoffs() {
        return tradeoffs;
    }

    public void setTradeoffs(Set<HasTradeoff> tradeoffs) {
        this.tradeoffs = tradeoffs;
    }

    public Set<HasImplication> getImplications() {
        return implications;
    }

    public void setImplications(Set<HasImplication> implications) {
        this.implications = implications;
    }

    public Set<HasRequiredComponent> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(Set<HasRequiredComponent> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
