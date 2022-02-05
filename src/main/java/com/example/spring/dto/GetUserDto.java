package com.example.spring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GetUserDto {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private LocationInfoDto locationInfoDto;
}
