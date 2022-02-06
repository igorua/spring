package com.example.spring.dao.Repository;

import com.example.spring.dao.Entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
