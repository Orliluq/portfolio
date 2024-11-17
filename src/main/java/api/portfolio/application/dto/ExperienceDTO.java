package api.portfolio.application.dto;

import api.portfolio.domain.enums.ExperienceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExperienceDTO {
    private Long id;
    private String company;
    private String position;
    private Date startDate;
    private Date endDate;
    private String description;
    private Long userId;
    private Long portfolioId;
    private ExperienceType type;
}