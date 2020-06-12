package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.SocioEconomicStatusDTO;
import ar.com.scholarship.Scholarship.model.entity.SocioEconomicStatus;
import ar.com.scholarship.Scholarship.model.entity.Student;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.SocioEconomicStatusCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.SocioEconomicStatusRepository;
import ar.com.scholarship.Scholarship.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("socioEconomicStatusServices")
public class SocioEconomicStatusServices {

    private SocioEconomicStatusCycleMapper statusCycleMapper = SocioEconomicStatusCycleMapper.MAPPER;

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Autowired
    @Qualifier("socioEconomicStatusRepository")
    private SocioEconomicStatusRepository socioEconomicStatusRepository;

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;



    public SocioEconomicStatusDTO save(SocioEconomicStatusDTO dto) throws Exception {
        Long studentId = dto.getStudentId();
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("Student", studentId));

        SocioEconomicStatus socioEconomicStatusToSave = statusCycleMapper.toEntity(dto, context);
        socioEconomicStatusToSave.setStudent(student);
        SocioEconomicStatus socioEconomicStatusSaved = socioEconomicStatusRepository.save(socioEconomicStatusToSave);
        SocioEconomicStatusDTO socioEconomicStatusDTOSaved = statusCycleMapper.toDto(socioEconomicStatusSaved, context);
        return socioEconomicStatusDTOSaved;
    }


    public List<SocioEconomicStatusDTO> findAll() {
        List<SocioEconomicStatus> socioEconomicStatusList = socioEconomicStatusRepository.findAll();
        List<SocioEconomicStatusDTO> socioEconomicStatusDTOList = statusCycleMapper.toDto(socioEconomicStatusList, context);
        return socioEconomicStatusDTOList;
    }


    public void delete(Long id) {
        Optional<SocioEconomicStatus> byIdOptional = socioEconomicStatusRepository.findById(id);
        if (byIdOptional.isPresent()){
            SocioEconomicStatus socioEconomicStatusToDelete = byIdOptional.get();
            socioEconomicStatusRepository.delete(socioEconomicStatusToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Socio_Economic_Status", id);
        }
    }
}
