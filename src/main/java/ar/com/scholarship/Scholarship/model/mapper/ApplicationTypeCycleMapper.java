package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.ApplicationTypeDTO;
import ar.com.scholarship.Scholarship.model.entity.ApplicationType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationTypeCycleMapper extends DataCyclerMapper<ApplicationTypeDTO, ApplicationType> {

    ApplicationTypeCycleMapper MAPPER = Mappers.getMapper(ApplicationTypeCycleMapper.class);

}
