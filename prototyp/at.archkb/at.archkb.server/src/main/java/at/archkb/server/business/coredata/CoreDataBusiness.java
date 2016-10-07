package at.archkb.server.business.coredata;

import at.archkb.server.neo4jentity.dto.APAttributeAdminDto;
import at.archkb.server.neo4jentity.dto.ApAttributeDetailAdminDto;
import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.Tradeoff;

import java.util.List;

public interface CoreDataBusiness {
    /**
     * Fetching the List of fitting CoreData Values
     *
     * @param titlePartial  filtered by a partial title
     * @param coreDataClass which core data class (e.g. Attribute). Needed for querying
     *                      the right node
     * @return A list of Core Data Values filtered by the partial title
     */
    <T extends CoreData> List<ArchProfileRelationDto> getCoreData(String titlePartial,
                                                                  Class<T> coreDataClass);

    Iterable<APAttributeAdminDto> getAllOfType(String type);

    ApAttributeDetailAdminDto getById(Long id);

    ApAttributeDetailAdminDto addToCore(Long id);

    ApAttributeDetailAdminDto removeFromCore(Long id);

    ApAttributeDetailAdminDto updateAPAttribute(ApAttributeDetailAdminDto apat);

    ApAttributeDetailAdminDto createAPAttribute(ApAttributeDetailAdminDto apat) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

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

    /**
     * Getting a Tradeoff by the given Tradeoff items -> if it is not found -> a new one will be created!
     *
     * @param idTradeoffItemOver  Which Tradeoffitem over
     * @param idTradeoffItemUnder Which Tradeoffitem under
     * @return The Tradeoff with the tradeoffitem over and under
     */
    Tradeoff getTradeoffByTradeoffItems(Long idTradeoffItemOver, Long idTradeoffItemUnder);
}
