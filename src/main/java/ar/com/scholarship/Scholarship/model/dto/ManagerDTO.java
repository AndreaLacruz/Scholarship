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

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name", "last_name","doc_type", "documentation", "position", "email", "company"})
public class ManagerDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "last_name is required")
    private String lastName;

    @NotNull(message = "documentation is required")
    private Integer documentation;

    @NotBlank(message = "position is required")
    private String position;

    @NotBlank(message = "email is required")
    private String email;

    @NotNull(message = "doctypeId is required")
    private Long docTypeId;

    @NotNull(message = "companyId is required")
    private Long companyId;

    @JsonIgnoreProperties({"manager"})
    private DocTypeDTO docType;

    @JsonIgnoreProperties({"manager"})
    private CompanyDTO company;
}

/*
{
    "name": "Juana",
    "last_name": "Perez",
    "documentation": 10200300,
    "position": "RRHH",
    "email": "email@email.com",
    "company_id": 1,
    "doc_type_id": 1,
}
 */
