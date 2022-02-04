package com.example.spring.dto;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateUserDto {
    private String name;
    private String surname;
    private Integer age;
}
