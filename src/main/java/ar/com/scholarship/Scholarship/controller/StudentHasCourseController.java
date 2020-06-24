package ar.com.scholarship.Scholarship.controller;

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

    @PutMapping({"/student/{studentId}/course/{courseId}/studentStatusId/{studentStatusId}/applicationTypeId/{applicationTypeId}",
            "/student/{studentId}/course/{courseId}/studentStatus/{studentStatusId}/applicationTypeId/{applicationTypeId}/"})
    //  localhost:8080/student/1/courses/1/studentStatus/1/applicationType/1/

    public ResponseEntity addNewStudentToCourse(
            @Valid @RequestBody StudentHasCourseDTO dto, @PathVariable Long studentId,
            @PathVariable Long courseId, @PathVariable Long studentStatusId, @PathVariable Long applicationTypeId){
        StudentHasCourseDTO studentStatusDTOSaving = studentHasCourseServices.saveApplication(
               dto ,applicationTypeId, studentId, courseId, studentStatusId);
        return ResponseEntity.ok(studentStatusDTOSaving);
    }

}
