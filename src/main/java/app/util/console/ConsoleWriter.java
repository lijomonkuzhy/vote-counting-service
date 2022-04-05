package app.util.console;

import java.util.LinkedHashSet;

public class ConsoleWriter {
    public static void printInfo(final String message) {
        System.out.printf("%n%s%n", message);
    }

    public static void printError(Exception e) {
        System.out.printf("Error: %s%n", e);
        e.printStackTrace(); // Enable this to debug
    }

    public static <T> void printOptions(final LinkedHashSet<T> options) {
        printInfo("Please vote from the Candidate options in preference order by typing in a letter sequence on a single line.");
        for (T option : options) {
            System.out.println(option);
        }
    }

    public static <T> void printCandidateVoteCont(){

    }

    private ConsoleWriter() {
        throw new AssertionError("Can not be instantiated.");
    }
}
