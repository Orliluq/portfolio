package api.portfolio.controller;

import api.portfolio.dto.SkillDTO;
import api.portfolio.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable("id") Long id) {
        Optional<SkillDTO> skill = skillService.getSkillById(id);
        return skill.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillDTO>> findByUserId(@PathVariable("userId") Long userId) {
        List<SkillDTO> skills = skillService.getSkillsByUserId(userId);
        return ResponseEntity.ok(skills);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillDTO createSkill(@Valid @RequestBody SkillDTO skillDTO) {
        return skillService.createSkill(skillDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable("id") Long id,
            @Valid @RequestBody SkillDTO skillDTO) {
        Optional<SkillDTO> updatedSkill = skillService.updateSkill(id, skillDTO);
        return updatedSkill.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(@PathVariable("id") Long id) {
        skillService.deleteSkill(id);
    }
}