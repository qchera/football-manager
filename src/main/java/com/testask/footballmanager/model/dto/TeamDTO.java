package com.testask.footballmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotNull
    @NotBlank(message = "Field name can't be blank")
    private String name;

    @NotNull
    @Min(value = 0, message = "Minimal commission for team is 0")
    @Max(value = 10, message = "Maximal commission for team is 10")
    private Integer commission;

    @NotNull
    @Positive(message = "Balance must be positive value")
    private Double balance;

    private List<@Valid PlayerDTO> players;
}
