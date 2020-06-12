package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.SocioEconomicStatusDTO;
import ar.com.scholarship.Scholarship.model.entity.SocioEconomicStatus;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocioEconomicStatusCycleMapper extends DataCyclerMapper<SocioEconomicStatusDTO,SocioEconomicStatus> {

    SocioEconomicStatusCycleMapper MAPPER = Mappers.getMapper(SocioEconomicStatusCycleMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "studentId", ignore = true)
    SocioEconomicStatusDTO toDto(SocioEconomicStatus entity, @Context CycleAvoidingMappingContext context);
}
