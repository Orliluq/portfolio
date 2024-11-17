package api.portfolio.infrastructure.controller;

import api.portfolio.application.dto.ExperienceDTO;
import api.portfolio.application.service.ExperienceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceDTO>> getAllExperiences() {
        List<ExperienceDTO> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> getExperienceById(@PathVariable Long id) {
        Optional<ExperienceDTO> experience = experienceService.getExperienceById(id);
        return experience.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<ExperienceDTO> getExperiencesByUserId(@PathVariable Long userId) {
        return experienceService.getExperiencesByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> createExperience(@Valid @RequestBody ExperienceDTO experienceDTO) {
        ExperienceDTO createdExperience = experienceService.createExperience(experienceDTO);
        return ResponseEntity.ok(createdExperience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDTO> updateExperience(
            @PathVariable Long id,
            @RequestBody ExperienceDTO experienceDTO) {
        Optional<ExperienceDTO> updatedExperience = experienceService.updateExperience(id, experienceDTO);
        return updatedExperience.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}