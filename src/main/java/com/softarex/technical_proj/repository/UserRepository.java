package com.softarex.technical_proj.repository;

import com.softarex.technical_proj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
