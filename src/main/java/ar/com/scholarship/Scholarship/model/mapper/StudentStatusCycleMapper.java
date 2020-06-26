package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.components.data.StudentStatusLoaderData;
import ar.com.scholarship.Scholarship.model.dto.StudentStatusDTO;
import ar.com.scholarship.Scholarship.model.entity.StudentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentStatusCycleMapper extends DataCyclerMapper<StudentStatusDTO, StudentStatus> {

    StudentStatusCycleMapper MAPPER = Mappers.getMapper(StudentStatusCycleMapper.class);

}
