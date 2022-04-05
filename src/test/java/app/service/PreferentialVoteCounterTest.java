package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;

import static app.util.BallotProvider.provideValidBallots;
import static app.util.CandidateProvider.provideValidCandidates;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PreferentialVoteCounterTest {
    private static final LinkedHashSet<Candidate> VALID_CANDIDATES = provideValidCandidates();
    private static List<Ballot> VALID_BALLOTS = provideValidBallots();
    private PreferentialVoteCounter preferentialVoteCounter;

    @BeforeEach
    public void setUp() {
        preferentialVoteCounter = new PreferentialVoteCounter();
    }

    @Test
    @DisplayName("Given valid ballots and candidates, Then the method should return the winner.")
    public void testFindTheWinnerSuccess() throws ServiceException {
        List<Ballot> ballots = provideValidBallots(List.of("ABDC", "DA", "BAD", "DB", "CABD", "BAC", "CDAB", "CBAD"));
        assertThat(preferentialVoteCounter.findTheWinner(VALID_CANDIDATES, ballots)).isEqualTo("B. Ten pin bowling");
    }

    @Test
    @DisplayName("Given that  all the candidates have just one ballot allocated to them with a single preference.\n" +
            "Then the winner is selected at random.")
    public void testFindTheWinnerWithJustOneBallot() throws ServiceException {
        List<Ballot> ballots = provideValidBallots(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I"));
        assertThat(preferentialVoteCounter.findTheWinner(VALID_CANDIDATES, ballots)).isEqualTo("A. Winery tour");
    }

    @Test
    @DisplayName("Given that one candidate has the quota to win in the first round.\n" +
            "Then the candidate with the quota is identified as the winner.")
    public void testFindTheWinnerWithCandidateMeetsQuotaInFirstRound() throws ServiceException {
        List<Ballot> ballots = provideValidBallots(List.of("ABDC", "BAD", "C", "CABD", "BAC", "CDAB", "CBAD"));
        assertThat(preferentialVoteCounter.findTheWinner(VALID_CANDIDATES, ballots)).isEqualTo("C. Movie night");
    }

    @Test
    @DisplayName("Given that  more than one candidate has the least number of ballots.\n" +
            "Then their ballots will be reassigned to the next available preference to identify the winner.")
    public void testFindTheWinnerWithManyCandidatesHaveTheLeastNumberOfBallots() throws ServiceException {
        List<Ballot> ballots = provideValidBallots(List.of("ABCD", "ACD", "BCD", "ECD", "DCA"));
        assertThat(preferentialVoteCounter.findTheWinner(VALID_CANDIDATES, ballots)).isEqualTo("D. Dinner at a restaurant");
    }

    @Test
    @DisplayName("Given that two candidates have an equal number of ballots after all other candidates are eliminated,  and they do not have at least as many votes as the quota.\n" +
            "Then the winner is selected at random.")
    public void testFindTheWinnerWithWithTwoCandidtesHaveSameCount() throws ServiceException {
        List<Ballot> ballots = provideValidBallots(List.of("AB", "AC", "A", "BG", "BG", "BG", "CD"));
        assertThat(preferentialVoteCounter.findTheWinner(VALID_CANDIDATES, ballots)).isEqualTo("A. Winery tour");
    }
}