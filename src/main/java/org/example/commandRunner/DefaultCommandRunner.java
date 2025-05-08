package org.example.commandRunner;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;

public class DefaultCommandRunner implements ExternalCommandRunner {

    private final OutputHandler outputHandler;

    public DefaultCommandRunner(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    @Override
    public void runCommand(CommandConfig config) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(buildCommand(config));
        builder.directory(config.getDirectory());
        if (config.getEnvironment() != null) {
            builder.environment().putAll(config.getEnvironment());
        }

        System.out.println("Executing command: " + builder.command());
        System.out.println("In directory: " + builder.directory());

        Process process = builder.start();

        outputHandler.handle(process);

        try {
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Execution interrupted", e);
        }
    }

    private List<String> buildCommand(CommandConfig config) {
        List<String> command = new ArrayList<>();
        command.add(config.getExecutablePath());
        command.addAll(config.getArgs());
        return command;
    }
}
