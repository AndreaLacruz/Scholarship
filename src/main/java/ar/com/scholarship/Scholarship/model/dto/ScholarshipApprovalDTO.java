package ar.com.scholarship.Scholarship.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ScholarshipApprovalDTO {

    @NotNull(message = "item filled is required")
    private Boolean scholarshipApproval;

    @NotNull(message = "item filled is required")
    private Long scholarshipTypeId;

}
    /*
{
    "scholarship_approval":true,
    "Scholarship_Type_id": 3 - 6,
}
 */