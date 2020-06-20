package com.challenge.repository;

import java.util.List;

import com.challenge.entity.Challenge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
    @Query("select distinct c from Challenge c join c.accelerations a join a.candidates ca "
            + "where a.id = :accelerationId and ca.id.user.id = :userId")
    List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId);
}
