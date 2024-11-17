package api.portfolio.infrastructure.controller;

import api.portfolio.application.dto.EducationDTO;
import api.portfolio.application.service.EducationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/educations")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAllEducations() {
        List<EducationDTO> educations = educationService.getAllEducations();
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDTO> getEducationById(@PathVariable Long id) {
        Optional<EducationDTO> education = educationService.getEducationById(id);
        return education.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<EducationDTO> getEducationsByUserId(@PathVariable Long userId) {
        return educationService.getEducationsByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<EducationDTO> createEducation(@Valid @RequestBody EducationDTO educationDTO) {
        EducationDTO createdEducation = educationService.createEducation(educationDTO);
        return ResponseEntity.ok(createdEducation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationDTO> updateEducation(
            @PathVariable Long id,
            @RequestBody EducationDTO educationDTO) {
        Optional<EducationDTO> updatedEducation = educationService.updateEducation(id, educationDTO);
        return updatedEducation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}