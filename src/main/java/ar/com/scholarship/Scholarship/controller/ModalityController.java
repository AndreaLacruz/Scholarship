package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.ModalityDTO;
import ar.com.scholarship.Scholarship.service.ModalityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/modality"})
public class ModalityController {

    @Autowired
    @Qualifier("modalityServices")
    private ModalityServices modalityServices;

    @GetMapping({"", "/"}) //http://localhost:8080/modality //http://localhost:8080/modality/
    public ResponseEntity getAllModalities(){
        List<ModalityDTO> modalityDTOList = modalityServices.findAll();
        return ResponseEntity.ok(modalityDTOList);
    }
}
