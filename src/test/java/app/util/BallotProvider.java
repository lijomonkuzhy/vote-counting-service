package app.util;

import app.model.Ballot;

import java.util.List;
import java.util.Set;

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

    private BallotProvider() {
        throw new AssertionError("Can not be instantiated");
    }

}
