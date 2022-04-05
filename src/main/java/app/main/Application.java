package app.main;

import app.repository.CandidateFileReader;
import app.repository.BallotReader;
import app.service.PreferentialVoteCounter;
import app.service.VoteCounter;

import static app.util.console.ConsoleWriter.printError;
import static app.util.console.ConsoleWriter.printInfo;

public class Application {

    public static void main(String[] args) {
        final CandidateFileReader candidateFileReader = new CandidateFileReader();
        final PreferentialVoteCounter preferentialVoteCounter = new PreferentialVoteCounter();
        final BallotReader ballotReader = new BallotReader(System.in);

        final VoteCounter voteCounter = new VoteCounter(candidateFileReader, preferentialVoteCounter, ballotReader);

        try {
            printInfo("Vote count is completed. The winner is: " + voteCounter.processVotes());
        } catch (Exception e) {
            printError(e);
        }
    }
}

