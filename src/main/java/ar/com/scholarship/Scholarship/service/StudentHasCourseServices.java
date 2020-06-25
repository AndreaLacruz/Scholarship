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

    public StudentHasCourseDTO findByStudentStatus(Long statusId, Long studentId, Long courseId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Student", studentId));
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Course", courseId));
        StudentStatus studentStatus = studentStatusRepository
                .findById(statusId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("StudentStatus", statusId));
        StudentHasCourseId studentHasCourseId = new StudentHasCourseId()
                .setStudentId(studentId)
                .setCourseId(courseId);
        StudentHasCourse studentHasCourse = studentHasCourseRepository
                .findById(studentHasCourseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("StudentHasCourse", studentHasCourseId));
        studentHasCourse.setStudentStatus(studentStatus);
        if (studentStatus.getStatus().equals("Estudiante finalizado")) {
            studentHasCourse.setCourseHasFinalized(true);
        }
        StudentHasCourse studentHasCourseUpdated = studentHasCourseRepository.save(studentHasCourse);
        StudentHasCourseDTO studentHasCourseDTOUpdated = studentHasCourseCycleMapper.toDto(studentHasCourseUpdated, context);
        return studentHasCourseDTOUpdated;
    }


    public StudentHasCourseDTO courseScholarshipApproval(ScholarshipApprovalDTO approvalDTO, Long courseId, Long studentId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Student", studentId));
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Course", courseId));
        Integer scholarshipCounter = course.getScholarshipCounter();
        if (scholarshipCounter == 0)
            throw logicExceptionComponent.throwExceptionCourseSoldOut(course.getName());
        ApplicationType applicationType = applicationTypeRepository
                .findById(approvalDTO.getScholarshipTypeId())
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Course", approvalDTO.getScholarshipTypeId()));
        StudentHasCourseId studentHasCourseId = new StudentHasCourseId()
                .setStudentId(student.getId())
                .setCourseId(course.getId());
        StudentHasCourse studentHasCourse = studentHasCourseRepository
                .findById(studentHasCourseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("StudentHasCourse", studentHasCourseId));
        if (approvalDTO.getScholarshipApproval()) {
            StudentStatus studentStatus = studentStatusRepository
                    .findById(1L)
                    .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("StudentStatus", 1L));
            studentHasCourse.setStudentStatus(studentStatus);
            studentHasCourse.setApplicationType(applicationType);
            studentHasCourse.getCourse().setScholarshipCounter(scholarshipCounter - 1);
        } else {
            StudentStatus studentStatus = studentStatusRepository
                    .findById(4L)
                    .orElseThrow(() -> logicExceptionComponent.throwExceptionScholarshipDenied());
            // buscar en el la bd el studentStatus rechado
            studentHasCourse.setStudentStatus(studentStatus);
            studentHasCourse.setApplicationType(applicationType);
        }
        StudentHasCourse studentHasCourseUpdated = studentHasCourseRepository.save(studentHasCourse);
        StudentHasCourseDTO studentHasCourseDTOUpdated = studentHasCourseCycleMapper.toDto(studentHasCourseUpdated, context);
        return studentHasCourseDTOUpdated;
    }

    public List<StudentHasCourseDTO> findAll(){
        List<StudentHasCourse> all = studentHasCourseRepository.findAll();
        List<StudentHasCourseDTO> studentHasCourseDTOList = studentHasCourseCycleMapper.toDto(all, context);
        return studentHasCourseDTOList;
    }

}
