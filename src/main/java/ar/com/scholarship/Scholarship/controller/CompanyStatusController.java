package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CompanyStatusDTO;
import ar.com.scholarship.Scholarship.service.CompanyStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/companystatus"})
public class CompanyStatusController {

    @Autowired
    @Qualifier("companyStatusServices")
    private CompanyStatusServices companyStatusServices;

    @GetMapping({"","/"}) //http://localhost:8080/companystatus
    public ResponseEntity getAllStatus(){
        List<CompanyStatusDTO> companyStatusDTOList = companyStatusServices.findAll();
        return ResponseEntity.ok(companyStatusDTOList);
    }
}
