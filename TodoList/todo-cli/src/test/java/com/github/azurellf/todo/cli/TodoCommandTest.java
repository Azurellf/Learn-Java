package com.github.azurellf.todo.cli;

import com.github.azurellf.todo.core.TodoItem;
import com.github.azurellf.todo.core.TodoItemService;
import com.github.azurellf.todo.core.TodoParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TodoCommandTest {

    @TempDir
    File tempDir;
    private TodoItemService service;
    private CommandLine cli;

    @BeforeEach
    void setUp() {
        final ObejectFactory factory = new ObejectFactory();
        final File repositoryFile = new File(tempDir, "repository.json");
        service = factory.createServide(repositoryFile);
        cli = factory.createCommandLine(repositoryFile);
    }

    @Test
    void should_add_todo_item() {
        final int result = cli.execute("add", "foo");
        assertThat(result).isEqualTo(0);
        final List<TodoItem> items = service.list(true);
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getContent()).isEqualTo("foo");
    }

    @Test
    void should_fail_to_add_empty_item() {
        assertThat(cli.execute("todo","")).isNotEqualTo(0);
    }

    @Test
    void should_mark_item_done() {
        service.addTodoItem(TodoParameter.of("foo"));
        final int result = cli.execute("done", "1");

        assertThat(result).isEqualTo(0);

        final List<TodoItem> items = service.list(true);
        assertThat(items.get(0).isDone()).isTrue();
        assertThat(items.get(0).getContent()).isEqualTo("foo");
    }

    @Test
    void should_fail_to_mark_with_illegal_index() {
        assertThat(cli.execute("done", "0")).isNotEqualTo(0);
        assertThat(cli.execute("done", "-1")).isNotEqualTo(0);
    }

    @Test
    void should_fail_to_mark_unknown_todo_item() {
        assertThat(cli.execute("done", "1")).isNotEqualTo(0);
    }
}