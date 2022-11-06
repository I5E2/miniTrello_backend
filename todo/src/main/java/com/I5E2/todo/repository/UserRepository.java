package com.I5E2.todo.repository;

import com.I5E2.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findById(String id);
    Optional<User> findByLoginId(String loginId);
}
