package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.CourseCategoryDTO;
import ar.com.scholarship.Scholarship.model.entity.CourseCategory;
import ar.com.scholarship.Scholarship.model.mapper.CourseCategoryCycleMapper;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.repository.CourseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseCategoryServices")
public class CourseCategoryServices implements Services<CourseCategoryDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CourseCategoryCycleMapper categoryCycleMapper = CourseCategoryCycleMapper.MAPPER;

    @Autowired
    @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    @Override
    public List<CourseCategoryDTO> findAll() {
        List<CourseCategory> categoryList = courseCategoryRepository.findAll();
        List<CourseCategoryDTO> categoryDTOList = categoryCycleMapper.toDto(categoryList,context);
        return categoryDTOList;
    }

    @Override
    public CourseCategoryDTO save(CourseCategoryDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
