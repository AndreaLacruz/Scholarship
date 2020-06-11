package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.Company;
import ar.com.scholarship.Scholarship.model.entity.CourseCategory;
import ar.com.scholarship.Scholarship.model.entity.Modality;
import ar.com.scholarship.Scholarship.model.entity.StudentHasCourse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name","description", "category", "modality", "total_hours",  "price", "company", "places", "scholarship_places",
"studentHasCourse"})
public class CourseDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "price is required")
    private BigInteger price;

    @NotNull(message = "total_hours is required")
    private Time totalHours;

    @NotNull(message = "places is required")
    private Integer places;

    @NotNull(message = "scholarship_places is required")
    private Integer scholarshipPlaces;

    private Set<StudentHasCourse> studentHasCourses;
    private Modality modality;
    private CourseCategory category;
    private Company company;
}
