package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.ApplicationTypeDTO;
import ar.com.scholarship.Scholarship.service.ApplicationTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/applicationType"})
public class ApplicationTypeController {

    @Autowired
    @Qualifier("applicationTypeServices")
    private ApplicationTypeServices applicationTypeServices;

    @GetMapping({"", "/"}) //http://localhost:8080/applicationtype
    public ResponseEntity getAllTypes(){
        List<ApplicationTypeDTO> applicationTypeDTOList = applicationTypeServices.findAll();
        return ResponseEntity.ok(applicationTypeDTOList);
    }
}
