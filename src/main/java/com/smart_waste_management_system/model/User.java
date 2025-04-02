package com.smart_waste_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotEmpty(message = "Username cannot be empty")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Role cannot be empty")
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PickupRequest> pickupRequests = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Complaint> complaints = new ArrayList<>();


}