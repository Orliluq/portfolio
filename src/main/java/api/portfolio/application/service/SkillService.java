package api.portfolio.application.service;

import api.portfolio.application.dto.SkillDTO;
import api.portfolio.domain.exception.ResourceNotFoundException;
import api.portfolio.application.mapper.PortfolioMapper;
import api.portfolio.domain.model.Skill;
import api.portfolio.infrastructure.repository.SkillRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final PortfolioMapper portfolioMapper;

    @Autowired
    public SkillService(SkillRepository skillRepository, PortfolioMapper portfolioMapper) {
        this.skillRepository = skillRepository;
        this.portfolioMapper = portfolioMapper;
    }

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(portfolioMapper::skillToSkillDTO)
                .toList();
    }

    public List<SkillDTO> getSkillsByUserId(Long userId) {
        List<Skill> skills = skillRepository.findByUserId(userId);
        if (skills.isEmpty()) {
            throw new ResourceNotFoundException("No skills found for user with id: " + userId);
        }
        return skills.stream()
                .map(portfolioMapper::skillToSkillDTO)
                .toList();
    }

    public Optional<SkillDTO> getSkillById(Long skillId) {
        return skillRepository.findById(skillId)
                .map(portfolioMapper::skillToSkillDTO);
    }

    public SkillDTO createSkill(@Valid SkillDTO skillDTO) {
        Skill skill = portfolioMapper.skillDTOToSkill(skillDTO);
        Skill savedSkill = skillRepository.save(skill);
        return portfolioMapper.skillToSkillDTO(savedSkill);
    }

    public Optional<SkillDTO> updateSkill(Long skillId, SkillDTO skillDTO) {
        Optional<Skill> existingSkillOptional = skillRepository.findById(skillId);
        if (existingSkillOptional.isEmpty()) {
            return Optional.empty();
        }

        Skill existingSkill = existingSkillOptional.get();
        existingSkill.setSkillName(skillDTO.getSkillName());
        existingSkill.setProficiency(skillDTO.getProficiency());
        Skill updatedSkill = skillRepository.save(existingSkill);
        return Optional.of(portfolioMapper.skillToSkillDTO(updatedSkill));
    }


    public void deleteSkill(Long skillId) {
        skillRepository.deleteById(skillId);
    }
}