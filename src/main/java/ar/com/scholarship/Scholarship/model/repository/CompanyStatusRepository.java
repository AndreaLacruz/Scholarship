package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component("companyStatusRepository")
public interface CompanyStatusRepository extends JpaRepository<CompanyStatus, Long> {

    @Modifying
    @Query(value = "ALTER TABLE TypeCategoryCompany AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
