package app.main;

import app.repository.BallotConsoleReader;
import app.repository.BallotReader;
import app.repository.CandidateFileReader;
import app.repository.CandidateReader;
import app.service.VoteCountingStrategyFactory;
import app.service.VotingSystemProcessor;

import static app.util.console.ConsoleWriter.printError;
import static app.util.console.ConsoleWriter.printInfo;

public class Application {

    public static void main(String[] args) {
        final CandidateReader candidateReader = new CandidateFileReader();
        final VoteCountingStrategyFactory voteCountingStrategyFactory = new VoteCountingStrategyFactory();
        final BallotReader ballotReader = new BallotConsoleReader(System.in);

        final VotingSystemProcessor votingSystemProcessor = new VotingSystemProcessor(candidateReader, voteCountingStrategyFactory, ballotReader);

        try {
            printInfo(String.format("Vote count is completed. The winner is '%s'",
                    votingSystemProcessor.processVotes("preferentialVoteCounter")));
        } catch (Exception e) {
            printError(e);
        }
    }
}

