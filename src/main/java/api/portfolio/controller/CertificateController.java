package api.portfolio.controller;

import api.portfolio.dto.CertificateDTO;
import api.portfolio.service.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public ResponseEntity<List<CertificateDTO>> getAllCertificates() {
        List<CertificateDTO> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
    }

    @PostMapping
    public ResponseEntity<CertificateDTO> createCertificate(@Valid @RequestBody CertificateDTO certificateDTO) {
        CertificateDTO createdCertificate = certificateService.createCertificate(certificateDTO);
        return ResponseEntity.status(201).body(createdCertificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDTO> updateCertificate(@PathVariable Long id, @Valid @RequestBody CertificateDTO certificateDTO) {
        CertificateDTO updatedCertificate = certificateService.updateCertificate(id, certificateDTO);
        return ResponseEntity.ok(updatedCertificate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }
}
