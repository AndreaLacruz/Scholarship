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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public CourseDTO save(CourseDTO dto) {
        Long modalityId = dto.getModalityId();
        Modality modality = modalityRepository
                .findById(modalityId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("Modality", modalityId));

        Long companyId = dto.getCompanyId();
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("Company", companyId));

        Long courseCategoryId = dto.getCourseCategoryId();
        CourseCategory courseCategory = courseCategoryRepository
                .findById(courseCategoryId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("CourseCategory", courseCategoryId));

        Course courseToSave = courseCycleMapper.toEntity(dto, context);
        courseToSave.setCompany(company);
        courseToSave.setCategory(courseCategory);
        courseToSave.setModality(modality);

        int openPlacesCounter = dto.getPlaces() - dto.getScholarshipPlaces();
        courseToSave.setOpenPlacesCounter(openPlacesCounter);
        courseToSave.setScholarshipCounter(dto.getScholarshipPlaces());

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

    public CourseDTO update(CourseDTO courseDTO, Long id){
        Optional<Course> byIdOptional = courseRepository.findById(id);
        CourseDTO course = null;

        if (byIdOptional.isPresent()){
            Course courseById = byIdOptional.get();
            course.setId(courseById.getId());
            Course courseToUpdate = courseCycleMapper.toEntity(courseDTO, context);
            Course courseUpdated = courseRepository.save(courseToUpdate);
            course = courseCycleMapper.toDto(courseUpdated,context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Course", id);
        } return course;
    }

    public List<CourseDTO> findByAvailablePlaces(Integer page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page<Course> allCoursesByPlaces = courseRepository.findByAvailablePlaces(pageRequest);

        List<Course> openCourseList = allCoursesByPlaces.getContent();
        if (openCourseList.size() == 0)
            logicExceptionComponent.throwExceptionNotAvailableCourse();
        List<CourseDTO> openCourseListDto = courseCycleMapper.toDto(openCourseList, context);
        return openCourseListDto;
    }

    public List<CourseDTO> findByCompany(Long companyId, Integer page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page<Course> allCoursesByCompany = courseRepository.findByCompany(companyId, pageRequest);

        List<Course> courseListByCompany = allCoursesByCompany.getContent();
        List<CourseDTO> courseDTOList = courseCycleMapper.toDto(courseListByCompany,context);
        return courseDTOList;
    }

    public List<CourseDTO> findByCategory(Long categoryId, Integer page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page<Course> allCoursesByCategory = courseRepository.findByCategory(categoryId, pageRequest);

        List<Course> all = allCoursesByCategory.getContent();
        List<CourseDTO> courseDTOList = courseCycleMapper.toDto(all, context);
        return courseDTOList;
    }

    public List<CourseDTO> findByCompanyAndCategory(Long companyId, Long categoryId, Integer page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page<Course> allCoursesByCompanyAndCategory = courseRepository.findByCompanyAndCategory(categoryId, categoryId, pageRequest);

        List<Course> all = allCoursesByCompanyAndCategory.getContent();
        List<CourseDTO> courseDTOList = courseCycleMapper.toDto(all, context);
        return courseDTOList;
    }

    public List<CourseDTO> getAllCoursesByStudentStatusProgress(Long studentStatusId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page<Course> allCoursesByStudentStatusProgressPage = courseRepository.findAllCoursesByStudentStatusProgress(studentStatusId, pageRequest);
        List<Course> allCoursesByStudentStatusProgress = allCoursesByStudentStatusProgressPage.getContent();
        List<CourseDTO> courseDTOList = courseCycleMapper.toDto(allCoursesByStudentStatusProgress, context);
        return courseDTOList;
    }
}
