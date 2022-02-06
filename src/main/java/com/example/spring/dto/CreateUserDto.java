package com.example.spring.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateUserDto {
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "surname must not be empty")
    private String surname;
    @Min(1)
    @Max(120)
    private Integer age;
    @Min(1)
    private Long locationId;
}
