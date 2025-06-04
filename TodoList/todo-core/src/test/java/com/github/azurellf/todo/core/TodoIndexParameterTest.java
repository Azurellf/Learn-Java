package com.github.azurellf.todo.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;

class TodoIndexParameterTest {

    @Test
    public void should_return_todo_index_parameter() {
        TodoIndexParameter todoIndexParameter = TodoIndexParameter.of(1);
        assertThat(todoIndexParameter.getIndex()).isEqualTo(1);
    }

    @Test
    public void should_throw_exception_when_index_is_zero_or_negative() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TodoIndexParameter.of(0));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TodoIndexParameter.of(-1));
    }
}