package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
import ar.com.scholarship.Scholarship.model.entity.Company;
import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import ar.com.scholarship.Scholarship.model.mapper.CompanyCycleMapper;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.repository.CompanyRepository;
import ar.com.scholarship.Scholarship.model.repository.CompanyStatusRepository;
import ar.com.scholarship.Scholarship.model.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("companyServices")
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

    private CompanyCycleMapper companyCycleMapper = CompanyCycleMapper.MAPPER;


    public void delete(Long id) {
        Optional<Company> byIdOptional = companyRepository.findById(id);
        if (byIdOptional.isPresent()) {
            Company companyToDelete = byIdOptional.get();
            companyRepository.delete(companyToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company", id);
        }
    }


    public CompanyDTO save(CompanyDTO dto) {
        Long companyStatusId = dto.getCompanyStatusId();
        CompanyStatus status = companyStatusRepository
                .findById(companyStatusId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("CompanyStatus", companyStatusId));

        Company companyToSave = companyCycleMapper.toEntity(dto, context);
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

    public CompanyDTO findById(Long id) {
        Optional<Company> byId = companyRepository.findById(id);
        CompanyDTO companyDTO = null;

        if (byId.isPresent()) {
            Company companyById = byId.get();
            companyDTO = companyCycleMapper.toDto(companyById, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company", id);
        }
        return companyDTO;
    }

    public CompanyDTO update(CompanyDTO companyDTO, Long id){
        Optional<Company> byIdOptional = companyRepository.findById(id);
        CompanyDTO company = null;

        if (byIdOptional.isPresent()){
            Company companyById = byIdOptional.get();
            company.setId(companyById.getId());
            Company companyToUpdate = companyCycleMapper.toEntity(companyDTO, context);
            Company companyUpdated = companyRepository.save(companyToUpdate);
            company = companyCycleMapper.toDto(companyUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Company " , id);
        } return company;
    }
}
