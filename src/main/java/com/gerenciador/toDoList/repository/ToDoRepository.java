package com.gerenciador.toDoList.repository;

import com.gerenciador.toDoList.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
