package api.portfolio.application.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CertificateDTO {
    @Id
    private Long id;
    private String certificates;
    private int year;
}

