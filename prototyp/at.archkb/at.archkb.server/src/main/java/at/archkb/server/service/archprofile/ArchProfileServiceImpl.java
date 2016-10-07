package at.archkb.server.service.archprofile;

import at.archkb.server.business.archprofile.ArchProfileBusiness;
import at.archkb.server.neo4jentity.dto.*;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;
import at.archkb.server.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Service
public class ArchProfileServiceImpl implements ArchProfileService {

    @Autowired
    private ArchProfileBusiness archProfileBusiness;

    @Override
    public ArchProfileDto getArchProfile(Long id) {
        return archProfileBusiness.getArchProfile(id);
    }

    @Override
    public ArchProfileDto createArchProfile(ArchProfileChangeRequestDto archProfile) {
        return archProfileBusiness.createArchProfile(archProfile);
    }

    @Override
    public void deleteArchProfile(Long id) {
        archProfileBusiness.deleteArchProfile(id);
    }

    @Override
    public ArchProfileDto updateArchProfileProperties(Long id, ArchProfileChangeRequestDto newValues) {
        return archProfileBusiness.updateArchProfileProperties(id, newValues);
    }

    @Override
    public <T extends APAttributeRelationship<? extends CoreData>> ArchProfileRelationDto updateExistingRelationAttribute(@NotNull Long idArchProfile, @NotNull Long idArchProfileRelation, ArchProfileRelationDto newValues, Class<T> clazz) {
        return archProfileBusiness.updateExistingRelationAttribute(idArchProfile, idArchProfileRelation, newValues, clazz);
    }

    @Override
    public <T extends APAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idArchProfile, Long idArchProfileRelation, Class<T> relationClass) {
        archProfileBusiness.deleteRelationAttribute(idArchProfile, idArchProfileRelation, relationClass);
    }

    @Override
    public <T extends APAttributeRelationship<A>, A extends CoreData> ArchProfileRelationDto createRelation(@NotNull Long idArchProfile, ArchProfileRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        return archProfileBusiness.createRelation(idArchProfile, attributeInfo, relationClass, toNodeType);
    }

    @Override
    public DiagramDto createDiagram(@NotNull Long idArchProfile, @NotNull MultipartFile imageFile) {
        // Only supporting jpeg or png files
        if (imageFile.getContentType().equals("image/jpeg") || imageFile.getContentType().equals("image/png"))
            return archProfileBusiness.createDiagram(idArchProfile, imageFile);

        return null;
    }

    @Override
    public DiagramDto updateDiagram(@NotNull Long idArchProfile, @NotNull Long idDiagram, @NotNull DiagramDto newValues) {
        return archProfileBusiness.updateDiagram(idArchProfile, idDiagram, newValues);
    }

    @Override
    public void deleteDiagram(@NotNull Long idArchProfile, @NotNull Long idDiagram) {
        archProfileBusiness.deleteDiagram(idArchProfile, idDiagram);
    }

    @Override
    public ArchProfileTradeoffRelationDto updateExistingTradeoffRelationAttribute(@NotNull Long idArchProfile, @NotNull Long idArchProfileRelation, @NotNull ArchProfileTradeoffRelationDto newValues) {
        return archProfileBusiness.updateExistingTradeoffRelationAttribute(idArchProfile, idArchProfileRelation, newValues);
    }

    @Override
    public void deleteArchProfileTradeoffRelation(@NotNull Long idArchProfile, @NotNull Long idArchProfileTradeoffRelation) {
        archProfileBusiness.deleteArchProfileTradeoffRelation(idArchProfile, idArchProfileTradeoffRelation);
    }

    @Override
    public ArchProfileTradeoffRelationDto createTradeoffRelation(@NotNull Long idArchProfile, ArchProfileTradeoffRelationDto tradeoffInfo) {
        return archProfileBusiness.createTradeoffRelation(idArchProfile, tradeoffInfo);
    }

    @Override
    public ArchProfilePageableDto getNewestArchProfiles(Integer page, Integer size) {
        return archProfileBusiness.getNewestArchProfiles(page, size, SecurityUtils.hasRole("ROLE_ADMIN", SecurityContextHolder.getContext().getAuthentication()));
    }

    @Override
    public ArchProfilePageableDto getUserArchProfiles(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size) {
        return archProfileBusiness.getUserArchProfiles(idUser, page, size);
    }
}
