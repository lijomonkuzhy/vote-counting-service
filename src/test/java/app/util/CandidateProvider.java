package app.util;

import app.model.Candidate;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CandidateProvider {

    public static LinkedHashSet<Candidate> provideValidCandidates() {

        return Stream.of(Candidate.newBuilder().withName("Winery tour").withOption('A').build(),
                Candidate.newBuilder().withName("Ten pin bowling").withOption('B').build(),
                Candidate.newBuilder().withName("Movie night").withOption('C').build(),
                Candidate.newBuilder().withName("Dinner at a restaurant").withOption('D').build(),
                Candidate.newBuilder().withName("Art gallery visit").withOption('E').build(),
                Candidate.newBuilder().withName("Picnic in the park").withOption('F').build(),
                Candidate.newBuilder().withName("Horse riding lessons").withOption('G').build(),
                Candidate.newBuilder().withName("Museum visit").withOption('H').build(),
                Candidate.newBuilder().withName("Surfing lesson").withOption('I').build())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private CandidateProvider() {
        throw new AssertionError("Can not be instantiated");
    }
}
