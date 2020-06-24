package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
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
import java.util.stream.Collectors;

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

    public StudentHasCourseDTO saveApplication(StudentHasCourseDTO dto, Long studentId , Long courseId,
                                               Long studentStatusId, Long applicationTypeId){
        StudentStatus studentStatus = studentStatusRepository
                .findById(studentStatusId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("student_Status", studentStatusId));

        ApplicationType applicationType = applicationTypeRepository
                .findById(applicationTypeId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("applicationType", applicationTypeId));

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
                .setApplicationType(applicationType)
                .setStudentStatus(studentStatus);

        //TODO como colocar application type en este codigo (el id de compra es 1L)
        StudentHasCourseDTO studentHasCourseDTOSaved;
        if (dto.getApplicationType().equals(1L)) {
            studentHasCourseDTOSaved = this.saveApplicationByPurchase(studentHasCourseToSave);
        } else {
            studentHasCourseDTOSaved = this.saveApplicationByScholarship(applicationType, student, course);
        }
        return studentHasCourseDTOSaved;
    }

    public StudentHasCourseDTO saveApplicationByScholarship(ApplicationType type, Student student, Course course){

        StudentHasCourseId id = new StudentHasCourseId();
        id.setCourseId(course.getId());
        id.setStudentId(student.getId());

        StudentHasCourse studentHasCourseToSave = new StudentHasCourse();
        studentHasCourseToSave.setId(id);
        studentHasCourseToSave.setCourseHasFinalized(false);
        // TODO falta poner application type y ver como reflear todos los tipos
        studentHasCourseToSave.getApplicationType().setId(2L);

        StudentHasCourse studentHasCourseSaved = studentHasCourseRepository.save(studentHasCourseToSave);
        StudentHasCourseDTO studentHasCourseDTOSaved = studentHasCourseCycleMapper.toDto(studentHasCourseSaved,context);
        return studentHasCourseDTOSaved;
    }

    public StudentHasCourseDTO saveApplicationByPurchase(StudentHasCourse studentHasCourseToSave){
        Integer purchasedCourseCounter = studentHasCourseToSave.getCourse().getOpenPlacesCounter();
        if (purchasedCourseCounter == 0)
            throw logicExceptionComponent.throwExceptionCourseSoldOut(studentHasCourseToSave.getCourse().getName());

        studentHasCourseToSave.setCourseHasFinalized(false);
        // TODO descubrir como se debe aplicar lo siguiente
        studentHasCourseToSave.getApplicationType().setId(1L);
        studentHasCourseToSave.getCourse().setOpenPlacesCounter(purchasedCourseCounter - 1);
        StudentHasCourse studentHasCourseSave = studentHasCourseRepository.save(studentHasCourseToSave);
        StudentHasCourseDTO studentHasCourseDTOSaved = studentHasCourseCycleMapper.toDto(studentHasCourseSave, context);
        return studentHasCourseDTOSaved;
    }

    public List<StudentHasCourseDTO> findByStudentStatus(Long studentStatusId){
        List<StudentHasCourse> studentHasCourses = studentHasCourseRepository
                .findCourseByStudentStatus(studentStatusId);
        List<StudentHasCourseDTO> courseListByStatus = studentHasCourseCycleMapper.toDto(studentHasCourses,context);
        return courseListByStatus;
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
