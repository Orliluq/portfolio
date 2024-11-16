package api.portfolio.dto;

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