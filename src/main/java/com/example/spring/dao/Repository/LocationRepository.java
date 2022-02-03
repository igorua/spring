package com.example.spring.dao.Repository;

import com.example.spring.dao.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
