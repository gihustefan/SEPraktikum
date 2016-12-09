package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@QueryResult
public class DecisionDocumentationModelPageableDto {

    private List<DecisionDocumentationModelDto> decisionDocumentationModels;

    private Long countTotal;

    public DecisionDocumentationModelPageableDto(List<DecisionDocumentationModel> decisionDocumentationModels, Long countTotal) {
        super();

        this.decisionDocumentationModels = new ArrayList<>();

        decisionDocumentationModels.forEach(ddm -> {
            this.decisionDocumentationModels.add(new DecisionDocumentationModelDto(ddm));
        });

        this.countTotal = countTotal;
    }

    public DecisionDocumentationModelPageableDto(Long countTotal, List<DecisionDocumentationModelDto> decisionDocumentationModels) {
        super();
        this.decisionDocumentationModels = decisionDocumentationModels;
        this.countTotal = countTotal;
    }

    public DecisionDocumentationModelPageableDto() {
        super();
    }

    public List<DecisionDocumentationModelDto> getDecisionDocumentationModels() {
        return decisionDocumentationModels;
    }

    public void setDecisionDocumentationModels(List<DecisionDocumentationModelDto> decisionDocumentationModels) {
        this.decisionDocumentationModels = decisionDocumentationModels;
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
