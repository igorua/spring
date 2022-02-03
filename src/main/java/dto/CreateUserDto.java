package dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateUserDto {
    private String name;
    private String surname;
    private Integer age;
}
