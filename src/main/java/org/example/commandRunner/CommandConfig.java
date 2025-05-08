package org.example.commandRunner;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CommandConfig {
    private final String executablePath;
    private final List<String> args;
    private final File directory;
    private final Map<String, String> environment;

    public CommandConfig(String executablePath, List<String> args, File directory, Map<String, String> environment) {
        this.executablePath = executablePath;
        this.args = args;
        this.directory = directory;
        this.environment = environment;
    }

    public String getExecutablePath() {
        return executablePath;
    }

    public List<String> getArgs() {
        return args;
    }

    public File getDirectory() {
        return directory;
    }

    public Map<String, String> getEnvironment() {
        return environment;
    }
}
