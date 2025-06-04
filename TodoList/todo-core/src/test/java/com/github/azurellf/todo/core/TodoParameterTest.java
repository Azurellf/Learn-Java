package com.github.azurellf.todo.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class TodoParameterTest {

    @Test
    public void should_return_todo_parameter() {
        final TodoParameter todoParameter = TodoParameter.of("foo");
        assertThat(todoParameter.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_throw_exception_when_null_or_empty_content() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TodoParameter.of(""));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TodoParameter.of(null));
    }
}