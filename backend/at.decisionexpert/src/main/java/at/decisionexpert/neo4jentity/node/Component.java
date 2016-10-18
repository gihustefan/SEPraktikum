package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.component.HasPotentialRequirement;
import at.decisionexpert.neo4jentity.relationship.component.HasTechnologyOption;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 21.09.16.
 */
@NodeEntity
public class Component extends CoreData {

    @Relationship(type = "HAS_POTENTIALREQUIREMENT", direction = Relationship.OUTGOING)
    private Set<HasPotentialRequirement> potentialRequirements = new HashSet<>(0);

    @Relationship(type = "HAS_TECHNOLOGYOPTION", direction = Relationship.OUTGOING)
    private Set<HasTechnologyOption> technologyOptions = new HashSet<>(0);

    private String description;

    public Component(User creator, String name, String definition) {
        super(creator, name, definition);
        // TODO Auto-generated constructor stub
    }

    public Component(String name, String definition) {
        super(name, definition);
        // TODO Auto-generated constructor stub
    }

    public Component () {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HasPotentialRequirement> getPotentialRequirements() {
        return potentialRequirements;
    }

    public void setPotentialRequirements(Set<HasPotentialRequirement> potentialRequirements) {
        this.potentialRequirements = potentialRequirements;
    }

    public Set<HasTechnologyOption> getTechnologyOptions() {
        return technologyOptions;
    }

    public void setTechnologyOptions(Set<HasTechnologyOption> technologyOptions) {
        this.technologyOptions = technologyOptions;
    }
}
