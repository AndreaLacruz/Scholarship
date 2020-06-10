package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.model.entity.*;
import ar.com.scholarship.Scholarship.model.mapper.CourseCycleMapper;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.repository.CompanyRepository;
import ar.com.scholarship.Scholarship.model.repository.CourseCategoryRepository;
import ar.com.scholarship.Scholarship.model.repository.CourseRepository;
import ar.com.scholarship.Scholarship.model.repository.ModalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("courseServices")
public class CourseServices {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseCycleMapper courseCycleMapper = CourseCycleMapper.MAPPER;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    @Qualifier("modalityRepository")
    private ModalityRepository modalityRepository;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    public List<CourseDTO> findAll() {
        List<Course> courseList = courseRepository.findAll();
        List<CourseDTO> courseDTOList = courseCycleMapper.toDto(courseList, context);
        return courseDTOList;
    }


    public CourseDTO save(CourseDTO dto, Long modalityId, Long companyId, Long courseCategoryId
                          ) {
        Modality modality = modalityRepository
                .findById(modalityId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("Modality", modalityId));

        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("Company", companyId));

        CourseCategory courseCategory = courseCategoryRepository
                .findById(courseCategoryId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("CourseCategory", courseCategoryId));

        Course courseToSave = courseCycleMapper.toEntity(dto, context);
        courseToSave.setCompany(company);
        courseToSave.setCategory(courseCategory);
        courseToSave.setModality(modality);
        Course courseSaved = courseRepository.save(courseToSave);
        CourseDTO courseDTOSaved = courseCycleMapper.toDto(courseSaved, context);
        return courseDTOSaved;
    }


    public void delete(Long id) {
        Optional<Course> byIdOptional = courseRepository.findById(id);
        if (byIdOptional.isPresent()){
            Course courseToDelete = byIdOptional.get();
            courseRepository.delete(courseToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Course", id);
        }
    }
}
