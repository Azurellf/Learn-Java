package com.github.azurellf.todo.core;

import lombok.Getter;

public class TodoItem {

    @Getter
    private final String content;

    @Getter
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

}
