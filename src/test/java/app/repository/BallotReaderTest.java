package app.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static app.util.BallotProvider.VALID_CANDIDATE_OPTIONS;
import static app.util.BallotProvider.provideValidBallots;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BallotConsoleReaderTest {
    private BallotConsoleReader ballotReader;

    @Test
    @DisplayName("Given valid preferences,Then method should create valid ballots corresponding to given input.")
    public void testLoadBallotsSuccess() throws IOException {
        final String inputData = "ABDC\n" +
                "DA\n" +
                "BAD\n" +
                "DB\n" +
                "CABD\n" +
                "BAC\n" +
                "CDAB\n" +
                "CBAD\n" +
                "tally";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        ballotReader = new BallotConsoleReader(inputStream);

        assertThat(ballotReader.loadBallots(VALID_CANDIDATE_OPTIONS)).isEqualTo(provideValidBallots());
    }

    @Test
    @DisplayName("Given a ballot with white space,Then method should ignore white spaces.")
    public void testLoadBallotsWithWhiteSpace() throws IOException {
        final String inputData = "ABDC\n" +
                "DA\n" +
                "BAD\n" +
                "DB\n  " +
                "CABD\n" +
                "BAC\n  " +
                "CDAB\n" +
                "CBAD\n" +
                "tally";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        ballotReader = new BallotConsoleReader(inputStream);

        assertThat(ballotReader.loadBallots(VALID_CANDIDATE_OPTIONS)).isEqualTo(provideValidBallots());
    }

    @Test
    @DisplayName("Given a ballot with same option twice,Then method should discard that.")
    public void testLoadBallotsWithSameOptionTwice() throws IOException {
        final String inputData = "ABDC\n" +
                "DA\n" +
                "BAD\n" +
                "DB\n" +
                "CABD\n" +
                "BAC\n" +
                "CDAB\n" +
                "CDABB\n" +
                "CBAD\n" +
                "tally";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        ballotReader = new BallotConsoleReader(inputStream);

        assertThat(ballotReader.loadBallots(VALID_CANDIDATE_OPTIONS)).isEqualTo(provideValidBallots());
    }

    @Test
    @DisplayName("Given a ballot with an option does not correspond to any candidate,Then method should discard that.")
    public void testLoadBallotsWithInvalidOption() throws IOException {
        final String inputData = "ABDC\n" +
                "DA\n" +
                "BAD\n" +
                "DB\n" +
                "CABD\n" +
                "BAC\n" +
                "CDAB\n" +
                "CDABZ\n" +
                "CBAD\n" +
                "tally";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        ballotReader = new BallotConsoleReader(inputStream);

        assertThat(ballotReader.loadBallots(VALID_CANDIDATE_OPTIONS)).isEqualTo(provideValidBallots());
    }

}