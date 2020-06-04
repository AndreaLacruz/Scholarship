package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("studentStatusRepository")
public interface StudentStatusRepository extends JpaRepository<StudentStatus, Long> {

}
