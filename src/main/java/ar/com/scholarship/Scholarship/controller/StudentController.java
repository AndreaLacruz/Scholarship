package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.SocioEconomicStatusDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentDTO;
import ar.com.scholarship.Scholarship.service.SocioEconomicStatusServices;
import ar.com.scholarship.Scholarship.service.StudentServices;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    @Qualifier("studentServices")
    private StudentServices studentServices;

    @Autowired
    @Qualifier("socioEconomicStatusServices")
    private SocioEconomicStatusServices socioEconomicStatusServices;

    //STUDENT

    @PostMapping({"/students", "/students/"}) // http://localhost:8080/students
    public ResponseEntity addNewStudent(@Valid @RequestBody StudentDTO studentDTO) throws URISyntaxException {
        StudentDTO studentDTOSaved = studentServices.save(studentDTO);
        return ResponseEntity.created( new URI("/students/" + studentDTOSaved.getId()))
                .body(studentDTOSaved);
    }

    @GetMapping({"/students", "/students/"}) // http://localhost:8080/students
    public ResponseEntity getAllStudents(){
        List<StudentDTO> all = studentServices.findAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping({"/students/{id}", "/students/{id}/"}) // http://localhost:8080/students/1
    public ResponseEntity deleteStudent(Long id){
        studentServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping({"/students/{id}", "/students/{id}/"}) // http://localhost:8080/students/1
    public ResponseEntity updateStudent(@Valid @RequestBody StudentDTO studentDTO, Long id){
        StudentDTO studentUpdate = studentServices.update(studentDTO,id);
        return ResponseEntity.ok(studentUpdate);
    }

    // SOCIO ECONOMIC STATUS

    @SneakyThrows
    @PostMapping({"/socioEconomicStatus", "/socioEconomicStatus/"}) // http://localhost:8080/socioEconomicStatus
    public ResponseEntity addNewSocioEconomicStatus(@Valid @RequestBody SocioEconomicStatusDTO socioEconomicStatusDTO) throws URISyntaxException {
        SocioEconomicStatusDTO socioEconomicStatusDTOSaved = socioEconomicStatusServices.save(socioEconomicStatusDTO);
        return ResponseEntity
                .created(new URI("/socioEconomicStatus" + socioEconomicStatusDTOSaved.getId()))
                .body(socioEconomicStatusDTOSaved);
    }

    @DeleteMapping({"/socioEconomicStatus/{id}", "/socioEconomicStatus/{id}/"}) // http://localhost:8080/socioEconomicStatus/1
    public ResponseEntity deleteSocioEconomicStatus(Long id){
        socioEconomicStatusServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}

