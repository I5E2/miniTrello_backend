package com.I5E2.todo.repository;

import com.I5E2.todo.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode,String> {
    Optional<CommonCode> findById(String id);
    Optional<CommonCode> findBycodeName(String name);
}
