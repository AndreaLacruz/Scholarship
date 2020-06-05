package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.CompanyStatusDTO;
import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyStatusCycleMapper extends DataCyclerMapper<CompanyStatusDTO, CompanyStatus> {
    CompanyStatusCycleMapper MAPPER = Mappers.getMapper(CompanyStatusCycleMapper.class);

}
