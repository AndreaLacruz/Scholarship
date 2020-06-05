package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseCycleMapper extends DataCyclerMapper<CourseDTO, Course> {

    CourseCycleMapper MAPPER = Mappers.getMapper(CourseCycleMapper.class);

}
