package app.util.console;

import app.model.Ballot;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

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

    public static void printCandidateBallotCount(final Map<Character, List<Ballot>> candidateToBallotsMap) {
        printInfo("The number of votes currently assigned to each candidate are: ");
        candidateToBallotsMap.forEach((key, value) -> printInfo(String.format("%s has %s ballots.", key, value.size())));
    }

    private ConsoleWriter() {
        throw new AssertionError("Can not be instantiated.");
    }
}
