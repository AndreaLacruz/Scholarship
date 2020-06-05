package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.components.BusinessLogicExceptionComponent;
import ar.com.scholarship.Scholarship.model.dto.ModalityDTO;
import ar.com.scholarship.Scholarship.model.entity.Modality;
import ar.com.scholarship.Scholarship.model.mapper.CycleAvoidingMappingContext;
import ar.com.scholarship.Scholarship.model.mapper.ModalityCycleMapper;
import ar.com.scholarship.Scholarship.model.repository.ModalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("modalityServices")
public class ModalityServices implements Services<ModalityDTO> {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ModalityCycleMapper modalityCycleMapper = ModalityCycleMapper.MAPPER;

    @Autowired
    @Qualifier("modalityRepository")
    private ModalityRepository modalityRepository;

    @Override
    public List<ModalityDTO> findAll() {
        List<Modality> modalities = modalityRepository.findAll();
        List<ModalityDTO> modalityDTOList = modalityCycleMapper.toDto(modalities, context);
        return modalityDTOList;
    }

    @Override
    public ModalityDTO save(ModalityDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
