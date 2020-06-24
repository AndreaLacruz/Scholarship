package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentHasCourseDTO;
import ar.com.scholarship.Scholarship.service.CourseServices;
import ar.com.scholarship.Scholarship.service.StudentHasCourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/browse")
public class BrowserController {

    @Autowired
    @Qualifier("courseServices")
    private CourseServices courseServices;

    @Autowired
    @Qualifier("studentHasCourseServices")
    private StudentHasCourseServices studentHasCourseServices;

    @GetMapping({"/category/{categoryId}", "/category/{categoryId}/"}) // http://localhost:8080/browse/category/1
    public ResponseEntity getCourseByCategory(@PathVariable Long categoryId){
        List<CourseDTO> allCourses = courseServices.findByCategory(categoryId);
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping({"/companies/{companyId}", "/companies/{companyId}/"}) // http://localhost:8080/browse/companies/1
    public ResponseEntity getCoursesByCompany(@PathVariable Long companyId){
        List<CourseDTO> allCourses = courseServices.findByCompany(companyId);
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping({"/category/{categoryId}/company/{companyId}, /courses/category/{categoryId}/company/{companyId}/"})
    // http://localhost:8080/browse/courses/category/1/company/1
    public ResponseEntity getCoursesByCompanyAndCategory(@PathVariable Long companyId, @PathVariable Long courseCategoryId){
        List<CourseDTO> allCourses = courseServices.findByCompanyAndCategory(companyId, courseCategoryId);
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping({"/studentStatus/{studentStatusId}, /studentStatus/{studentStatusId}/"}) // http://localhost:8080/browse/studentStatus/1
    public ResponseEntity getCoursesByStudentStatus(@PathVariable Long studentStatusId){
        List<StudentHasCourseDTO> studentHasCourseDTOList = studentHasCourseServices.findByStudentStatus(studentStatusId);
        return  ResponseEntity.ok(studentHasCourseDTOList);
    }

    @GetMapping({"/openCourses", "/openCourses/"}) // http://localhost:8080/browse/openCourses/
    public ResponseEntity getOpenPlacesCourses(@Valid @RequestBody CourseDTO courseDTO){
        List<CourseDTO> courseDTOList = courseServices.findByAvailablePlaces(courseDTO);
        return ResponseEntity.ok(courseDTOList);
    }

}
