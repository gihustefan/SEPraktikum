package at.decisionexpert.repository.relationship.comment;

import at.decisionexpert.neo4jentity.relationship.HasComment;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
public interface HasCommentRepository extends GraphRepository<HasComment> {
}
