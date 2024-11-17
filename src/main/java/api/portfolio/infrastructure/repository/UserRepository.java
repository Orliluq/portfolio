package api.portfolio.infrastructure.repository;

import api.portfolio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}