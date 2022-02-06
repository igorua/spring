package com.example.spring.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EditUserDto {
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ ']*", message = "use only English,Ukrainian or Russian letters")
    @NotEmpty(message = "surname must not be empty")
    private String surname;
    @Min(1)
    private Integer age;
}
