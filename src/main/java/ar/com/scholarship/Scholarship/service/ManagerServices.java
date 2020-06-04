package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.ManagerDTO;
import ar.com.scholarship.Scholarship.model.entity.Company;
import ar.com.scholarship.Scholarship.model.entity.DocType;
import ar.com.scholarship.Scholarship.model.entity.Manager;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.ManagerCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.CompanyRepository;
import ar.com.scholarship.Scholarship.model.repository.DocTypeRepository;
import ar.com.scholarship.Scholarship.model.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerServices")
public class ManagerServices  {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("managerRepository")
    private ManagerRepository managerRepository;

    @Autowired @Qualifier("docTypeRepository")
    private DocTypeRepository docTypeRepository;

    @Autowired @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ManagerCycleMapper managerCycleMapper = ManagerCycleMapper.MAPPER;


    public ManagerDTO save(ManagerDTO dto, Long companyId, Long docTypeId) {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Company", companyId));

        DocType docType = docTypeRepository
                .findById(docTypeId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("DocType", docTypeId));


        Manager managerToSave = managerCycleMapper.toEntity(dto,context);
        managerToSave.setDocType(docType);
        managerToSave.setCompany(company);
        Manager managerSaved = managerRepository.save(managerToSave);
        ManagerDTO managerDTOSaved = managerCycleMapper.toDto(managerSaved, context);
        return managerDTOSaved;
    }


    public void delete(Long id) {

    }


    public List<ManagerDTO> findAll() {
        return null;
    }

}
