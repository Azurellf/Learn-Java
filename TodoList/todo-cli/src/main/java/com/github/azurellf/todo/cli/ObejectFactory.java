package com.github.azurellf.todo.cli;

import com.github.azurellf.todo.cli.file.FileTodoItemRepository;
import com.github.azurellf.todo.core.TodoItemRepository;
import com.github.azurellf.todo.core.TodoItemService;
import picocli.CommandLine;

import java.io.File;

public class ObejectFactory {
    public CommandLine createCommandLine(final File repositoryFile) {
        return new CommandLine(createTodoCommand(repositoryFile));
    }

    private TodoCommand createTodoCommand(final File repositoryFile) {
        TodoItemService service = createServide(repositoryFile);
        return new TodoCommand(service);
    }


    public TodoItemService createServide(final File repositoryFile) {
        TodoItemRepository repository = new FileTodoItemRepository(repositoryFile);
        return new TodoItemService(repository);
    }
}
