package api.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LanguageDTO {
    private String languages;
    private String level;
    private Long userId;
}