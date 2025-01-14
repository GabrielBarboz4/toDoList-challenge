package com.gerenciador.toDoList.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class ToDo {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String taskName;
    private String description;
    private boolean status;
    private LocalDateTime createdIn;
    private LocalDateTime finishedIn;
    private int priority;

    public ToDo () {}

    public ToDo(@NotBlank String taskName, String description, boolean status, int priority) {
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public LocalDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn) {
        this.createdIn = createdIn;
    }

    public LocalDateTime getFinishedIn() {
        return finishedIn;
    }

    public void setFinishedIn(LocalDateTime finishedIn) {
        this.finishedIn = finishedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
