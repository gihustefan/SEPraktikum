package at.decisionexpert.repository.node.decisionguidance;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.Node;

import java.util.Collection;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
public interface DecisionGuidanceModelAttributeRepository {

    /**
     * Loading DecisionGuidanceModels according to a partial title
     *
     * @param partialTitle The partial title for filtering
     * @return a list of DecisionGuidanceModels filtered by the partialTitle, performed on the node Class
     */
    <T extends Node> Collection<T> findDGMByTitle(String partialTitle, Class<T> attributeClass);

    /**
     * Loading a specific DecisionGuidanceModel according to a Id
     *
     * @param id The id for filtering
     * @return a single Decision Guidance Model
     */
    <T extends Node> T findDGMById(Long id, Class<T> attributeClass);

    /**
     * Loading DecisionGuidanceModelAttributes according to a partial title
     *
     * @param partialTitle The partial title for filtering
     * @param attributeClass the node class on which the query should be executed
     * @return a list of DecisionGuidanceModelAttributes filtered by the partialTitle, performed on the node Class
     */
    <T extends CoreData> Collection<T> findAllByTitle(String partialTitle, Class<T> attributeClass);

    /**
     * Loading a specific DecisionGuidanceModel Attribute (e.g. Quality Attribute)
     * @param id Which AP Attribute
     * @param attributeClass What Class (Type)
     * @param <T> Type identifier
     * @return A single CoreData given by the id
     */
    <T extends CoreData> T findById(Long id, Class<T> attributeClass);

    /**
     * Loading a specific DecisionGuidanceModel Attribute (e.g. Quality Attribute)
     * @param decisionGuidanceModelAttribute What Attribute to save
     * @param <T> Type identifier
     * @return A single CoreData given by the id
     */
    <T extends CoreData> T save(T decisionGuidanceModelAttribute);
}
