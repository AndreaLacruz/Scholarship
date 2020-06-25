package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.ApplicationCourseStudentDTO;
import ar.com.scholarship.Scholarship.model.dto.ScholarshipApprovalDTO;
import ar.com.scholarship.Scholarship.model.dto.StatusProgressDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentHasCourseDTO;
import ar.com.scholarship.Scholarship.model.entity.*;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.StudentHasCourseCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentHasCourseServices")
public class StudentHasCourseServices {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    StudentHasCourseCycleMapper studentHasCourseCycleMapper = StudentHasCourseCycleMapper.MAPPER;

    @Autowired
    @Qualifier("studentHasCourseRepository")
    private StudentHasCourseRepository studentHasCourseRepository;

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("applicationTypeRepository")
    private ApplicationTypeRepository applicationTypeRepository;

    @Autowired
    @Qualifier("studentStatusRepository")
    private StudentStatusRepository studentStatusRepository;

    public StudentHasCourseDTO saveApplication(ApplicationCourseStudentDTO dto, Long studentId , Long courseId){
        StudentStatus studentStatus = studentStatusRepository
                .findById(1l)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("student_Status", 1L));
        Student student = studentRepository
                .findById(studentId).orElseThrow( () -> logicExceptionComponent.throwExceptionEntityNotFound("student", studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("course", courseId));
        StudentHasCourseId studentHasCourseId = new StudentHasCourseId()
                .setStudentId(studentId)
                .setCourseId(courseId);
        StudentHasCourse studentHasCourseToSave = new StudentHasCourse()
                .setId(studentHasCourseId)
                .setCourse(course)
                .setStudent(student)
                .setStudentStatus(studentStatus)
                .setCourseHasFinalized(false);
        StudentHasCourseDTO studentHasCourseDTOSaved;
        if (dto.getIsBuy()) {
            studentHasCourseDTOSaved = this.saveApplicationByPurchase(studentHasCourseToSave);
        } else {
            studentHasCourseDTOSaved = this.saveApplicationByScholarship(studentHasCourseToSave);
        }
        return studentHasCourseDTOSaved;
    }


    public StudentHasCourseDTO saveApplicationByScholarship(StudentHasCourse studentHasCourseToSave){
        SocioEconomicStatus socioEconomicStatus = studentHasCourseToSave.getStudent().getSocioEconomicStatus();
        if (socioEconomicStatus == null)
            throw logicExceptionComponent.throwExceptionScholarshipDenied();
        ApplicationType applicationType = applicationTypeRepository
                .findById(2L)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("applicationType", 2L));
        studentHasCourseToSave.setApplicationType(applicationType);
        StudentHasCourse studentHasCourseSaved = studentHasCourseRepository.save(studentHasCourseToSave);
        StudentHasCourseDTO studentHasCourseDTOSaved = studentHasCourseCycleMapper.toDto(studentHasCourseSaved,context);
        return studentHasCourseDTOSaved;
    }

    public StudentHasCourseDTO saveApplicationByPurchase(StudentHasCourse studentHasCourseToSave){
        Integer purchasedCourseCounter = studentHasCourseToSave.getCourse().getOpenPlacesCounter();
        if (purchasedCourseCounter == 0)
            throw logicExceptionComponent.throwExceptionCourseSoldOut(studentHasCourseToSave.getCourse().getName());
        ApplicationType applicationType = applicationTypeRepository
                .findById(1L)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("applicationType", 1L));
        studentHasCourseToSave.setApplicationType(applicationType);
        studentHasCourseToSave.getCourse().setOpenPlacesCounter(purchasedCourseCounter - 1);
        StudentHasCourse studentHasCourseSave = studentHasCourseRepository.save(studentHasCourseToSave);
        StudentHasCourseDTO studentHasCourseDTOSaved = studentHasCourseCycleMapper.toDto(studentHasCourseSave, context);
        return studentHasCourseDTOSaved;
    }

    //TODO Revisar
    public StudentHasCourseDTO findByStudentStatus(StatusProgressDTO statusId, Long studentId, Long courseId){
        StudentHasCourseId studentHasCourseId = new StudentHasCourseId()
                .setStudentId(studentId)
                .setCourseId(courseId);
        StudentHasCourse studentHasCourse = studentHasCourseRepository
                .findCourseByStudentStatus(studentHasCourseId);
        if (statusId.equals(3L)){
            studentHasCourse.setCourseHasFinalized(true);
        } else {
            studentHasCourse.setStudentStatus(statusId.getStatusProgressId());
        }
        StudentHasCourseDTO studentHasCourseDTOUpdated = studentHasCourseCycleMapper.toDto( studentHasCourse, context);
        return studentHasCourseDTOUpdated;
    }


    // TODO REVISAR
    public StudentHasCourseDTO courseScholarshipApproval(ScholarshipApprovalDTO approvalDTO, Long courseId, Long studentId){
        StudentHasCourse studentHasCourse = studentHasCourseRepository
                .findById(approvalDTO.getScholarshipTypeId())
                .orElseThrow(()-> logicExceptionComponent.throwExceptionScholarshipDenied());
        Integer scholarshipCounter = studentHasCourse.getCourse().getScholarshipCounter();
        if (scholarshipCounter == 0)
            throw logicExceptionComponent.throwExceptionCourseSoldOut(studentHasCourse.getCourse().getName());
        if (approvalDTO.getScholarshipApproval()){
            studentHasCourse.setStudentStatus(approvalDTO.getScholarshipApproval());
            studentHasCourse.setApplicationType(approvalDTO.getScholarshipTypeId());
            studentHasCourse.getCourse().setScholarshipCounter(scholarshipCounter -1);
        }
        StudentHasCourseDTO studentHasCourseDTOUpdated = studentHasCourseCycleMapper.toDto(studentHasCourse, context);
        return studentHasCourseDTOUpdated;
    }

    public List<StudentHasCourseDTO> findAll(){
        List<StudentHasCourse> all = studentHasCourseRepository.findAll();
        List<StudentHasCourseDTO> studentHasCourseDTOList = studentHasCourseCycleMapper.toDto(all, context);
        return studentHasCourseDTOList;
    }

    public void delete(Long id){
        Optional<StudentHasCourse> byIdOptional = studentHasCourseRepository.findById(id);
        if (byIdOptional.isPresent()){
            StudentHasCourse studentHasCourseToDelete = byIdOptional.get();
            studentHasCourseRepository.delete(studentHasCourseToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("studentHasCourse", id);
        }
    }
}
