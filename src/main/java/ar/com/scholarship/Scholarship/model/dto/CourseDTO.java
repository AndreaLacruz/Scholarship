package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.StudentHasCourse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name","description", "course_category", "modality", "total_hours",  "price", "company", "places", "scholarship_places"})
public class CourseDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "price is required")
    private BigInteger price;

    @NotNull(message = "total_hours is required")
    private Integer totalHours;

    @NotNull(message = "places is required")
    private Integer places;

    @NotNull(message = "scholarship_places is required")
    private Integer scholarshipPlaces;

    private Integer scholarshipCounter;
    private Integer openPlacesCounter;


    @NotNull(message = "company_id is required")
    private Long companyId;

    @NotNull(message = "modality_id is required")
    private Long modalityId;

    @NotNull(message = "course_category_id is required")
    private Long courseCategoryId;


    private Set<StudentHasCourse> studentHasCourses;

    @JsonIgnoreProperties({"courses"})
    private ModalityDTO modality;

    @JsonIgnoreProperties({"courses"})
    private CourseCategoryDTO courseCategory;

    @JsonIgnoreProperties({"courses"})
    private CompanyDTO company;
}

/*
{
    "name": "Diseño grafico",
    "description": "aprender a usar herramientas del diseño grafico",
    "total_hours": 700,
    "price": 200,
    "places": 10,
    "scholarship_places": 4,
    "course_category_id": 6,
    "modality_id" : 2,
    "company_id": 1
}
*/
