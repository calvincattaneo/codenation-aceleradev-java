package com.challenge.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmissionService implements SubmissionServiceInterface {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission save(Submission object) {
        return submissionRepository.save(object);
    }

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {

        return submissionRepository.findHigherScoreByChallengeId(challengeId).orElse(BigDecimal.ZERO);
    }


    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return submissionRepository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

}
