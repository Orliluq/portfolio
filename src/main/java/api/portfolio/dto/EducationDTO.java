package api.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class EducationDTO {
    private Long id;
    private String institution;
    private String degree;
    private Date startDate;
    private Date endDate;
    private String description;
    private UserDTO user;

}