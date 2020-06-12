package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.SocioEconomicStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("socioEconomicStatusRepository")
public interface SocioEconomicStatusRepository extends JpaRepository<SocioEconomicStatus, Long> {
}
