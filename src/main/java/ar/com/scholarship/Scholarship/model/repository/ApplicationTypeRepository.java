package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("applicationTypeRepository")
public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Long> {

}
