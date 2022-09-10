package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName(String userName);

}
