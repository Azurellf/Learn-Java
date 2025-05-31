package com.github.azurellf.todo.core;

import java.util.List;
import java.util.Optional;

public class TodoItemService {
    private final TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter todoParameter) {
        if (todoParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }
        final TodoItem item = new TodoItem(todoParameter.getContent());
        return this.repository.save(item);
    }

    public Optional<TodoItem> markTodoItemDone(final TodoIndexParameter index) {
        final Optional<TodoItem> optionalItem = repository.findAll()
                .stream()
                .filter(item -> item.getIndex() == index.getIndex())
                .findFirst();
        optionalItem.ifPresent(TodoItem::markDone);
        return optionalItem;
    }

    public List<TodoItem> list(boolean showAll) {
        return repository.findAll()
                .stream()
                .filter(item -> showAll || !item.isDone())
                .toList();
    }
}
