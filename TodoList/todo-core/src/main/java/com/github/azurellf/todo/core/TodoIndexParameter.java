package com.github.azurellf.todo.core;

import lombok.Getter;

public class TodoIndexParameter {

    @Getter
    private final long index;

    private TodoIndexParameter(final long index) {
        this.index = index;
    }

    public static TodoIndexParameter of(final long index) {
        if(index < 0) {
            throw new IllegalArgumentException("Todo index should be greater than 0");
        }
        return new TodoIndexParameter(index);
    }
}
