package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.StudentHasCourse;
import ar.com.scholarship.Scholarship.model.entity.StudentHasCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component("studentHasCourseRepository")
public interface StudentHasCourseRepository extends JpaRepository<StudentHasCourse, StudentHasCourseId> {

}
