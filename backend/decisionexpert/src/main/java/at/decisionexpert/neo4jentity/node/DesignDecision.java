package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMHasAddressedRequirement;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMHasImplication;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMHasRequiredComponent;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasEffectedDocumentationModel;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@NodeEntity
public class DesignDecision extends Node {

    @Relationship(type = "HAS_IMPLICATION", direction = Relationship.OUTGOING)
    private Set<DDMHasImplication> implications = new HashSet<>(0);

    @Relationship(type = "HAS_ADDRESSEDREQUIREMENT", direction = Relationship.OUTGOING)
    private Set<DDMHasAddressedRequirement> addressedRequirements = new HashSet<>(0);

    @Relationship(type = "HAS_EFFECTEDDOCUMENTATIONMODEL", direction = Relationship.OUTGOING)
    private Set<HasEffectedDocumentationModel> effectedDocumentationModels = new HashSet<>(0);

    @Relationship(type = "HAS_REQUIREDCOMPONENT", direction = Relationship.OUTGOING)
    private Set<DDMHasRequiredComponent> requiredComponents = new HashSet<>(0);

    private String name;

    private String description;

    private String rationale;

    public DesignDecision(User creator, String name, String description) {
        super(creator);
        this.name = name;
        this.description = description;
    }

    public DesignDecision(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Set<DDMHasImplication> getImplications() {
        return implications;
    }

    public void setImplications(Set<DDMHasImplication> implications) {
        this.implications = implications;
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

    public Set<DDMHasRequiredComponent> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(Set<DDMHasRequiredComponent> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }
}
