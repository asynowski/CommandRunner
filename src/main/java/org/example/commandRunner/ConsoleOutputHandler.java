package org.example.commandRunner;

import java.io.*;
import java.util.concurrent.Executors;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void handle(Process process) throws IOException {
        Executors.newSingleThreadExecutor().submit(() -> streamOutput(process.getInputStream(), System.out));
        Executors.newSingleThreadExecutor().submit(() -> streamOutput(process.getErrorStream(), System.err));
    }

    private void streamOutput(InputStream inputStream, PrintStream output) {
        new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .forEach(output::println);
    }
}

