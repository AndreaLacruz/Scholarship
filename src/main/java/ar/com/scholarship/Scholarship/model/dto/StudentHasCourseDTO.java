package ar.com.scholarship.Scholarship.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "student","status", "course", "application_type", "course_full", "course_has_finalized"})
public class StudentHasCourseDTO {


    private StudentDTO student;
    private CourseDTO course;

    @NotNull(message = "item filled is required")
    private Boolean courseFull;

    @NotNull(message = "item filled is required")
    private Boolean courseHasFinalized;

    private StudentStatusDTO status;
    private ApplicationTypeDTO applicationType;
}
