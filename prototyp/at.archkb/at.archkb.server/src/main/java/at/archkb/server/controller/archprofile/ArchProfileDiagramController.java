package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.DiagramDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Rainer on 31.05.2016.
 */
public interface ArchProfileDiagramController {

    DiagramDto createDiagram(Long idArchProfile, MultipartFile file) throws IOException;

    DiagramDto updateDiagram(Long idArchProfile, Long idDiagram, DiagramDto newValues);

    void deleteDiagram(Long idArchProfile, Long idDiagram);
}
