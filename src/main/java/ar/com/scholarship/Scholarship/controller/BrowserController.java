package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentHasCourseDTO;
import ar.com.scholarship.Scholarship.service.CourseServices;
import ar.com.scholarship.Scholarship.service.StudentHasCourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/company/{companyId/category/{categoryId}", "/company/{companyId/category/{categoryId}"})
    // http://localhost:8080/browse/company/1/category/1
    public ResponseEntity getCoursesByCompanyAndCategory(@PathVariable Long companyId, @PathVariable Long courseCategoryId){
        List<CourseDTO> allCourses = courseServices.findByCompanyAndCategory(companyId, courseCategoryId);
        return ResponseEntity.ok(allCourses);
    }

    // TODO hacer service
    @GetMapping({"/studentStatus/{studentStatusId}", "/studentStatus/{studentStatusId}/"}) // http://localhost:8080/browse/studentStatus/1
    public ResponseEntity getCoursesByStudentStatus(@PathVariable Long studentStatusId){
        List<StudentHasCourseDTO> studentHasCourseDTOList = studentHasCourseServices.findByStudentStatus(studentStatusId);
        return  ResponseEntity.ok(studentHasCourseDTOList);
    }

    // TODO revisar
    @GetMapping({"/openCourses", "/openCourses/"}) // http://localhost:8080/browse/openCourses/
    public ResponseEntity getOpenPlacesCourses(){
        List<CourseDTO> courseDTOList = courseServices.findByAvailablePlaces();
        return ResponseEntity.ok(courseDTOList);
    }

}
