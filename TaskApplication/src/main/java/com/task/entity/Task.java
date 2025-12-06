package com.task.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    private Integer id;

    private String title;
    private String description;
    private String assignedTo;
    private String priority;
    private String status;
    private LocalDate dueDate;
}
