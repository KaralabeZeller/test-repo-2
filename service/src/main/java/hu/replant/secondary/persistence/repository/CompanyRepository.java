package hu.replant.secondary.persistence.repository;

import hu.replant.secondary.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
