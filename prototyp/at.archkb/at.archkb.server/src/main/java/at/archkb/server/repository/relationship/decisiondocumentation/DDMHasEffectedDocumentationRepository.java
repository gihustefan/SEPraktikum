package at.archkb.server.repository.relationship.decisiondocumentation;

import at.archkb.server.neo4jentity.relationship.decisiondocumentation.HasEffectedDocumentationModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DDMHasEffectedDocumentationRepository extends GraphRepository<HasEffectedDocumentationModel> {

    @Query("MATCH (ddm:DecisionDocumentationModel)->[rel:HAS_EFFECTEDDOCUMENTATIONMODEL]->() WHERE id(do) = {0} and rel.ordering = {1} RETURN rel")
    HasEffectedDocumentationModel findByOrdering(Long idDesignOption, int ordering);
}
