package api.portfolio.service;

import api.portfolio.dto.ExperienceDTO;
import api.portfolio.mapper.PortfolioMapper;
import api.portfolio.model.Experience;
import api.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final PortfolioMapper portfolioMapper;

    public List<ExperienceDTO> getAllExperiences() {
        return experienceRepository.findAll().stream()
                .map(portfolioMapper::experienceToExperienceDTO)
                .toList();
    }

    public Optional<ExperienceDTO> getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId)
                .map(portfolioMapper::experienceToExperienceDTO);
    }

    public List<ExperienceDTO> getExperiencesByUserId(Long userId) {
        List<Experience> experiences = experienceRepository.findByUserId(userId);
        return experiences.stream()
                .map(portfolioMapper::experienceToExperienceDTO)
                .toList();
    }

    public ExperienceDTO createExperience(ExperienceDTO experienceDTO) {
        Experience experience = portfolioMapper.experienceDTOToExperience(experienceDTO);
        Experience savedExperience = experienceRepository.save(experience);
        return portfolioMapper.experienceToExperienceDTO(savedExperience);
    }

    public Optional<ExperienceDTO> updateExperience(Long experienceId, ExperienceDTO experienceDTO) {
        return experienceRepository.findById(experienceId).map(existingExperience -> {
            existingExperience.setCompany(experienceDTO.getCompany());
            existingExperience.setPosition(experienceDTO.getPosition());
            existingExperience.setStartDate(experienceDTO.getStartDate());
            existingExperience.setEndDate(experienceDTO.getEndDate());
            existingExperience.setDescription(experienceDTO.getDescription());
            Experience updatedExperience = experienceRepository.save(existingExperience);
            return portfolioMapper.experienceToExperienceDTO(updatedExperience);
        });
    }

    public void deleteExperience(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }
}