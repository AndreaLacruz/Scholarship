package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



@Component("companyStatusRepository")
public interface CompanyStatusRepository extends JpaRepository<CompanyStatus, Long> {
    //Optional<CompanyStatus> findByStatus(String status);
}
