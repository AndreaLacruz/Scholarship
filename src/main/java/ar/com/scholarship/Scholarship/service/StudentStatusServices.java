package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.ModalityDTO;
import ar.com.scholarship.Scholarship.model.dto.StudentStatusDTO;
import ar.com.scholarship.Scholarship.model.entity.Modality;
import ar.com.scholarship.Scholarship.model.entity.StudentStatus;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.StudentStatusCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.StudentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentStatusServices")
public class StudentStatusServices implements Services<StudentStatusDTO>{

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private StudentStatusCycleMapper statusCycleMapper = StudentStatusCycleMapper.MAPPER;

    @Autowired
    @Qualifier("studentStatusRepository")
    private StudentStatusRepository studentStatusRepository;

    @Override
    public List<StudentStatusDTO> findAll() {
        List<StudentStatus> statusList =  studentStatusRepository.findAll();
        List<StudentStatusDTO> studentStatusDTOList = statusCycleMapper.toDto(statusList,context);
        return studentStatusDTOList;
    }

    @Override
    public StudentStatusDTO save(StudentStatusDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
