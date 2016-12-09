package at.decisionexpert.repository.node.comment;

import at.decisionexpert.neo4jentity.node.Comment;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
public interface CommentRepository extends GraphRepository<Comment> {
}
