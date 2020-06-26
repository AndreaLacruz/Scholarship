package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.model.dto.CourseDTO;
import ar.com.scholarship.Scholarship.model.entity.Course;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseCycleMapper extends DataCyclerMapper<CourseDTO, Course> {

    CourseCycleMapper MAPPER = Mappers.getMapper(CourseCycleMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "courseCategoryId", ignore = true)
    @Mapping(target = "modalityId", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    CourseDTO toDto(Course entity, @Context CycleAvoidingMappingContext context);

}
