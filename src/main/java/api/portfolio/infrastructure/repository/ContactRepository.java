package api.portfolio.infrastructure.repository;

import api.portfolio.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmail(String email);
}
