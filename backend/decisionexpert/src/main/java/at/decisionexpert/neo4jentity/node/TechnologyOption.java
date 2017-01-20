package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOHasAddressedRequirement;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOHasImplication;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@NodeEntity
public class TechnologyOption extends CoreData {

    @Relationship(type = "HAS_ADDRESSEDREQUIREMENT", direction = Relationship.OUTGOING)
    private Set<TOHasAddressedRequirement> addressedRequirements = new HashSet<>(0);

    @Relationship(type = "HAS_IMPLICATION", direction = Relationship.OUTGOING)
    private Set<TOHasImplication> implications = new HashSet<>(0);

    private String description;

    public TechnologyOption(User creator, String name, String definition, String description) {
        super(creator, name, definition);
        this.description = description;
    }

    public TechnologyOption(String name, String definition, String description) {
        super(name, definition);
        this.description = description;
    }

    public TechnologyOption() {
    }

    public Set<TOHasAddressedRequirement> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(Set<TOHasAddressedRequirement> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }

    public Set<TOHasImplication> getImplications() {
        return implications;
    }

    public void setImplications(Set<TOHasImplication> implications) {
        this.implications = implications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
