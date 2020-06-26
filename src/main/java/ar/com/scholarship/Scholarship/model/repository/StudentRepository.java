package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Student AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();

    Optional<Student> findByLastName(String lastName);
}
