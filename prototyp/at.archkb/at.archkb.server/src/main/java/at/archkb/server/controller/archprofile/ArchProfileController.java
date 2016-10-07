package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileChangeRequestDto;
import at.archkb.server.neo4jentity.dto.ArchProfileDto;
import at.archkb.server.neo4jentity.dto.ArchProfilePageableDto;

import java.util.List;

public interface ArchProfileController {

    /**
     * Create a completely new ArchProfile
     *
     * @param archProfile The ArchProfile Model where all the data is stored
     * @return The model persisted ArchProfile
     */
    ArchProfileDto createArchProfile(ArchProfileChangeRequestDto archProfile);

    /**
     * Receive related ArchProfiles by a given ArchProfile
     *
     * @param idArchProfile The id of the ArchProfile - we want other related ArchProfiles
     *                      for this ArchProfile
     * @param count         How many ArchProfiles (limit)
     * @return A list of Related ArchProfiles given by the idArchProfile
     */
    List<ArchProfileDto> getRelatedArchProfiles(Long idArchProfile, Integer count);

    /**
     * Retrieving paginated ArchProfiles
     *
     * @param page What page
     * @param size How many items in page
     * @param type What type (e.g. newest, featured, active, ...)
     * @return A List of ArchProfileDtos restricted on the given parameters
     */
    ArchProfilePageableDto getArchProfiles(Integer page, Integer size, ArchProfilesType type);

    /**
     * Receive a single model of an ArchProfile
     *
     * @param idArchProfile The ArchProfile Id of interest
     * @return The ArchProfile model of the ArchProfile - selected by
     * idArchProfile
     */
    ArchProfileDto getArchProfile(Long idArchProfile);

    /**
     * Updates the direct Attributes of a given ArchProfile (e.g. Title,
     * Description)
     *
     * @param idArchProfile Which ArchProfile to update
     * @param newValues     new values based on the model
     * @return The new Value for the attribute, after persisting
     */
    ArchProfileDto updateArchProfileAttribute(Long idArchProfile, ArchProfileChangeRequestDto newValues);

    /**
     * Deleting an ArchProfile
     *
     * @param idArchProfile Which ArchProfile
     */
    void deleteArchProfile(Long idArchProfile);

    enum ArchProfilesType {
        NEWEST, FEATURED, ACTIVE
    }
}
