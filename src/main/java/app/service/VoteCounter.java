package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import app.repository.CandidateFileReader;
import app.util.CollectionValidator;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

public class VoteCounter {
    private static final String CANDIDATE_FILE_PATH = "src/main/resources/candidates.txt";

    private final CandidateFileReader candidateFileReader;
    private final PreferentialVoteCounter preferentialVoteCounter;
    private final BallotLoader ballotLoader;

    public VoteCounter(final CandidateFileReader candidateFileReader,
                       final PreferentialVoteCounter preferentialVoteCounter,
                       final BallotLoader ballotLoader) {
        this.candidateFileReader = candidateFileReader;
        this.preferentialVoteCounter = preferentialVoteCounter;
        this.ballotLoader = ballotLoader;
    }

    public String processVotes() throws IOException, ServiceException {
        final LinkedHashSet<Candidate> candidates = candidateFileReader.loadCandidateList(CANDIDATE_FILE_PATH);
        if (CollectionValidator.isNullOrEmpty(candidates)) {
            throw new ServiceException("No Candidates available.");
        }
        //printOptions(candidateList);
        final List<Ballot> ballots = ballotLoader.loadBallots();
        return preferentialVoteCounter.findTheWinner(candidates, ballots);
    }
}

