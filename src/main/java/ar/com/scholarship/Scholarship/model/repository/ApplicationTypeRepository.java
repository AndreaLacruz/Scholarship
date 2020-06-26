package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component("applicationTypeRepository")
public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Application_Type AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
