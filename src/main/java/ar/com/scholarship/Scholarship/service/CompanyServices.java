package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
import ar.com.scholarship.Scholarship.model.entity.Company;
import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import ar.com.scholarship.Scholarship.model.entity.Manager;
import ar.com.scholarship.Scholarship.model.mapper.CompanyCycleMapper;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.repository.CompanyRepository;
import ar.com.scholarship.Scholarship.model.repository.CompanyStatusRepository;
import ar.com.scholarship.Scholarship.model.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CompanyServices {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Autowired
    @Qualifier("companyStatusRepository")
    private CompanyStatusRepository companyStatusRepository;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("managerRepository")
    private ManagerRepository managerRepository;

    private CompanyCycleMapper companyCycleMapper = CompanyCycleMapper.MAPPER;


    public void delete(Long id) {
        Optional<Company> byIdOptional = companyRepository.findById(id);
        if (byIdOptional.isPresent()){
            Company companyToDelete = byIdOptional.get();
            companyRepository.delete(companyToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company", id);
        }
    }


    public CompanyDTO save(CompanyDTO dto,Long companyStatusId, Long managerId)  {
        Manager manager = managerRepository
                .findById(managerId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Manager", managerId));

        CompanyStatus status = companyStatusRepository
                .findById(companyStatusId)
                .orElseThrow(()-> logicExceptionComponent.throwExceptionEntityNotFound("CompanyStatus", companyStatusId));

        Company companyToSave = companyCycleMapper.toEntity(dto, context);
        companyToSave.setManager((Set<Manager>) manager);
        companyToSave.setStatus(status);
        Company companySaved = companyRepository.save(companyToSave);
        CompanyDTO companyDTOSaved = companyCycleMapper.toDto(companySaved, context);
        return companyDTOSaved;
    }


    public List<CompanyDTO> findAll() {
       List<Company> companyList = companyRepository.findAll();
       List<CompanyDTO> companyDTOList = companyCycleMapper.toDto(companyList, context);
       return companyDTOList;
    }
}
