package at.archkb.server.neo4jentity.dto.decisionguidance;

import at.archkb.server.neo4jentity.node.DecisionGuidanceModel;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@QueryResult
public class DecisionGuidanceModelPageableDto {

    private List<DecisionGuidanceModelDto> decisionGuidanceModels;

    private Long countTotal;

    public DecisionGuidanceModelPageableDto(List<DecisionGuidanceModel> decisionGuidanceModels, Long countTotal) {
        super();

        this.decisionGuidanceModels = new ArrayList<>();

        decisionGuidanceModels.forEach(dgm -> {
            this.decisionGuidanceModels.add(new DecisionGuidanceModelDto(dgm));
        });

        this.countTotal = countTotal;
    }

    public DecisionGuidanceModelPageableDto(Long countTotal, List<DecisionGuidanceModelDto> decisionGuidanceModels) {
        super();
        this.decisionGuidanceModels = decisionGuidanceModels;
        this.countTotal = countTotal;
    }

    public DecisionGuidanceModelPageableDto() {
        super();
    }

    public List<DecisionGuidanceModelDto> getDecisionGuidanceModels() {
        return decisionGuidanceModels;
    }

    public void setDecisionGuidanceModels(List<DecisionGuidanceModelDto> decisionGuidanceModels) {
        this.decisionGuidanceModels = decisionGuidanceModels;
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
