package api.portfolio.dto;

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

}