package app.repository;

import app.model.Ballot;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static app.util.console.ConsoleReader.retrieveBallotsFromConsole;
import static app.util.console.ConsoleWriter.printInfo;

public class BallotConsoleReader implements BallotReader {
    private final Scanner scanner;

    public BallotConsoleReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public List<Ballot> loadBallots(final Set<Character> options) {
        printInfo("Please enter 'tally' when everyone is done voting.");

        return retrieveBallotsFromConsole(options, scanner);
    }

}

