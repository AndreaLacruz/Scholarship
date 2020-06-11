package ar.com.scholarship.Scholarship.model.dto;

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
import java.time.Year;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name", "cuil", "type", "address", "foundation_year", "manager", "contact", "status", "courses" })
public class CompanyDTO implements Serializable {


    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "cuil is required")
    private Integer cuil;

    @NotBlank(message = "type is required")
    private String type;

    @NotBlank(message = "address is required")
    private String address;

    @NotNull(message = "foundation_year is required")
    private Year foundationYear;

    @NotNull(message = "contact is required")
    private Integer contact;

    @NotNull(message = "company_status_id is required")
    private Long companyStatusId;

    @JsonIgnoreProperties({"company"})
    private CompanyStatusDTO status;

    @JsonIgnoreProperties({"company"})
    private Set<ManagerDTO> manager;

    @JsonIgnoreProperties({"company"})
    private List<CourseDTO> courses;
}

/*
{
    "name": "Company 1",
    "cuil": 10203004005,
    "type": "CA",
    "address": "Florida 100",
    "foundation_year": 2009,
    "contact": 1122223333,
    "company_status_id": 1
}
*/
