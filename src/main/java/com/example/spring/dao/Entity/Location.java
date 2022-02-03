package com.example.spring.dao.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @ManyToMany(mappedBy = "locations")
    private List<User> user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;
}
