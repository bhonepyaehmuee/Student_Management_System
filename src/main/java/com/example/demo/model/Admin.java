package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue ("ADMIN")
@Getter
@Setter

public class Admin extends User {

}
