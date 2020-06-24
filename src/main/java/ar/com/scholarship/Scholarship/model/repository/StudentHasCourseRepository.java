package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.StudentHasCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("studentHasCourseRepository")
public interface StudentHasCourseRepository extends JpaRepository<StudentHasCourse, Long> {

    //TODO el status student
    @Query(value = "SELECT * FROM StudentStatus s INNER JOIN StudentHasCourse sc ON s.id = sc.Student_id WHERE studentStatus_id = :studentStatusId", nativeQuery = true)
    List<StudentHasCourse> findCourseByStudentStatus(@Param("studentStatusId") Long studentStatusId);
    }
