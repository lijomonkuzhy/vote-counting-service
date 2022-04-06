package app.main;

import app.repository.BallotConsoleReader;
import app.repository.CandidateFileReader;
import app.service.VoteCountingStrategyFactory;
import app.service.VotingSystemProcessor;

import static app.util.console.ConsoleWriter.printError;
import static app.util.console.ConsoleWriter.printInfo;

public class Application {

    public static void main(String[] args) {
        final CandidateFileReader candidateFileReader = new CandidateFileReader();
        final VoteCountingStrategyFactory voteCountingStrategyFactory = new VoteCountingStrategyFactory();
        final BallotConsoleReader ballotReader = new BallotConsoleReader(System.in);

        final VotingSystemProcessor votingSystemProcessor = new VotingSystemProcessor(candidateFileReader, voteCountingStrategyFactory, ballotReader);

        try {
            printInfo(String.format("Vote count is completed. The winner is '%s'",
                    votingSystemProcessor.processVotes("preferentialVoteCounter")));
        } catch (Exception e) {
            printError(e);
        }
    }
}

