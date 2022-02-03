package dto;

import lombok.*;
@Getter
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
