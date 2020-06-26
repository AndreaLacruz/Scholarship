package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.StudentDTO;
import ar.com.scholarship.Scholarship.model.entity.Student;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.StudentCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentServices")
public class StudentServices {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private StudentCycleMapper studentCycleMapper = StudentCycleMapper.MAPPER;

    @Autowired @Qualifier("studentRepository")
    private StudentRepository studentRepository;

    public StudentDTO save(StudentDTO dto){
        Student studentToSave = studentCycleMapper.toEntity(dto, context);
        Student studentSaved = studentRepository.save(studentToSave);
        StudentDTO studentDTOSaved = studentCycleMapper.toDto(studentSaved,context);
        return studentDTOSaved;
    }

    public List<StudentDTO> findAll(){
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOList = studentCycleMapper.toDto(students, context);
        return studentDTOList;
    }

    public void delete(Long id){
        Optional<Student> byIdOptional = studentRepository.findById(id);
        if (byIdOptional.isPresent()){
            Student studentToDelete = byIdOptional.get();
            studentRepository.delete(studentToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Student", id);
        }
    }

    public StudentDTO findByLastName(String lastName){
        Optional<Student> byLastName = studentRepository.findByLastName(lastName);
        StudentDTO studentDTO = null;
        Long id = null;
        if (byLastName.isPresent()){
            Student studentByLastName = byLastName.get();
            studentDTO = studentCycleMapper.toDto(studentByLastName, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Student", id);
        } return studentDTO;
    }

    public StudentDTO update(StudentDTO studentDTO, Long id){
        Optional<Student> byIdOptional = studentRepository.findById(id);
        StudentDTO student = null;
        if (byIdOptional.isPresent()){
            Student studentById = byIdOptional.get();
            student.setId(studentById.getId());
            Student studentToUpdate = studentCycleMapper.toEntity(studentDTO, context);
            Student studentUpdated = studentRepository.save(studentToUpdate);
            student = studentCycleMapper.toDto( studentUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Student", id);
        } return student;
    }
}
