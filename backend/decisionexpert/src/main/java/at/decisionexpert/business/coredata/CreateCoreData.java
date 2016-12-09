package at.decisionexpert.business.coredata;

import at.decisionexpert.neo4jentity.node.CoreData;

/**
 * Created by stefanhaselboeck on 11.10.16.
 */
public interface CreateCoreData {

    /**
     * Fetching the List of fitting CoreData Values
     *
     * @param title         The title of the new core data
     * @param definition    the definition for the new core data
     * @param coreDataClass which core data class (e.g. Attribute). Needed for querying
     *                      the right node
     * @return A list of Core Data Values filtered by the partial title
     */
    <T extends CoreData> T createCoreData(String title, String definition,
                                          Class<T> coreDataClass);
}
