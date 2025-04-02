package com.smart_waste_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complaint_id;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Status cannot be empty")
    private String status; // e.g., Pending, Resolved

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}