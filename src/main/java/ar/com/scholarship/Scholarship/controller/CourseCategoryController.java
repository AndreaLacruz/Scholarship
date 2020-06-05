package ar.com.scholarship.Scholarship.controller;

import ar.com.scholarship.Scholarship.model.dto.CourseCategoryDTO;
import ar.com.scholarship.Scholarship.service.CourseCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/coursecategory"})
public class CourseCategoryController {

    @Autowired
    @Qualifier("courseCategoryServices")
    private CourseCategoryServices courseCategoryServices;

    @GetMapping({"", "/"}) //http://localhost:8080/coursecategory
    public ResponseEntity getAllCategories(){
        List<CourseCategoryDTO> categoryDTOS = courseCategoryServices.findAll();
        return ResponseEntity.ok(categoryDTOS);
    }
}
