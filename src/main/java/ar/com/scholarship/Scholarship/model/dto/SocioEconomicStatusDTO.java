package ar.com.scholarship.Scholarship.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "are_Studying", "are_Working", "has_Income", "monthly_income", "has_Kids", "how_many", "student"})
public class SocioEconomicStatusDTO implements Serializable {

    private Long id;

    @NotNull(message = "item filled is required")
    private Boolean areStudying;

    @NotNull(message = "item filled is required")
    private Boolean areWorking;

    @NotNull(message = "item filled is required")
    private Boolean hasIncome;

    @NotNull(message = "item filled is required")
    private Double monthlyIncome;

    @NotNull(message = "item filled is required")
    private Boolean hasKids;

    @NotNull(message = "item filled is required")
    private Integer howMany;

    @NotNull(message = "student_id is required")
    private Long studentId;

    private StudentDTO student;


}

/*
{
    "are_studying": true,
    "are_working": false,
    "has_income": true,
    "monthly_income": 23000.4,
    "has_kids": false,
    "how_many": 0,
    "student_id": 1
}
*/

