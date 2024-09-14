package com.javamrzin.community.repository;

import com.javamrzin.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u from User u where u.username like %?1%")
    List<User> findByUsernameQuery(String username);

    @Query(value = "select * from user u where u.username like CONCAT('%', ?1, '%')", nativeQuery = true)
    List<User> findByUsernameNativeQuery(String username);

}
