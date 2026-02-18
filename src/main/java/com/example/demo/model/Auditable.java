package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Auditable
{
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by",updatable = false)
    private String createdBy;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;


    @PrePersist
    public void onCreate()
    {
        this.createdAt = LocalDateTime.now();
        this.createdBy = getCurrentUser();

        this.updatedAt =  LocalDateTime.now();
        this.updatedBy = getCurrentUser();
    }

    @PreUpdate
    public void onUpdate()
    {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = getCurrentUser();
    }


    public String getCurrentUser()
    {
        return "test user";
    }
}
