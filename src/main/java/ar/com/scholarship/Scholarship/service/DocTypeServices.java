package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.DocTypeDTO;
import ar.com.scholarship.Scholarship.model.entity.DocType;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.DocTypeCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.DocTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("docTypeServices")
public class DocTypeServices implements Services<DocTypeDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("docTypeRepository")
    private DocTypeRepository docTypeRepository;

    private DocTypeCycleMapper docTypeCycleMapper = DocTypeCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Override
    public DocTypeDTO save(DocTypeDTO dto) throws Exception {
        return null;
    }

    @Override
    public List<DocTypeDTO> findAll() {
        List<DocType> docTypeEntityList = docTypeRepository.findAll();
        List<DocTypeDTO> typeDTOList = docTypeCycleMapper.toDto(docTypeEntityList, context);
        return typeDTOList;
    }

    @Override
    public void delete(Long id) {
    }
}