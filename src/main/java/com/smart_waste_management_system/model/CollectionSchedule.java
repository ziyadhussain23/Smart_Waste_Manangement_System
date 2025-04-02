package com.smart_waste_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CollectionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schedule_id;

    @NotEmpty(message = "Area cannot be empty")
    private String area;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @NotEmpty(message = "Status cannot be empty")
    private String status;
}