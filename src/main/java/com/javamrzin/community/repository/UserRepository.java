package com.javamrzin.community.repository;

import com.javamrzin.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
