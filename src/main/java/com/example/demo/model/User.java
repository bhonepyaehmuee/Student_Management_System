package com.example.demo.model;

import com.example.demo.enums.RoleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@SuperBuilder
@Table (name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@AllArgsConstructor
@NoArgsConstructor
public abstract class User  extends Auditable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    protected String name;

    @Column (nullable = false)
    protected LocalDate dateOfBirth;

    @Column (nullable = false, unique = true)
    protected String email;

    @Column (nullable =false, unique = true)
    protected String username;


    @Column (nullable = false)
    @JsonIgnore
    protected String password;

    @Column (nullable = false)
    protected String phoneNumber;

    @Column (nullable = false)
    protected String address;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    protected RoleStatus role;


    @ManyToMany
    @JoinTable(name = "user_role",
           joinColumns =  @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

//    @OneToMany(mappedBy = "user")
//    private Set<Role> roles;


}
