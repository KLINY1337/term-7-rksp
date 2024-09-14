package org.example;

import java.io.IOException;

public class Practice2 {
    public static void runCode() throws IOException, InterruptedException {
        FileReaderNIO.readFile();

        measureExecutionTime(FileCopyStream::copyFile, "FileCopyStream.copyFile()");
        measureExecutionTime(FileCopyChannel::copyFile, "FileCopyChannel.copyFile()");
        measureExecutionTime(FileCopyApache::copyFile, "FileCopyApache.copyFile()");
        measureExecutionTime(FileCopyNIO::copyFile, "FileCopyNIO.copyFile()");

        CheckSumCalculator.calculateCheckSum();

        DirectoryWatcher.watchDirectory();
    }

    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws IOException;
    }

    private static void measureExecutionTime(ThrowingRunnable task, String taskName) {
        long startTime = System.currentTimeMillis();
        try {
            task.run();
        } catch (IOException e) {
            System.err.println(taskName + " failed with IOException: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        System.out.println(taskName + " took " + (endTime - startTime) + " milliseconds");
    }

}