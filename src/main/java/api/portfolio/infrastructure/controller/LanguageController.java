package api.portfolio.infrastructure.controller;

import api.portfolio.application.dto.LanguageDTO;
import api.portfolio.application.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping
    public List<LanguageDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @PostMapping
    public LanguageDTO createLanguage(@Valid @RequestBody LanguageDTO languageDTO) {
        return languageService.saveLanguage(languageDTO);
    }

    @PutMapping("/{id}")
    public LanguageDTO updateLanguage(@PathVariable Long id, @Valid @RequestBody LanguageDTO languageDTO) {
        return languageService.updateLanguage(id, languageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
    }
}
