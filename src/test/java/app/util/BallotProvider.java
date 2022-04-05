package app.util;

import app.model.Ballot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class BallotProvider {

    public static Set<Character> VALID_CANDIDATE_OPTIONS = Set.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I');

    public static List<Ballot> provideValidBallots() {
        return List.of(
                Ballot.newBuilder().withPreferences(List.of('A', 'B', 'D', 'C')).build(),
                Ballot.newBuilder().withPreferences(List.of('D', 'A')).build(),
                Ballot.newBuilder().withPreferences(List.of('B', 'A', 'D')).build(),
                Ballot.newBuilder().withPreferences(List.of('D', 'B')).build(),
                Ballot.newBuilder().withPreferences(List.of('C', 'A', 'B', 'D')).build(),
                Ballot.newBuilder().withPreferences(List.of('B', 'A', 'C')).build(),
                Ballot.newBuilder().withPreferences(List.of('C', 'D', 'A', 'B')).build(),
                Ballot.newBuilder().withPreferences(List.of('C', 'B', 'A', 'D')).build());
    }

    public static List<Ballot> provideValidBallots(final List<String> userInputs) {
        List<Ballot> ballots = new ArrayList<>();

        for (String userInput : userInputs) {
            ballots.add(Ballot.newBuilder().withPreferences(
                    userInput.chars().mapToObj(e -> (char) e).collect(Collectors.toList()))
                    .build());
        }
        return ballots;
    }

    private BallotProvider() {
        throw new AssertionError("Can not be instantiated");
    }
}
