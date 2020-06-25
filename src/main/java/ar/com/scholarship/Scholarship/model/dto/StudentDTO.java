package ar.com.scholarship.Scholarship.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name", "last_name", "birthday", "gender", "address"})
public class StudentDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "last_name is required")
    private String lastName;

    @NotNull(message = "birthday is required")
    @Past(message = "the birthday must be past date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank(message = "gender is required")
    private String gender;

    @NotBlank(message = "address is required")
    private String address;

    @JsonPropertyOrder({"student"})
    private SocioEconomicStatusDTO socioEconomicStatus;


}

/*
{
    "name": "Leonardo",
    "last_name": "Torres",
    "birthday": "1999-02-11",
    "gender": "masculino",
    "address": "av. rivadavia 1221",
}
*/
