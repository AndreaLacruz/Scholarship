package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.components.data.ModalityLoaderData;
import ar.com.scholarship.Scholarship.model.dto.ModalityDTO;
import ar.com.scholarship.Scholarship.model.entity.Modality;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModalityCycleMapper extends DataCyclerMapper<ModalityDTO, Modality>{

    ModalityCycleMapper MAPPER = Mappers.getMapper(ModalityCycleMapper.class);

}
