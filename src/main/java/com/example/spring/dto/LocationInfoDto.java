package com.example.spring.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LocationInfoDto {
    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
}
