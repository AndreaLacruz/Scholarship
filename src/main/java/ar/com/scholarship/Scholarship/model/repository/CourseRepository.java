package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM Course c WHERE c.course_category_id = :courseCategoryId", nativeQuery = true)
    Page<Course> findByCategory(@Param("courseCategoryId") Long categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE c.company_id = :companyId", nativeQuery = true)
    Page<Course> findByCompany(@Param("companyId") Long companyId, Pageable pageable);

    @Query(value = "SELECT * FROM Course c WHERE c.openPlacesCounter > 0 OR c.scholarshipCounter > 0 ", nativeQuery = true)
    Page<Course> findByAvailablePlaces(Pageable pageable);

    @Query(value = "SELECT * FROM Course co WHERE co.company_id = :companyId AND co.course_category_id = :courseCategoryId", nativeQuery = true)
    Page<Course> findByCompanyAndCategory(@Param("companyId") Long companyId, @Param("courseCategoryId") Long categoryId, Pageable pageable);

    @Query(
            value = "SELECT c.* FROM Course c LEFT JOIN StudentHasCourse sc ON c.id = sc.course_id WHERE sc.StudentStatus_id = :studentStatusId GROUP BY c.id",
            countQuery = "SELECT DISTINCT COUNT(*) OVER() FROM Course c LEFT JOIN StudentHasCourse sc ON c.id = sc.course_id WHERE sc.StudentStatus_id = :studentStatusId GROUP BY c.id",
            nativeQuery = true)
    Page<Course> findAllCoursesByStudentStatusProgress(@Param("studentStatusId") Long studentStatusId, Pageable pageable);

}
