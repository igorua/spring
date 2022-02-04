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
public class RegionInfoDto {
    private Long id;
    private String name;
    private List<LocationInfoDto> locationInfoDtoList;
}
