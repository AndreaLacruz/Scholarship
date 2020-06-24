package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.ApplicationCourseStudentDTO;
import ar.com.scholarship.Scholarship.model.dto.ApplicationTypeDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentHasCourseDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentStatusDTO;
import ar.com.scholarship.Scholarship.service.StudentHasCourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentHasCourseController {

    @Autowired
    @Qualifier("studentHasCourseServices")
    private StudentHasCourseServices studentHasCourseServices;

    // http://localhost:8080/student/1/course/1

    @PutMapping({"/student/{studentId}/course/{courseId}", "/student/{studentId}/course/{courseId}/"})
    public ResponseEntity addNewStudentToCourse(
            @Valid @RequestBody ApplicationCourseStudentDTO dto, @PathVariable Long studentId, @PathVariable Long courseId){
        StudentHasCourseDTO studentStatusDTOSaving = studentHasCourseServices.saveApplication(dto, studentId, courseId);
        return ResponseEntity.ok(studentStatusDTOSaving);
    }

}
