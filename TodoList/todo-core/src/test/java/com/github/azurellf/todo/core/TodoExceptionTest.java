package com.github.azurellf.todo.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class TodoExceptionTest {
    @Test
    void should_create_Todo_exception() {
        final TodoException exception = new TodoException("foo", new IllegalArgumentException());
        assertThat(exception).hasMessage("foo");
    }
}