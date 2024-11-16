package api.portfolio.service;

import api.portfolio.dto.CertificateDTO;
import api.portfolio.mapper.PortfolioMapper;
import api.portfolio.model.Certificate;
import api.portfolio.repository.CertificateRepository;
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
        certificateRepository.save(certificate);
        return new CertificateDTO();
    }

    public void deleteCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found with id " + id));

        certificateRepository.delete(certificate);
    }

}