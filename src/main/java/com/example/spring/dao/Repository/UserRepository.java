package com.example.spring.dao.Repository;

import com.example.spring.dao.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
