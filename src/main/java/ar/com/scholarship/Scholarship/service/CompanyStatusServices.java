package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.CompanyStatusDTO;
import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import ar.com.scholarship.Scholarship.model.mapper.CompanyStatusCycleMapper;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.repository.CompanyStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyStatusServices")
public class CompanyStatusServices implements Services<CompanyStatusDTO> {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CompanyStatusCycleMapper companyStatusCycleMapper = CompanyStatusCycleMapper.MAPPER;

    @Autowired
    @Qualifier("companyStatusRepository")
    private CompanyStatusRepository companyStatusRepository;

    @Override
    public List<CompanyStatusDTO> findAll() {
        List<CompanyStatus> companyStatusList = companyStatusRepository.findAll();
        List<CompanyStatusDTO> companyStatusDTOList = companyStatusCycleMapper.toDto(companyStatusList, context);
        return companyStatusDTOList;
    }

    @Override
    public CompanyStatusDTO save(CompanyStatusDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
