package com.smart_waste_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RecyclingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer center_id;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotEmpty(message = "Contact Info cannot be empty")
    private String contact_info;
}