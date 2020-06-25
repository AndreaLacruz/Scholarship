package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.service.CourseServices;
import ar.com.scholarship.Scholarship.service.StudentHasCourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/browse")
public class BrowserController {

    @Autowired
    @Qualifier("courseServices")
    private CourseServices courseServices;

    @Autowired
    @Qualifier("studentHasCourseServices")
    private StudentHasCourseServices studentHasCourseServices;

    @GetMapping({"/category/{categoryId}", "/category/{categoryId}/"}) // http://localhost:8080/browse/category/1/1?page=0
    public ResponseEntity getCourseByCategory(@RequestParam Optional<Integer> page,@PathVariable Long categoryId){
        List<CourseDTO> allCourses = courseServices.findByCategory(categoryId, page.orElse(0));
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping({"/companies/{companyId}", "/companies/{companyId}/"}) // http://localhost:8080/browse/companies/1/1?page=0
    public ResponseEntity getCoursesByCompany(@RequestParam Optional<Integer> page,@PathVariable Long companyId){
        List<CourseDTO> allCourses = courseServices.findByCompany(companyId, page.orElse(0));
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping({"/company/{companyId/category/{categoryId}", "/company/{companyId/category/{categoryId}"})
    // http://localhost:8080/browse/company/1/category/1/1?page=0
    public ResponseEntity getCoursesByCompanyAndCategory(@RequestParam Optional<Integer> page, @PathVariable Long companyId, @PathVariable Long courseCategoryId){
        List<CourseDTO> allCourses = courseServices.findByCompanyAndCategory(companyId, courseCategoryId, page.orElse(0));
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping({"/openCourses", "/openCourses/"}) // http://localhost:8080/browse/openCourses/1?page=0
    public ResponseEntity getOpenPlacesCourses(@RequestParam Optional<Integer> page){
        List<CourseDTO> courseDTOList = courseServices.findByAvailablePlaces(page.orElse(0));
        return ResponseEntity.ok(courseDTOList);
    }

    // http://localhost:8080/browse/course/student-status-progress/1?page=0
    @GetMapping({"/course/student-status-progress/{studentStatusId}", "/course/student-status-progress/{studentStatusId}/"})
    public ResponseEntity studentProgressUpdate(@PathVariable Long studentStatusId, @RequestParam Optional<Integer> page){
        List<CourseDTO> coursesByStudentStatusProgress = courseServices.getAllCoursesByStudentStatusProgress(studentStatusId, page.orElse(0));
        return ResponseEntity.ok(coursesByStudentStatusProgress);
    }


}
