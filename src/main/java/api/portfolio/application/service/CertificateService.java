package api.portfolio.application.service;

import api.portfolio.application.dto.CertificateDTO;
import api.portfolio.application.mapper.PortfolioMapper;
import api.portfolio.domain.model.Certificate;
import api.portfolio.infrastructure.repository.CertificateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final PortfolioMapper portfolioMapper;

    public List<CertificateDTO> getAllCertificates() {
        return certificateRepository.findAll().stream()
                .map(portfolioMapper::certificateToCertificateDTO)
                .toList();
    }

    public CertificateDTO createCertificate(CertificateDTO certificateDTO) {
        Certificate certificate = portfolioMapper.certificateDTOToCertificate(certificateDTO);
        Certificate savedCertificate = certificateRepository.save(certificate);
        return portfolioMapper.certificateToCertificateDTO(savedCertificate);
    }

    public CertificateDTO updateCertificate(Long id, CertificateDTO certificateDTO) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found with id " + id));
        certificate.setCertificates(certificateDTO.getCertificates());
        certificate.setYear(certificateDTO.getYear());

        Certificate updatedCertificate = certificateRepository.save(certificate);
        return portfolioMapper.certificateToCertificateDTO(updatedCertificate);
    }

    public void deleteCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found with id " + id));

        certificateRepository.delete(certificate);
    }
}