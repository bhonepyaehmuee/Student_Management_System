package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends Auditable
{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    private String description;

    private Boolean status = true;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}

