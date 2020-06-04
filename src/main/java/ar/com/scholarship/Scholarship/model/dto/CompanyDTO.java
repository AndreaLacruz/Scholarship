package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import ar.com.scholarship.Scholarship.model.entity.Course;
import ar.com.scholarship.Scholarship.model.entity.Manager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name", "cuil", "type", "address", "foundationYear", "manager", "contact", "status", "courses" })
public class CompanyDTO implements Serializable {

    private Long id;
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cuil is required")
    private Integer cuil;

    @NotBlank(message = "type is required")
    private String type;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "year is required")
    private Year foundationYear;

    @NotBlank(message = "contact is required")
    private Integer contact;


    private CompanyStatus status;
    private Set<Manager> manager;
    private List<Course> courses;
}
