package com.gerenciador.toDoList.service;

import com.gerenciador.toDoList.dto.ToDoDTO;
import com.gerenciador.toDoList.entity.ToDo;
import com.gerenciador.toDoList.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public ToDo register ( ToDoDTO input ) {
        return repository.save( new ToDo( input ));
    }

    public List <ToDo> listToDo( ) {
        return repository.findAll();

    }

    public void deleteByToDo( Long id ) {
        repository.deleteById(id);
    }

    public void editToDos(Long id, ToDoDTO input) {

        try {
            ToDo myTodo = repository.findById( id ).orElseThrow(() -> new RuntimeException("To Do not found: " + id));

            myTodo.setTaskName( input.taskName() );
            myTodo.setDescription( input.description() );
            myTodo.setStatus( input.status() );
            myTodo.setCreatedIn( input.createdIn() );
            myTodo.setFinishedIn( input.finishedIn() );
            myTodo.setPriority( input.priority() );

            repository.save( myTodo );

        } catch (RuntimeException e) {
            throw new RuntimeException("A error occur during the edit process: " + e.getMessage(), e);
        }
    }
}
