package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.StudentHasCourseDTO;
import ar.com.scholarship.Scholarship.model.entity.StudentHasCourse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentHasCourseCycleMapper extends DataCyclerMapper<StudentHasCourseDTO, StudentHasCourse> {

    StudentHasCourseCycleMapper MAPPER = Mappers.getMapper(StudentHasCourseCycleMapper.class);
}
