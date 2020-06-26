package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "category"})
public class CourseCategoryDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Category is required")
    private String category;

    private List<CourseDTO> courses;
}
