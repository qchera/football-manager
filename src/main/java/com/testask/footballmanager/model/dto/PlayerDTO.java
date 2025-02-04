package com.testask.footballmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotNull
    @NotBlank(message = "Field name can't be blank")
    private String name;

    @NotNull
    @Min(value = 16, message = "Minimal age of player is 16")
    @Max(value = 70, message = "Maximal age of player is 70")
    private Integer age;

    @NotNull
    @Positive(message = "Experience can't be negative")
    private Integer experience;

    @NotNull
    private String team;
}

