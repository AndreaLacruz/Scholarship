package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.SocioEconomicStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component("socioEconomicStatusRepository")
public interface SocioEconomicStatusRepository extends JpaRepository<SocioEconomicStatus, Long> {
    @Modifying
    @Query(value = "ALTER TABLE Socio_Economic_Status AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
