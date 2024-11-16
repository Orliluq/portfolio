package api.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Set<ProjectDTO> projects;
    private Set<ExperienceDTO> experiences;
    private Set<EducationDTO> educations;
    private Set<SkillDTO> skills;
    private Set<LanguageDTO> languages;
    private Set<CertificateDTO> certificates;
    private ContactDTO contact;


}