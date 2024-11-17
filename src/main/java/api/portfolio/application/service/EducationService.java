package api.portfolio.application.service;

import api.portfolio.application.dto.EducationDTO;
import api.portfolio.domain.exception.ResourceNotFoundException;
import api.portfolio.application.mapper.PortfolioMapper;
import api.portfolio.domain.model.Education;
import api.portfolio.domain.model.User;
import api.portfolio.infrastructure.repository.EducationRepository;
import api.portfolio.infrastructure.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final PortfolioMapper portfolioMapper;
    private final UserRepository userRepository;

    public List<EducationDTO> getAllEducations() {
        return educationRepository.findAll().stream()
                .map(portfolioMapper::educationToEducationDTO)
                .toList();
    }

    public Optional<EducationDTO> getEducationById(Long educationId) {
        return educationRepository.findById(educationId)
                .map(portfolioMapper::educationToEducationDTO);
    }

    public List<EducationDTO> getEducationsByUserId(Long userId) {
        List<Education> educations = educationRepository.findByUserId(userId);
        return educations.stream()
                .map(portfolioMapper::educationToEducationDTO)
                .toList();
    }

    public EducationDTO createEducation(@Valid EducationDTO educationDTO) {
        User user = userRepository.findById(educationDTO.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + educationDTO.getUser().getId()));

        Education education = portfolioMapper.educationDTOToEducation(educationDTO);
        education.setUser(user);

        Education savedEducation = educationRepository.save(education);
        return portfolioMapper.educationToEducationDTO(savedEducation);
    }

    public Optional<EducationDTO> updateEducation(Long educationId, EducationDTO educationDTO) {
        return educationRepository.findById(educationId).map(existingEducation -> {
            existingEducation.setInstitution(educationDTO.getInstitution());
            existingEducation.setDegree(educationDTO.getDegree());
            existingEducation.setStartDate(educationDTO.getStartDate());
            existingEducation.setEndDate(educationDTO.getEndDate());
            existingEducation.setDescription(educationDTO.getDescription());
            Education updatedEducation = educationRepository.save(existingEducation);
            return portfolioMapper.educationToEducationDTO(updatedEducation);
        });
    }

    public void deleteEducation(Long educationId) {
        educationRepository.deleteById(educationId);
    }
}
