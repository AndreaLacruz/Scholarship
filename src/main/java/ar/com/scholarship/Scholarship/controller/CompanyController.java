package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
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

@RestController
@RequestMapping({"/companies"})
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

    @PostMapping({"/companies", "/companies/"}) // http://localhost:8080/companies
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO, @PathVariable Long statusId,
                                        @PathVariable Long managerId){
        CompanyDTO companyDTOSaved = companyServices.save(companyDTO, managerId, statusId);
        return ResponseEntity
                .ok(companyDTOSaved);
    }

    @PostMapping({"/managers", "/managers/"}) // http://localhost:8080/managers
    public ResponseEntity addNewManager(@Valid @RequestBody ManagerDTO managerDTO) throws URISyntaxException{
        ManagerDTO managerDTOSaved = managerServices.save(managerDTO);
        return ResponseEntity.created(new URI("/managers/" + managerDTOSaved.getId()))
                .body(managerDTOSaved);
    }
}
