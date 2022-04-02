package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteCounterTest {

    private VoteCounter voteCounter;

    @Mock
    private BallotLoader ballotLoaderMock;
    @Mock
    private CandidateLoader candidateLoaderMock;
    @Mock
    private PreferentialVoteCounter preferentialVoteCounter;

    @BeforeEach
    public void setUp() {
        voteCounter = new VoteCounter(candidateLoaderMock, preferentialVoteCounter, ballotLoaderMock);
    }

    @Test
    @DisplayName("Given valid ballots, Then method should return the winning candidate name")
    public void testFindTheWinnerSuccess() throws IOException, ServiceException {
        when(ballotLoaderMock.loadBallots()).thenReturn(provideBallots());

        assertThat(voteCounter.processVotes()).isEqualTo("Ten pin bowling");

    }

    @ParameterizedTest
    @MethodSource("provideEmptyAndNullCandidates")
    @DisplayName("Given no candidates for voting, Then method invocation should result in ServiceException")
    public void testFindTheWinnerFailsWithNoCandidates(Set<Candidate> candidates) throws IOException {
        when(candidateLoaderMock.loadCandidateList()).thenReturn(candidates);

        assertThatExceptionOfType(ServiceException.class)
                .isThrownBy(() -> voteCounter.processVotes())
                .withMessage("No Candidates available.");
    }

    @Test
    @DisplayName("Given IOException while reading candidate file, Then method invocation should result in IOException")
    public void testFindTheWinnerFailsWithIOException() throws IOException, ServiceException {
        when(candidateLoaderMock.loadCandidateList()).thenThrow(IOException.class);

        assertThatIOException().isThrownBy(() -> voteCounter.processVotes());
    }

    private static Stream<Set<Candidate>> provideEmptyAndNullCandidates() {
        return Stream.of(null, Set.of());
    }

    private List<Ballot> provideBallots() {
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


}