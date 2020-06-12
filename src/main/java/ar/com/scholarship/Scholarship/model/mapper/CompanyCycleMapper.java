package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
import ar.com.scholarship.Scholarship.model.entity.Company;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyCycleMapper extends DataCyclerMapper<CompanyDTO, Company> {

    CompanyCycleMapper MAPPER = Mappers.getMapper(CompanyCycleMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "companyStatusId", ignore = true)
    CompanyDTO toDto(Company entity, @Context CycleAvoidingMappingContext context);
}
