package com.example.spring.dto;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocationInfoDto {
    @Min(1)
    private Long id;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁЇїІіЄєҐґ ']*", message = "use only English,Ukrainian or Russian letters")
    private String name;
    @DecimalMin("0.01")
    private Double longitude;
    @DecimalMin("0.01")
    private Double latitude;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude;
    }
}
