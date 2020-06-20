package com.challenge.repository;

import java.util.List;

import com.challenge.entity.Company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query("select distinct c from Company c join c.candidates ca where ca.id.acceleration.id = :accelerationId")
    List<Company> findByAccelerationId(Long accelerationId);

    @Query("from Company c join c.candidates ca where ca.id.user.id = :userId")
    List<Company> findByUserId(Long userId);

}
