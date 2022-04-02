package app.service;

public class ConsoleWriter {
    public static void printInfo(final String message) {
        System.out.printf("%s%n", message);
    }

    public static void printError(Exception e) {
        System.out.printf("Error: %s%n", e);
        e.printStackTrace(); // Enable this to debug
    }

    private ConsoleWriter() {
        throw new AssertionError("Can not be instantiated.");
    }
}
