package com.gerenciador.toDoList.service;

import com.gerenciador.toDoList.dto.ToDoDTO;
import com.gerenciador.toDoList.entity.ToDo;
import com.gerenciador.toDoList.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public ToDo register ( ToDoDTO input ) {
        ToDo newTask = new ToDo( input.getTaskName(), input.getDescription(), true, input.getPriority());
        newTask.setCreatedIn(LocalDateTime.now());
        return repository.save(newTask);
    }

    public List <ToDo> listToDo( ) {
        return repository.findAll();

    }

    public void deleteToDoById( Long id ) {
        Optional<ToDo> task = repository.findById(id);

            if ( task.isPresent() ) {
                repository.delete(task.orElseThrow(() -> new RuntimeException("An error occur during the delete process")));
            } else {
                throw new RuntimeException("Error ");
            }
    }

    public ToDo editToDos(Long id, ToDoDTO input) {

        try {
            ToDo myTask = repository.findById( id ).orElseThrow(() -> new RuntimeException("To Do not found: " + id));

            myTask.setTaskName( input.getTaskName());
            myTask.setDescription( input.getDescription());
            myTask.setPriority( input.getPriority());

           return repository.save(myTask);

        } catch (RuntimeException e) {
            throw new RuntimeException("A error occur during the edit process: " + e.getMessage(), e);
        }
    }

    public ToDo finishedToDos(Long id) {

        try {
            ToDo completedTask = repository.findById( id ).orElseThrow(() -> new RuntimeException("To Do not found: " + id));

            completedTask.setStatus(false);
            completedTask.setFinishedIn(LocalDateTime.now());

            return repository.save(completedTask);

        } catch (RuntimeException e) {
            throw new RuntimeException("A error occur during the edit process: " + e.getMessage(), e);
        }

    }
}
