package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Course AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();

    Optional<Course> findByName(String name);

    List<Course> findByCompany(String companyName);
}
