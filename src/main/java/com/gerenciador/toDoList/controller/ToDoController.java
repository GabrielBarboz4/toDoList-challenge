package com.gerenciador.toDoList.controller;

import com.gerenciador.toDoList.dto.ToDoDTO;
import com.gerenciador.toDoList.entity.ToDo;
import com.gerenciador.toDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity <ToDo> registerTodo (@RequestBody ToDoDTO input ) {
        ToDo toDo = service.register( input );

        return ResponseEntity.ok(toDo);
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
        service.deleteByToDo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <ToDo> updateToDos (@PathVariable Long id, @RequestBody ToDoDTO input) {

        try {
            service.editToDos( id, input );
            return ResponseEntity.ok(new ToDo(input));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
