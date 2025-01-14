package com.gerenciador.toDoList.controller;

import com.gerenciador.toDoList.dto.ToDoDTO;
import com.gerenciador.toDoList.entity.ToDo;
import com.gerenciador.toDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todo")
@RestController
public class ToDoController {

    @Autowired
    private final ToDoService service;

    public ToDoController ( ToDoService service ) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity <ToDo> registerTodo ( @RequestBody ToDoDTO input ) {
        ToDo toDo = service.register( input );

        return ResponseEntity.status(HttpStatus.CREATED).body(toDo);
    }

    @GetMapping("/list")
    public ResponseEntity <List<ToDo>> listAll () {
        List<ToDo> allItems = service.listToDo();

        if (allItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteToDo (@PathVariable Long id) {
        try {
            service.deleteToDoById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <ToDo> updateToDos (@PathVariable Long id, @RequestBody ToDoDTO input) {

        try {
            ToDo updatedTasK = service.editToDos( id, input );
            return ResponseEntity.ok(updatedTasK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/finished/{id}")
    public ResponseEntity <ToDo> finishedToDos (@PathVariable Long id) {

        try {
            ToDo completedTask = service.finishedToDos(id);
            return ResponseEntity.ok(completedTask);
        } catch ( RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
