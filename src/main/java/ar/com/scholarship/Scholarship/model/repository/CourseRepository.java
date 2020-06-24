package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM Course c WHERE c.course_category_id = :course_category_id", nativeQuery = true)
    List<Course> findByCategory(@Param("course_Category") Long categoryId);

    @Query(value = "SELECT * FROM Course c WHERE c.company = :company", nativeQuery = true)
    List<Course> findByCompany(@Param("company") Long companyId);

    // TODO buscar el query verdadero (buscar en curso los cupos mayores a 1)
    @Query(value = "SELECT places, COUNT(*) FROM Course GROUP BY places ORDER BY places LIMIT 1", nativeQuery = true)
    List<Course> findByAvailablePlaces();

    @Query(value = "SELECT * FROM Course co WHERE co.companyId = :companyId AND c = c.course_category_id")
    List<Course> findByCompanyAndCategory(@Param("companyId") Long companyId, @Param("courseCategoryId") Long categoryId);

    @Query(value = "SELECT * FROM Course c ORDER BY name", nativeQuery = true)
    List<Course> findByName(@Param("name") String name);

}
