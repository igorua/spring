package com.example.spring.dto;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EditLocationDto {
    private String name;
    private Double longitude;
    private Double latitude;
}
