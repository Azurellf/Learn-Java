package com.github.azurellf.todo.core;

import lombok.Getter;

public class TodoParameter {

    @Getter
    final private String content;

    public TodoParameter(final String content) {
        this.content = content;
    }
}
