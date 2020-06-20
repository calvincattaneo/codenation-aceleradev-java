package com.challenge.repository;

import java.util.List;

import com.challenge.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select c.id.user from Candidate c where c.id.acceleration.name like :name")
    List<User> findByAccelerationName(String name);

    @Query("select c.id.user from Candidate c where c.id.company.id = :companyId")
    List<User> findByCompanyId(Long companyId);

}
