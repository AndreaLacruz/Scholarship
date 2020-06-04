package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.Company;
import ar.com.scholarship.Scholarship.model.entity.DocType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter @Getter @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name", "lastName","docType", "documentation", "position", "email", "company"})
public class ManagerDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "documentation is required")
    private Integer documentation;

    @NotBlank(message = "position is required")
    private String position;

    @NotBlank(message = "email is required")
    private String email;

    private DocType docType;

    private Company company;
}
