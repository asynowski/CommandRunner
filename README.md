# CommandRunner

A flexible Java module for running external binaries, scripts, or shell commands on Linux systems. Built with a clear, SOLID design, it enables robust command execution with full control over arguments, working directories, and environment variables.

---

## Features

- **Execute any external command or script**
- **Pass arguments dynamically**
- **Define the working directory for execution**
- **Inject additional environment variables**
- **Capture and handle standard output and error streams separately**
- **Follows SOLID principles** (single responsibility, open/closed, interface segregation, etc.)

---

## Example Usage

### Running a Command with Arguments

```java
CommandConfig config = new CommandConfig(
  ./runFile, // Path to executable or script
  List.of("arg1", "arg2", "arg3"), // Arguments
  new File("/mnt/scripts"), // Working directory

  Map.of( // (Optional) environment variables
    "LD_LIBRARY_PATH", "/path/to/lib",
    "DB2_CLI_DRIVER_INSTALL_PATH", "/path/to/second/lib"
)
);

OutputHandler handler = new DefaultOutputHandler(); // Handles stdout and stderr
ExternalCommandRunner runner = new DefaultCommandRunner(handler);

runner.runCommand(config);
```
---

### Running a Command Without Arguments
```java
CommandConfig config = new CommandConfig(
  ./runFile,
  List.of(),
  new File("/mnt/scripts"),
  null // No extra environment variables
);

runner.runCommand(config);
```

---

### Capturing Command Output

If you want to capture the output and process it further:

```java
String output = runner.runCommandAndCaptureOutput(config);
System.out.println("Command output:\n" + output);
```

---

## Class Overview

| Class/Interface           | Description                                                              |
|-------------------------- |--------------------------------------------------------------------------|
| `CommandConfig`           | Holds configuration: executable path, arguments, directory, environment. |
| `ExternalCommandRunner`   | Interface defining contract for running commands.                        |
| `DefaultCommandRunner`    | Default implementation; uses `CommandConfig` and `OutputHandler`.        |
| `OutputHandler`           | Interface for handling process output.                                   |
| `DefaultOutputHandler`    | Prints output/error streams to `System.out`/`System.err`.                |

---

## Requirements

- **Java 11 or newer**
- **Linux system** (or compatible Unix environment)

---

## Notes

- Ensure your executable or script has execute permissions:



