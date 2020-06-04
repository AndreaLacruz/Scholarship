package ar.com.scholarship.Scholarship.model.dto;

import ar.com.scholarship.Scholarship.model.entity.Manager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter @Getter @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "type", "manager"})
public class DocTypeDTO {

    private Long id;

    @NotBlank(message = "type is required")
    private String type;

    private Set<Manager> manager;

    public DocTypeDTO(Long id, String type, Set<Manager> manager) {
        this.id = id;
        this.type = type;
        this.manager = manager;
    }
}
