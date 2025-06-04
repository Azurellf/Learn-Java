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
        TodoItem item = service.addTodoItem(TodoParameter.of("foo"));
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
        when(repository.save(any())).then(returnsFirstArg());

        Optional<TodoItem> todoItem = service.markTodoItemDone(TodoIndexParameter.of(1));

        assertThat(todoItem).isPresent();
        final TodoItem actual = todoItem.get();
        assertThat(actual.isDone()).isTrue();
    }

    @Test
    public void should_not_mark_item_for_out_of_scope_index() {
        TodoItem item = new TodoItem("foo");
        when(repository.findAll()).thenReturn(ImmutableList.of(item));

        Optional<TodoItem> todoItem = service.markTodoItemDone(TodoIndexParameter.of(2));
        assertThat(todoItem).isEmpty();
    }

    @Test
    public void should_return_all_item() {
        var item1 = new TodoItem("undo");
        item1.assignIndex(1);
        var item2 = new TodoItem("done");
        item2.assignIndex(2);
        item2.markDone();

        when(repository.findAll()).thenReturn(ImmutableList.of(item1, item2));
        List<TodoItem> items = service.list(true);
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getIndex()).isEqualTo(1);
        assertThat(items.get(1).getIndex()).isEqualTo(2);
    }

    @Test
    public void should_return_empty_list_when_have_no_item() {
        when(repository.findAll()).thenReturn(ImmutableList.of());
        List<TodoItem> items = service.list(true);
        assertThat(items).isEmpty();
    }

    @Test
    public void should_return_all_undo_items() {
        TodoItem doneItem = new TodoItem("done");
        doneItem.assignIndex(1);
        doneItem.markDone();
        TodoItem undoItem = new TodoItem("undo");
        undoItem.assignIndex(2);

        when(repository.findAll()).thenReturn(ImmutableList.of(doneItem, undoItem));
        List<TodoItem> undoItems = service.list(false);

        assertThat(undoItems).hasSize(1);
        assertThat(undoItems.getFirst().getIndex()).isEqualTo(2);
        assertThat(undoItems.getFirst().getContent()).isEqualTo("undo");
    }

    @Test
    public void should_return_empty_list_when_have_no_undo_item() {
        TodoItem doneItem = new TodoItem("done");
        doneItem.markDone();

        when(repository.findAll()).thenReturn(ImmutableList.of(doneItem));
        List<TodoItem> undoItems = service.list(false);

        assertThat(undoItems).isEmpty();
    }
}