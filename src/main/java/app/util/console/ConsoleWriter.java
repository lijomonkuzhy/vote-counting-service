package app.util.console;

import java.util.LinkedHashSet;

public class ConsoleWriter {
    public static void printInfo(final String message) {
        System.out.printf("%s%n", message);
    }

    public static void printError(Exception e) {
        System.out.printf("Error: %s%n", e);
        e.printStackTrace(); // Enable this to debug
    }

    public static <T> void printOptions(final LinkedHashSet<T> options) {
        System.out.println("List of options: ");
        for (T option : options) {
            System.out.println(option);
        }
    }

    private ConsoleWriter() {
        throw new AssertionError("Can not be instantiated.");
    }
}
