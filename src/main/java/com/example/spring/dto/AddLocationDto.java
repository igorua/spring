package com.example.spring.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLocationDto {
    private String locationName;
    private Double longitude;
    private Double latitude;
    private Long regionId;
    private String regionName;
}
