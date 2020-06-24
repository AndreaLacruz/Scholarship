package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.StudentHasCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("studentHasCourseRepository")
public interface StudentHasCourseRepository extends JpaRepository<StudentHasCourse, Long> {

    @Query(value = "SELECT c.* FROM Course c INNER JOIN StudentHasCourse sc WHERE sc.StudentStatus_id = 2 GROUP BY c.id", nativeQuery = true)
    List<StudentHasCourse> findCourseByStudentStatus(@Param("studentStatusId") Long studentStatusId);
    }
