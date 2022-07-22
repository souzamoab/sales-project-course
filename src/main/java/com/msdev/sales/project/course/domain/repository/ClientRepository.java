package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    boolean existsByName(String name);

    @Query(value = "delete from Client c where c.name = :name")
    @Modifying
    @Transactional
    void deleteByName(String name);

}
