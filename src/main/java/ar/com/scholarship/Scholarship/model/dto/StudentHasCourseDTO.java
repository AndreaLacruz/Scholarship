package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.*;
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
@JsonPropertyOrder({"id", "student","student_status_id", "course", "application_type_id", "course_has_finalized"})
public class StudentHasCourseDTO {


    private StudentDTO student;
    private CourseDTO course;

    @NotNull(message = "item filled is required")
    private Boolean courseHasFinalized;

    private Long studentStatusId;
    private Long applicationTypeId;

    private StudentStatusDTO studentStatus;
    private ApplicationTypeDTO applicationType;

}

/*
{
    "student_id": 9,
    "course_id": 5,
    "course_Has_Finalized": false,
    "student_Status_id": 1,
    "application_Type_id": 3,
}
*/
