package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.ApplicationTypeDTO;
import ar.com.scholarship.Scholarship.model.entity.ApplicationType;
import ar.com.scholarship.Scholarship.model.mapper.ApplicationTypeCycleMapper;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.repository.ApplicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("applicationTypeServices")
public class ApplicationTypeServices implements Services<ApplicationTypeDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ApplicationTypeCycleMapper typeCycleMapper = ApplicationTypeCycleMapper.MAPPER;

    @Autowired @Qualifier("applicationTypeRepository")
    private ApplicationTypeRepository applicationTypeRepository;

    @Override
    public List<ApplicationTypeDTO> findAll() {
        List<ApplicationType> applicationTypeList = applicationTypeRepository.findAll();
        List<ApplicationTypeDTO> applicationTypeDTOList = typeCycleMapper.toDto(applicationTypeList,context);
        return applicationTypeDTOList;
    }

    @Override
    public ApplicationTypeDTO save(ApplicationTypeDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
