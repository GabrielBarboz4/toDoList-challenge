package com.gerenciador.toDoList;

import com.gerenciador.toDoList.entity.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TestConstants {

    public static final List<ToDo> TODOS = new ArrayList<>() {
        {
            add(new ToDo("Teste", "Curtir", true, 1));
            add(new ToDo("Teste", "Comentar", true, 1));
            add(new ToDo("Teste", "Compartilhar", true, 1));
            add(new ToDo("Teste", "Se Inscrever", true, 1));
            add(new ToDo("Teste", "Ativar as Notificações", true, 1));
        }
    };

    public static final ToDo TO_DO = TODOS.get(0);
}
