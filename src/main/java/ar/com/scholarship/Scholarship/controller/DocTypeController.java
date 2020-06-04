package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.DocTypeDTO;
import ar.com.scholarship.Scholarship.service.DocTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/doctype"})
public class DocTypeController {

    @Autowired @Qualifier("docTypeServices")
    private DocTypeServices docTypeServices;

    @GetMapping({"", "/"})  //localhost:8080/doctype localhost:8080/doctype/
    public ResponseEntity getAllTypes(){
        List<DocTypeDTO> allType = docTypeServices.findAll();
        return ResponseEntity.ok(allType);
    }
}
