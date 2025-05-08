package org.example.commandRunner;

import java.io.IOException;

public interface OutputHandler {
    void handle(Process process) throws IOException;
}
