package com.example.spring.dto;

import lombok.*;

import java.util.List;
@Getter
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
    private List<LocationInfoDto> locationInfoDtoList;
}
