package com.challenge.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId> {
    @Query("select max(s.score) from Submission s where s.id.challenge.id = :challengeId")
    Optional<BigDecimal> findHigherScoreByChallengeId(Long challengeId);

    @Query("from Submission s join s.id.challenge.accelerations a "
            +"where s.id.challenge.id = :challengeId and a.id = :accelerationId")
    List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);
}
