package com.gerenciador.toDoList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ToDoDTO(
         String taskName,
         String description,
         boolean status,
         @JsonFormat(pattern = "yyyy-MM-dd") LocalDate createdIn,
         @JsonFormat(pattern = "yyyy-MM-dd") LocalDate finishedIn,
         int priority
) {}