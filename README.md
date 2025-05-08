# CommandRunner

This CommandRunner module is designed to run external commands, scripts, or binary files, and is ideal for use in monitoring systems. It allows you to execute commands to check system status, resource usage, and service health, while managing the output efficiently.




## Features

- **Execute system diagnostics commands like top, df, ps, etc.**
- **Pass arguments dynamically**
- **Define the working directory for execution**
- **Handle standard output and error separately for logging.**
- **Alerting mechanism for failures (e.g., service down).**
- **Integration with alerting systems like email, Slack, or webhook.**




## Example Usage

### Example Usage for Monitoring System Health:

```java
CommandConfig config = new CommandConfig(
    "/usr/bin/top",                             // Path to executable
    List.of("-n", "1"),                         // Arguments
    new File("/mnt/scripts"),                   // Working directory
    null                                        // No additional environment variables
);

OutputHandler handler = new DefaultOutputHandler();
ExternalCommandRunner runner = new DefaultCommandRunner(handler);

runner.runCommand(config);
```



### Monitor Service Status:
```java
CommandConfig config = new CommandConfig(
    "/usr/bin/systemctl",                       // Check the status of a service
    List.of("is-active", "apache2"),            // Arguments to check Apache status
    new File("/mnt/scripts"),
    null
);

runner.runCommand(config);
```




### Capturing Command Output

If you want to capture the output and process it further:

```java
String output = runner.runCommandAndCaptureOutput(config);
System.out.println("Command output:\n" + output);
```




## Class Overview

| Class/Interface           | Description                                                              |
|-------------------------- |--------------------------------------------------------------------------|
| `CommandConfig`           | Holds configuration: executable path, arguments, directory, environment. |
| `ExternalCommandRunner`   | Interface defining contract for running commands.                        |
| `DefaultCommandRunner`    | Default implementation; uses `CommandConfig` and `OutputHandler`.        |
| `OutputHandler`           | Interface for handling process output.                                   |
| `DefaultOutputHandler`    | Prints output/error streams to `System.out`/`System.err`.                |




## Requirements

- **Java 11 or newer**
- **Linux system** (or compatible Unix environment)




## Notes

- Ensure your executable or script has execute permissions:



