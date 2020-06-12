package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);
}
