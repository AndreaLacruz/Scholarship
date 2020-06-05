package ar.com.scholarship.Scholarship.model.mapper;

import ar.com.scholarship.Scholarship.components.data.CourseCategoryLoaderData;
import ar.com.scholarship.Scholarship.model.dto.CourseCategoryDTO;
import ar.com.scholarship.Scholarship.model.entity.CourseCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseCategoryCycleMapper extends DataCyclerMapper<CourseCategoryDTO, CourseCategory> {
    CourseCategoryCycleMapper MAPPER = Mappers.getMapper(CourseCategoryCycleMapper.class);

}
