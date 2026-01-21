package com.example.TaskManager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String subject;

    public String task;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime taskDate;
}
