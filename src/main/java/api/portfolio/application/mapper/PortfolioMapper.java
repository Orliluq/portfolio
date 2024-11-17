package api.portfolio.application.mapper;

import api.portfolio.application.dto.*;
import api.portfolio.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PortfolioMapper {
    PortfolioMapper INSTANCE = Mappers.getMapper(PortfolioMapper.class);
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    ProjectDTO projectToProjectDTO(Project project);
    Project projectDTOToProject(ProjectDTO projectDTO);

    ExperienceDTO experienceToExperienceDTO(Experience experience);
    Experience experienceDTOToExperience(ExperienceDTO experienceDTO);

    EducationDTO educationToEducationDTO(Education education);
    Education educationDTOToEducation(EducationDTO educationDTO);

    SkillDTO skillToSkillDTO(Skill skill);
    Skill skillDTOToSkill(SkillDTO skillDTO);

    LanguageDTO languageToLanguageDTO(Language language);
    Language languageDTOToLanguage(LanguageDTO languageDTO);

    CertificateDTO certificateToCertificateDTO(Certificate certificate);
    Certificate certificateDTOToCertificate(CertificateDTO certificateDTO);

    ContactDTO contactToContactDTO(Contact contact);
    Contact contactDTOToContact(ContactDTO contactDTO);
}