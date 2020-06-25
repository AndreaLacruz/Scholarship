package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.model.dto.ManagerDTO;
import ar.com.scholarship.Scholarship.service.CompanyServices;
import ar.com.scholarship.Scholarship.service.CourseServices;
import ar.com.scholarship.Scholarship.service.ManagerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    @Qualifier("companyServices")
    private CompanyServices companyServices;

    @Autowired
    @Qualifier("managerServices")
    private ManagerServices managerServices;

    @Autowired
    @Qualifier("courseServices")
    private CourseServices courseServices;

    //COMPANY

    @PostMapping({"/companies", "/companies/"}) // http://localhost:8080/companies
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        CompanyDTO companyDTOSaved = companyServices.save(companyDTO);
        return ResponseEntity
                .created(new URI(("/companies/" + companyDTOSaved.getId())))
                .body(companyDTOSaved);
    }

    @GetMapping({"/companies","/companies/"})  // http://localhost:8080/companies
    public ResponseEntity getAllCompanies(){
        List<CompanyDTO> all = companyServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/companies/{id}", "/companies/{id}/"}) // http://localhost:8080/companies/1
    public ResponseEntity getCompanyById(@PathVariable Long id){
        CompanyDTO companyById = companyServices.findById(id);
        return ResponseEntity.ok(companyById);
    }

    @DeleteMapping({"/companies/{id}", "/companies/{id}/"}) // http://localhost:8080/companies/1
    public ResponseEntity deleteCompany(@PathVariable Long id){
        companyServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping({"/companies/{id}", "/companies/{id}/"}) // http://localhost:8080/companies/1
    public ResponseEntity updateCompany(@Valid @RequestBody CompanyDTO companyDTO, Long id){
        CompanyDTO companyUpdated = companyServices.update(companyDTO, id);
        return ResponseEntity.ok(companyUpdated);
    }

    //MANAGER

    @PostMapping({"/managers", "/managers/"}) // http://localhost:8080/managers
    public ResponseEntity addNewManager(@Valid @RequestBody ManagerDTO managerDTO) throws URISyntaxException{
        ManagerDTO managerDTOSaved = managerServices.save(managerDTO);
        return ResponseEntity.created(new URI("/managers/" + managerDTOSaved.getId()))
                .body(managerDTOSaved);
    }

    @GetMapping({"managers", "/managers/"}) // http://localhost:8080/managers
    public ResponseEntity getAllManagers(){
        List<ManagerDTO> all = managerServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/managers/{documentation}", "/managers/{documentation}/"}) // http://localhost:8080/managers/1
    public ResponseEntity findManagerByDni(@PathVariable Integer documentation){
        ManagerDTO managerDTO = managerServices.findManagerByDocumentation(documentation);
        return ResponseEntity.ok(managerDTO);
    }

    @DeleteMapping({"/managers/{id}", "/managers/{id}/"}) // http://localhost:8080/managers/1
    public ResponseEntity deleteManager(@PathVariable Long id){
        managerServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping({"/managers/{id}", "/managers/{id}/"}) // http://localhost:8080/managers/1
    public ResponseEntity updateManager(@Valid @RequestBody ManagerDTO managerDTO, Long id){
        ManagerDTO managerUpdated = managerServices.update(managerDTO, id);
        return ResponseEntity.ok(managerUpdated);
    }

    //COURSES

    @PostMapping({"/courses", "/courses/"}) // http://localhost:8080/courses
    public ResponseEntity addNewCourse(@Valid @RequestBody CourseDTO courseDTO) throws URISyntaxException{
        CourseDTO courseDTOSaved = courseServices.save(courseDTO);
        return ResponseEntity
                .created(new URI("/courses/" + courseDTOSaved.getId()))
                .body(courseDTOSaved);
    }

    @GetMapping({"/courses", "/courses/"}) // http://localhost:8080/courses
    public ResponseEntity getAllCourses(){
        List<CourseDTO> all = courseServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/courses/{name}", "/courses/{name}/"}) // http://localhost:8080/managers/name
    public ResponseEntity findCourseByName(@PathVariable String name){
        CourseDTO courseDTO = courseServices.findByName(name);
        return ResponseEntity.ok(courseDTO);
    }

    @DeleteMapping({"/courses/{id}", "/courses/{id}/"}) // http://localhost:8080/courses/1
    public ResponseEntity deleteCourses(@PathVariable Long id){
        courseServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping({"/courses/{id}", "/courses/{id}/"}) // http://localhost:8080/courses/1
    public ResponseEntity updateCourse(@Valid @RequestBody CourseDTO courseDTO, Long id){
        CourseDTO courseUpdated = courseServices.update(courseDTO, id);
        return ResponseEntity.ok(courseUpdated);
    }
}

