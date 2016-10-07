package at.decisionexpert.repository.node.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.Node;

import java.util.Collection;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationModelAttributeRepository {

    /**
     * Loading DecisionDocumentationModels according to a partial title
     *
     * @param partialTitle The partial title for filtering
     * @return a list of DecisionDocumentationModels filtered by the partialTitle, performed on the node Class
     */
    <T extends Node> Collection<T> findDDMByTitle(String partialTitle, Class<T> attributeClass);

    /**
     * Loading a specific DecisionDocumentationModel according to a Id
     *
     * @param id The id for filtering
     * @return a single Decision Documentation Model
     */
    <T extends Node> T findDDMById(Long id, Class<T> attributeClass);

    /**
     * Loading DecisionDocumentationModelAttributes according to a partial title
     *
     * @param partialTitle The partial title for filtering
     * @param attributeClass the node class on which the query should be executed
     * @return a list of DecisionDocumentationModelAttributes filtered by the partialTitle, performed on the node Class
     */
    <T extends CoreData> Collection<T> findAllByTitle(String partialTitle, Class<T> attributeClass);

    /**
     * Loading a specific DecisionDocumentationModel Attribute (e.g. Quality Attribute)
     * @param id Which AP Attribute
     * @param attributeClass What Class (Type)
     * @param <T> Type identifier
     * @return A single CoreData given by the id
     */
    <T extends CoreData> T findById(Long id, Class<T> attributeClass);

    /**
     * Loading a specific DecisionDocumentationModel Attribute (e.g. Quality Attribute)
     * @param decisionDocumentationModelAttribute What Attribute to save
     * @param <T> Type identifier
     * @return A single CoreData given by the id
     */
    <T extends CoreData> T save(T decisionDocumentationModelAttribute);
}
