package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String fullName;

    @Column
    @Email
    @NotNull
    @Size(max = 100)
    private String email;

    @Column
    @NotNull
    @Size(max = 50)
    private String nickname;

    @Column
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String password;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "id.user")
    public List<Candidate> candidates;

    @OneToMany(mappedBy = "id.user")
    private List<Submission> submissions;

    public void setCreatedAt(LocalDateTime now) {
        this.createdAt=now;
    }
}
