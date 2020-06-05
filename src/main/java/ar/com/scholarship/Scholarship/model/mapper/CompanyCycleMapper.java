package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
import ar.com.scholarship.Scholarship.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyCycleMapper extends DataCyclerMapper<CompanyDTO, Company> {

    CompanyCycleMapper MAPPER = Mappers.getMapper(CompanyCycleMapper.class);
}
