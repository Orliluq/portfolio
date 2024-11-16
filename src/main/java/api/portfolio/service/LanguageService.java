package api.portfolio.service;

import api.portfolio.dto.LanguageDTO;
import api.portfolio.exception.ResourceNotFoundException;
import api.portfolio.mapper.PortfolioMapper;
import api.portfolio.model.Language;
import api.portfolio.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final PortfolioMapper languageMapper;

    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(languageMapper::languageToLanguageDTO)
                .toList();
    }

    public LanguageDTO saveLanguage(LanguageDTO languageDTO) {
        Language language = languageMapper.languageDTOToLanguage(languageDTO);
        language.setId(null);

        Language savedLanguage = languageRepository.save(language);
        return languageMapper.languageToLanguageDTO(savedLanguage);
    }

    public LanguageDTO updateLanguage(Long id, LanguageDTO languageDTO) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id " + id));

        language.setLanguages(languageDTO.getLanguages());
        language.setLevel(languageDTO.getLevel());
        languageRepository.save(language);

        return languageToLanguageDTO(language);
    }

    public LanguageDTO languageToLanguageDTO(Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLanguages(language.getLanguages());
        languageDTO.setLevel(language.getLevel());
        return languageDTO;
    }

    public void deleteLanguage(Long id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id " + id));

        languageRepository.delete(language);
    }
}