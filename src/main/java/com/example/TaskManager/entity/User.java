package com.example.TaskManager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TaskUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JoinColumn(unique = true)
    public String username;

    public String password;

//    public

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime createdAt;
}
