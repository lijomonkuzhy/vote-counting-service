package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import app.repository.BallotReader;
import app.repository.CandidateFileReader;
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
import static app.util.BallotProvider.provideValidBallots;
import static app.util.CandidateProvider.provideValidCandidates;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotingSystemProcessorTest {

    private VotingSystemProcessor votingSystemProcessor;

    @Mock
    private BallotReader ballotReaderMock;
    @Mock
    private CandidateFileReader candidateFileReaderMock;
    @Mock
    private VoteCountingStrategyFactory voteCountingStrategyFactory;

    @BeforeEach
    public void setUp() {
        votingSystemProcessor = new VotingSystemProcessor(candidateFileReaderMock, voteCountingStrategyFactory, ballotReaderMock);
    }

    @Test
    @DisplayName("Given valid ballots, Then method should return the winning candidate name")
    public void testFindTheWinnerSuccess() throws Exception {
        LinkedHashSet<Candidate> candidates = provideValidCandidates();
        List<Ballot> ballots = provideValidBallots();
        when(candidateFileReaderMock.loadCandidateList(anyString())).thenReturn(candidates);
        when(ballotReaderMock.loadBallots(VALID_CANDIDATE_OPTIONS)).thenReturn(ballots);
        when(voteCountingStrategyFactory.provideVoteCounter("preferentialVoteCounter")).thenReturn(new PreferentialVoteCounter());

        assertThat(votingSystemProcessor.processVotes("preferentialVoteCounter")).isEqualTo("B. Ten pin bowling");
    }

    @ParameterizedTest
    @MethodSource("provideEmptyAndNullCandidates")
    @DisplayName("Given no candidates for voting, Then method invocation should result in ServiceException")
    public void testFindTheWinnerFailsWithNoCandidates(LinkedHashSet<Candidate> candidates) throws IOException {
        when(candidateFileReaderMock.loadCandidateList(anyString())).thenReturn(candidates);

        assertThatExceptionOfType(ServiceException.class)
                .isThrownBy(() -> votingSystemProcessor.processVotes("preferentialVoteCounter"))
                .withMessage("No Candidates available.");
    }

    @Test
    @DisplayName("Given IOException while reading candidate file, Then method invocation should result in IOException")
    public void testFindTheWinnerFailsWithIOException() throws IOException, ServiceException {
        when(candidateFileReaderMock.loadCandidateList(anyString())).thenThrow(IOException.class);

        assertThatIOException().isThrownBy(() -> votingSystemProcessor.processVotes("preferentialVoteCounter"));
    }

    private static Stream<LinkedHashSet<Candidate>> provideEmptyAndNullCandidates() {
        return Stream.of(null, new LinkedHashSet<>());
    }

}