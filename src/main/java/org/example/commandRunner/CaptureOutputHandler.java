package org.example.commandRunner;

import java.io.*;

public class CaptureOutputHandler implements OutputHandler {

    private String stdout;
    private String stderr;

    @Override
    public void handle(Process process) throws IOException {
        stdout = readStream(process.getInputStream());
        stderr = readStream(process.getErrorStream());

        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Command failed:\n" + stderr);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Execution interrupted", e);
        }
    }

    public String getStdout() {
        return stdout;
    }

    private String readStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        }
    }
}
