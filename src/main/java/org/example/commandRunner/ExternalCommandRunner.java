package org.example.commandRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExternalCommandRunner {
    void runCommand(CommandConfig config) throws IOException;
}