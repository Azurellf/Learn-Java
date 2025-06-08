package com.github.azurellf.todo.bootstrap;

import com.github.azurellf.todo.cli.ObejectFactory;
import picocli.CommandLine;

import javax.naming.spi.ObjectFactory;
import java.io.File;

public class Bootstrap {
    public static void main(final String[] args) {
        final File todoRepository = new File(todoHome(), "repository.json");
        ObejectFactory factory = new ObejectFactory();
        final CommandLine commandLine = factory.createCommandLine(todoRepository);
        commandLine.execute(args);
    }

    private static File todoHome() {
        final File homeDirectory = new File(System.getProperty("user.home"));
        final File todoHome = new File(homeDirectory, ".todo");
        if (!todoHome.exists()) {
            todoHome.mkdirs();
        }
        return todoHome;
    }
}
