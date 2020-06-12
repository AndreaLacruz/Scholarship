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
@JsonPropertyOrder({"id", "studies", "work", "income", "monthly_income", "kids", "how_many", "student"})
public class SocioEconomicStatusDTO implements Serializable {

    private Long id;

    @NotBlank(message = "item filled is required")
    private Boolean areStudying;

    @NotBlank(message = "item filled is required")
    private Boolean areWorking;

    @NotBlank(message = "item filled is required")
    private Boolean hasIncome;

    @NotNull(message = "item filled is required")
    private Double monthlyIncome;

    @NotBlank(message = "item filled is required")
    private Boolean hasKids;

    @NotNull(message = "item filled is required")
    private Integer howMany;

    @NotNull(message = "student_id is required")
    private Long studentId;

    private StudentDTO student;


}

/*
{
    "studies": true,
    "work": false,
    "income": true,
    "monthly_income": 23000.4,
    "kids": false,
    "how_many": 0,
    "student_id": 1
}
*/

