DELETE FROM tasks;

INSERT INTO tasks (id, task_name, description, status, priority)
VALUES (1, 'Teste', 'Curtir', true, 1),
       (2, 'Teste', 'Comentar', true, 1),
       (3, 'Teste', 'Compartilhar', true, 1),
       (4, 'Teste', 'Se Inscrever', true, 1),
       (5, 'Teste', 'Ativar as Notificações', true, 1);
