package com.example.spring.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EditUserDto {
    private String name;
    private String surname;
    private Integer age;
}
