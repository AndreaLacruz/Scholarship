package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByLastName(String lastName);
}
