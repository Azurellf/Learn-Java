package com.github.azurellf.todo.core;

import lombok.Getter;

import java.time.Instant;

public class TodoItem {

    @Getter
    private long index;

    @Getter
    private final String content;

    @Getter
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
    }

    public void assignIndex(final long index) {
        this.index = index;
    }

    public void markDone() {
        this.done = true;
    }

}
