package com.locar.locar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExeExecutor {

    // Helper class to hold execution results
    public static class ExecutionResult {
        public final String stdout;
        public final String stderr;

        public ExecutionResult(String stdout, String stderr) {
            this.stdout = stdout;
            this.stderr = stderr;
        }
    }

    // Main execution function
    public static ExecutionResult executeExe(String exePath, String[] args)
            throws IOException, InterruptedException {

        // Build command list
        List<String> command = new ArrayList<>();
        command.add(exePath);
        for (String arg : args) {
            command.add(arg);
        }

        // Start the process
        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();

        // Thread-safe string builders
        StringBuilder stdoutBuffer = new StringBuilder();
        StringBuilder stderrBuffer = new StringBuilder();

        // Thread to read stdout
        Thread stdoutThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stdoutBuffer.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Thread to read stderr
        Thread stderrThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stderrBuffer.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        stdoutThread.start();
        stderrThread.start();

        // Wait for process completion
        process.waitFor();

        // Wait for threads to finish
        stdoutThread.join();
        stderrThread.join();

        // Remove trailing newlines
        String stdout = stdoutBuffer.toString().trim();
        String stderr = stderrBuffer.toString().trim();

        return new ExecutionResult(stdout, stderr);
    }
}
