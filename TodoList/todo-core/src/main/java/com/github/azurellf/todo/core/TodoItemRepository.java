package com.github.azurellf.todo.core;

import java.util.List;
import java.util.Optional;

public interface TodoItemRepository {
    TodoItem save(TodoItem item);

    List<TodoItem> findAll();
}
