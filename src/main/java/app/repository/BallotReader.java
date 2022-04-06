package app.repository;

import app.model.Ballot;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static app.util.console.ConsoleReader.retrieveBallotsFromConsole;
import static app.util.console.ConsoleWriter.printInfo;
import static app.util.validator.BallotValidator.isValidBallot;

public class BallotReader {
    private final Scanner scanner;

    public BallotReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public List<Ballot> loadBallots(final Set<Character> options) {
        printInfo("Please enter 'tally' when everyone is done voting.");

        return retrieveBallotsFromConsole(options, scanner);
    }

}

