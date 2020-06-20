package com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Embeddable
@Data
public class CandidateId implements Serializable {

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Acceleration acceleration_id;

    @ManyToOne
    @NotNull
    private Company company;

}
