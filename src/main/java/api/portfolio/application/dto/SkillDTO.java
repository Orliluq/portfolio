package api.portfolio.application.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Setter
@Getter
public class SkillDTO {

    private Long id;

    @NotBlank(message = "Skill name cannot be empty")
    @Size(min = 3, max = 100, message = "Skill name must be between 3 and 100 characters")
    private String skillName;

    @NotBlank(message = "Proficiency level cannot be empty")
    private String proficiency;
}