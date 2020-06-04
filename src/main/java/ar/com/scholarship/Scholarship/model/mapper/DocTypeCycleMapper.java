package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.DocTypeDTO;
import ar.com.scholarship.Scholarship.model.entity.DocType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocTypeCycleMapper extends DataCyclerMapper<DocTypeDTO, DocType> {
    DocTypeCycleMapper MAPPER = Mappers.getMapper(DocTypeCycleMapper.class);
}
