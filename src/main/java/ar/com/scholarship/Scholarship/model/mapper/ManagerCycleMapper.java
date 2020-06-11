package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.ManagerDTO;
import ar.com.scholarship.Scholarship.model.entity.Manager;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ManagerCycleMapper extends DataCyclerMapper<ManagerDTO, Manager>{

    ManagerCycleMapper MAPPER = Mappers.getMapper(ManagerCycleMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "docTypeId", ignore = true)
    ManagerDTO toDto(Manager entity, @Context CycleAvoidingMappingContext context);
}
