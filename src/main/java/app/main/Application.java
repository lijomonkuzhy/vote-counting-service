package app.main;

import app.repository.CandidateFileReader;
import app.service.BallotLoader;
import app.service.CandidateLoader;
import app.service.PreferentialVoteCounter;
import app.service.VoteCounter;

import static app.service.ConsoleWriter.printError;

public class Application {

    public static void main(String[] args) {
        final CandidateFileReader candidateFileReader = new CandidateFileReader();
        final CandidateLoader candidateLoader = new CandidateLoader(candidateFileReader);
        final PreferentialVoteCounter preferentialVoteCounter = new PreferentialVoteCounter();
        final BallotLoader ballotLoader = new BallotLoader();

        VoteCounter voteCounter = new VoteCounter(candidateLoader, preferentialVoteCounter, ballotLoader);

        try {
            voteCounter.processVotes();
        } catch (Exception e) {
            printError(e);
        }
    }
}

