package api.portfolio.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PortfolioDTO {

    private Long id;
    private String name;
    private String description;
    private List<Long> skillIds;
    private List<Long> projectIds;
    private List<Long> userIds;

}