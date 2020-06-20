package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, CandidateId> {

    @Query("from Candidate c where c.id.user.id= :userId"+
            " and c.id.company.id = :companyId" +
            " and c.id.acceleration.id = :accelerationId")
    Optional<Candidate> findAllByUserIdAndCompanyIdAndAccelerationId(Long userId, Long companyId, Long accelerationId);


    @Query("from Candidate c where c.id.company.id = :companyId")
    List<Candidate> findByCompanyId(Long companyId);

    @Query("from Candidate c where c.id.acceleration.id = :accelerationId")
    List<Candidate> findByAccelerationId(Long accelerationId);

}
