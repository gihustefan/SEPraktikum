package at.decisionexpert.repository.node.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.CoreData;

import java.util.Collection;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DesignOptionAttributeRepository {

    /**
     * Loading DesignOptionAttributes according to a partial title
     *
     * @param partialTitle The partial title for filtering
     * @param attributeClass the node class on which the query should be executed
     * @return a list of DesignOptionAttributes filtered by the partialTitle, performed on the node Class
     */
    <T extends CoreData> Collection<T> findAllByTitle(String partialTitle, Class<T> attributeClass);

    /**
     * Loading a specific DesignOption Attribute (e.g. Quality Attribute)
     * @param id Which AP Attribute
     * @param attributeClass What Class (Type)
     * @param <T> Type identifier
     * @return A single CoreData given by the id
     */
    <T extends CoreData> T findById(Long id, Class<T> attributeClass);

    /**
     * Loading a specific DesignOption Attribute (e.g. Quality Attribute)
     * @param designOptionAttribute What Attribute to save
     * @param <T> Type identifier
     * @return A single CoreData given by the id
     */
    <T extends CoreData> T save(T designOptionAttribute);
}
