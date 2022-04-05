package app.repository;

import app.model.Ballot;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static app.util.console.ConsoleWriter.printInfo;
import static app.util.validator.BallotValidator.isValidBallot;

public class BallotReader {
    private Scanner scanner;

    public BallotReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public List<Ballot> loadBallots(final Set<Character> options) throws IOException {
        printInfo("Please enter 'tally' when everyone is done voting.");

        return retrieveBallotsFromConsole(options);
    }

    private List<Ballot> retrieveBallotsFromConsole(final Set<Character> ocandidateOptions) throws IOException {

        final List<Ballot> ballots = new ArrayList<>();

        String inputLine;

        while (scanner.hasNext()) {
            inputLine = scanner.next().replaceAll("\\s", "");
            if (inputLine.equalsIgnoreCase("tally")) {
                printInfo("Thanks for the votes. No more votes allowed.");
                return ballots;

            } else if (isValidBallot(inputLine, ocandidateOptions)) {
                ballots.add(Ballot.newBuilder().withPreferences(
                        inputLine.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedList::new)))
                        .build());
            }
        }
        return Collections.unmodifiableList(ballots);
    }


}

