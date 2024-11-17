package api.portfolio.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectDTO {
    private Long id;
    private String projectName;
    private String description;
    private String url;
    private String image;

}