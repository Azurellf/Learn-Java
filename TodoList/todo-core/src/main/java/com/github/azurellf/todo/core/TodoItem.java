package com.github.azurellf.todo.core;

import lombok.Getter;

import java.time.Instant;

public class TodoItem {

    @Getter
    private final String content;

    @Getter
    private final long index;

    @Getter
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
        this.index = Instant.now().toEpochMilli();
    }

    public void markDone() {
        this.done = true;
    }

}
