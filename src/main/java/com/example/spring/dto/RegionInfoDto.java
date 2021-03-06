package com.example.spring.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegionInfoDto {
    @Min(1)
    private Long id;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ ']*", message = "use only English,Ukrainian or Russian letters")
    private String name;
    private List<LocationInfoDto> locationInfoDtoList;
}
