package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.StudentStatusDTO;
import ar.com.scholarship.Scholarship.service.StudentStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/studentstatus"})
public class StudentStatusController {

    @Autowired
    @Qualifier("studentStatusServices")
    private StudentStatusServices studentStatusServices;

    @GetMapping({"", "/"}) //http://localhost:8080/studentstatus
    public ResponseEntity getAllStatus(){
        List<StudentStatusDTO> statusDTOList = studentStatusServices.findAll();
        return ResponseEntity.ok(statusDTOList);
    }
}
