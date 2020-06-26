package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.StudentDTO;
import ar.com.scholarship.Scholarship.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentCycleMapper extends DataCyclerMapper<StudentDTO, Student> {

    StudentCycleMapper MAPPER = Mappers.getMapper(StudentCycleMapper.class);
}
