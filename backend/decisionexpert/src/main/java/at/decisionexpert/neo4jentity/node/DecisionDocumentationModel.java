package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.*;
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
    private Set<DDMHasAddressedRequirement> addressedRequirements = new HashSet<>(0);

    @Relationship(type = "HAS_EFFECTEDDOCUMENTATIONMODEL", direction = Relationship.OUTGOING)
    private Set<HasEffectedDocumentationModel> effectedDocumentationModels = new HashSet<>(0);

    @Relationship(type = "HAS_TRADEOFF", direction = Relationship.OUTGOING)
    private Set<HasTradeoff> tradeoffs = new HashSet<>(0);

    @Relationship(type = "HAS_IMPLICATION", direction = Relationship.OUTGOING)
    private Set<DDMHasImplication> implications = new HashSet<>(0);

    @Relationship(type = "HAS_REQUIREDCOMPONENT", direction = Relationship.OUTGOING)
    private Set<DDMHasRequiredComponent> requiredComponents = new HashSet<>(0);

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

    public Set<DDMHasAddressedRequirement> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(Set<DDMHasAddressedRequirement> addressedRequirements) {
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

    public Set<DDMHasImplication> getImplications() {
        return implications;
    }

    public void setImplications(Set<DDMHasImplication> implications) {
        this.implications = implications;
    }

    public Set<DDMHasRequiredComponent> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(Set<DDMHasRequiredComponent> requiredComponents) {
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
