package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import app.repository.CandidateFileReader;
import app.repository.BallotReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

import static app.util.BallotProvider.VALID_CANDIDATE_OPTIONS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteCounterTest {

    private VoteCounter voteCounter;

    @Mock
    private BallotReader ballotReaderMock;
    @Mock
    private CandidateFileReader candidateFileReaderMock;
    @Mock
    private PreferentialVoteCounter preferentialVoteCounter;

    @BeforeEach
    public void setUp() {
        voteCounter = new VoteCounter(candidateFileReaderMock, preferentialVoteCounter, ballotReaderMock);
    }

    @Test
    @DisplayName("Given valid ballots, Then method should return the winning candidate name")
    public void testFindTheWinnerSuccess() throws IOException, ServiceException {
        when(ballotReaderMock.loadBallots(VALID_CANDIDATE_OPTIONS)).thenReturn(provideBallots());

        assertThat(voteCounter.processVotes()).isEqualTo("Ten pin bowling");
    }

    @ParameterizedTest
    @MethodSource("provideEmptyAndNullCandidates")
    @DisplayName("Given no candidates for voting, Then method invocation should result in ServiceException")
    public void testFindTheWinnerFailsWithNoCandidates(LinkedHashSet<Candidate> candidates) throws IOException {
        when(candidateFileReaderMock.loadCandidateList(anyString())).thenReturn(candidates);

        assertThatExceptionOfType(ServiceException.class)
                .isThrownBy(() -> voteCounter.processVotes())
                .withMessage("No Candidates available.");
    }

    @Test
    @DisplayName("Given IOException while reading candidate file, Then method invocation should result in IOException")
    public void testFindTheWinnerFailsWithIOException() throws IOException, ServiceException {
        when(candidateFileReaderMock.loadCandidateList(anyString())).thenThrow(IOException.class);

        assertThatIOException().isThrownBy(() -> voteCounter.processVotes());
    }

    private static Stream<LinkedHashSet<Candidate>> provideEmptyAndNullCandidates() {
        return Stream.of(null, new LinkedHashSet<Candidate>());
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