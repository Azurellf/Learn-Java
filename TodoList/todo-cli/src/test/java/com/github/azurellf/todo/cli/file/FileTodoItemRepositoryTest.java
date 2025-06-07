package com.github.azurellf.todo.cli.file;

import com.github.azurellf.todo.core.TodoException;
import com.github.azurellf.todo.core.TodoItem;
import com.github.azurellf.todo.util.Jsons;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class FileTodoItemRepositoryTest {
    @TempDir
    File tempDir;
    private File tempFile;

    private FileTodoItemRepository repository;
    @BeforeEach
    void setUp() throws IOException {
        this.tempFile = File.createTempFile("file","", tempDir);
        this.repository = new FileTodoItemRepository(tempFile);
    }

    @Test
    void should_throw_exception_when_save_null_item() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> repository.save(null));
    }

    @Test
    void should_find_saved_file() {
        repository = new FileTodoItemRepository(tempFile);
        repository.save(new TodoItem("foo"));
        repository.save(new TodoItem("bar"));
        final Iterable<TodoItem> items = repository.findAll();
        assertThat(items).hasSize(2);
        final TodoItem firstItem = Iterables.get(items, 0);
        assertThat(firstItem.getContent()).isEqualTo("foo");
        assertThat(firstItem.getIndex()).isEqualTo(1);
        final TodoItem secondItem = Iterables.get(items, 1);
        assertThat(secondItem.getContent()).isEqualTo("bar");
        assertThat(secondItem.getIndex()).isEqualTo(2);
    }

    @Test
    public void should_update_saved_items() {
        repository = new FileTodoItemRepository(tempFile);
        repository.save(new TodoItem("foo"));
        repository.save(new TodoItem("bar"));
        final Iterable<TodoItem> items = repository.findAll();
        final TodoItem toUpdate = Iterables.get(items, 0);
        toUpdate.markDone();

        repository.save(toUpdate);

        final Iterable<TodoItem> updated = repository.findAll();
        assertThat(updated).hasSize(2);
        assertThat(Iterables.get(items, 0).isDone()).isTrue();
    }

    @Test
    void should_find_nothing() {
        Iterable<TodoItem> all = repository.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    void should_throw_exception_when_toObjects_fail() {
        String foo = "foo";
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(tempFile, StandardCharsets.UTF_8))) { // FileWriter(File, Charset) for modern Java
            writer.write(foo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThatExceptionOfType(TodoException.class)
                .isThrownBy(() -> Jsons.toObjects(tempFile));
    }
}