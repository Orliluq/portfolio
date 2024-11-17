package api.portfolio.application.service;

import api.portfolio.application.dto.ExperienceDTO;
import api.portfolio.application.mapper.PortfolioMapper;
import api.portfolio.domain.model.Experience;
import api.portfolio.infrastructure.repository.ExperienceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ExperienceDTO createExperience(@Valid @RequestBody ExperienceDTO experienceDTO) {
        if (experienceDTO.getType() == null) {
            throw new IllegalArgumentException("Experience type is required");
        }
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
            existingExperience.setType(experienceDTO.getType());
            Experience updatedExperience = experienceRepository.save(existingExperience);
            return portfolioMapper.experienceToExperienceDTO(updatedExperience);
        });
    }

    public void deleteExperience(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }
}