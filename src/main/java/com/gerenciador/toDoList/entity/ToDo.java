package com.gerenciador.toDoList.entity;

import com.gerenciador.toDoList.dto.ToDoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "to_do")
@Getter
@Setter
public class ToDo {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskName;
    private String description;
    private boolean status;
    private LocalDate createdIn;
    private LocalDate finishedIn;
    private int priority;

    public ToDo ( ToDoDTO input ) {
        this.taskName = input.taskName();
        this.description = input.description();
        this.status = input.status();
        this.createdIn = input.createdIn();
        this.finishedIn = input.finishedIn();
        this.priority = input.priority();
    }
}
