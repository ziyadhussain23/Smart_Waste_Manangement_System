package com.smart_waste_manangement_system.model;

import jakarta.persistence.*;
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
    private int complaint_id;
    private String description;
    private String status; // Pending, Resolved

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
