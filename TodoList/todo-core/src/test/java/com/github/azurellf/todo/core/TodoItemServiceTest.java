package com.github.azurellf.todo.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoItemServiceTest {
    private TodoItemService service;
    private TodoItemRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = mock(TodoItemRepository.class);
        this.service = new TodoItemService(this.repository);
    }

    @Test
    public void should_add_todo_item() {
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem item = service.addTodoItem(new TodoParameter("foo"));
        assertThat(item.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_throw_exception_for_null_todo_item() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addTodoItem(null));
    }

    @Test
    public void should_mark_todo_item_done() {
        TodoItem item = new TodoItem("foo");
        when(repository.findAll()).thenReturn(ImmutableList.of(item));

        Optional<TodoItem> todoItem = service.markTodoItemDone(TodoIndexParameter.of(item.getIndex()));

        assertThat(todoItem).isPresent();
        assertThat(todoItem.get().isDone()).isTrue();
    }

    @Test
    public void should_return_undo_items() {
        var item1 = new TodoItem("undo");
        var item2 = new TodoItem("done");
        item2.markDone();

        when(repository.findAll()).thenReturn(ImmutableList.of(item1, item2));
        List<TodoItem> items = service.list(false);
        assertThat(items).containsOnly(item1);
    }

    @Test
    public void should_return_all_items() {
        var item1 = new TodoItem("undo");
        var item2 = new TodoItem("done");
        item2.markDone();

        when(repository.findAll()).thenReturn(ImmutableList.of(item1, item2));
        List<TodoItem> items = service.list(true);
        assertThat(items).contains(item1,item2);
    }
}