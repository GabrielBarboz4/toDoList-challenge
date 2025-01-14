package com.gerenciador.toDoList;

import com.gerenciador.toDoList.entity.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoListApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
	void testCreateTodoSuccess() {
        var expectedToDo = new ToDo("todo 1", "desc 1", true, 1);

        webTestClient
                .post()
                .uri("/todo/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(expectedToDo)
                .exchange()
                .expectStatus().isCreated() // Expect 201 CREATED
                .expectBody()
                .jsonPath("$.taskName").isEqualTo(expectedToDo.getTaskName())
                .jsonPath("$.description").isEqualTo(expectedToDo.getDescription())
                .jsonPath("$.status").isEqualTo(expectedToDo.isStatus())
                .jsonPath("$.priority").isEqualTo(expectedToDo.getPriority());
    }


	@Test
	void testCreateTodoFailure() {
        webTestClient
                .post()
                .uri("/todo/register")
                .bodyValue(
                        new ToDo("", "", false, 0)
                )
                .exchange()
                .expectStatus().is5xxServerError();
	}

    @Sql("/import.sql")
    @Test
    void testUpdateTodoSuccess() {
        Long idToUpdate = 1L;
        ToDo testTaskUpdate = new ToDo("Updated Task", "Updated Description", true, 2);

        webTestClient
                .put()
                .uri("/todo/update/{id}", idToUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(testTaskUpdate)
                .exchange()
                .expectStatus().isOk() // Expect HTTP 200
                .expectBody()
                .jsonPath("$.taskName").isEqualTo(testTaskUpdate.getTaskName())
                .jsonPath("$.description").isEqualTo(testTaskUpdate.getDescription())
                .jsonPath("$.status").isEqualTo(testTaskUpdate.isStatus())
                .jsonPath("$.priority").isEqualTo(testTaskUpdate.getPriority());
    }

    @Test
    void testUpdateTodoNotFound() {
        Long nonExistentId = 999L;
        ToDo failedUpdateTodo = new ToDo("Updated Task", "Updated Description", true, 2);

        webTestClient
                .put()
                .uri("/todo/update/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(failedUpdateTodo)
                .exchange()
                .expectStatus().isNotFound(); // Expect HTTP 404
    }

    @Sql("/import.sql")
    @Test
    void testDeleteTodoSuccess() {
        Long idToUpdate = 1L; //Fake id

        webTestClient
                .delete()
                .uri("/todo/{id}", idToUpdate)
                .exchange()
                .expectStatus().isOk(); // Expect HTTP 200
    }


    @Sql("/import.sql")
    @Test
    void testDeleteTodoFailure() {
        Long idToUpdate = 999L; //Fake id

        webTestClient
                .delete()
                .uri("/todo/{id}", idToUpdate)
                .exchange()
                .expectStatus().isNotFound(); // Expect HTTP 404
    }

    @Sql("/import.sql")
    @Test
    public void testListTodos() {
        var responseSpec = webTestClient
                .get()
                .uri("/todo/list")
                .exchange()
                .expectStatus().isOk() // Expect HTTP 200
                .expectBody();

        for (int i = 0; i < TestConstants.TODOS.size(); i++) {
            responseSpec
                    .jsonPath("$[" + i + "].taskName").isEqualTo(TestConstants.TODOS.get(i).getTaskName())
                    .jsonPath("$[" + i + "].description").isEqualTo(TestConstants.TODOS.get(i).getDescription())
                    .jsonPath("$[" + i + "].status").isEqualTo(TestConstants.TODOS.get(i).isStatus())
                    .jsonPath("$[" + i + "].priority").isEqualTo(TestConstants.TODOS.get(i).getPriority());
        }
    }
}
