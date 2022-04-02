package app.service;

import app.exception.ServiceException;
import app.model.Ballot;
import app.model.Candidate;
import app.util.CollectionValidator;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class VoteCounter {
    private final CandidateLoader candidateLoader;
    private final PreferentialVoteCounter preferentialVoteCounter;
    private final BallotLoader ballotLoader;

    public VoteCounter(final CandidateLoader candidateLoader,
                       final PreferentialVoteCounter preferentialVoteCounter,
                       final BallotLoader ballotLoader) {
        this.candidateLoader = candidateLoader;
        this.preferentialVoteCounter = preferentialVoteCounter;
        this.ballotLoader = ballotLoader;
    }

    public String processVotes() throws IOException, ServiceException {
        final Set<Candidate> candidates = candidateLoader.loadCandidateList();
        if (CollectionValidator.isNullOrEmpty(candidates)) {
            throw new ServiceException("No Candidates available.");
        }
        //printOptions(candidateList);
        final List<Ballot> ballots = ballotLoader.loadBallots();
        return preferentialVoteCounter.findTheWinner(candidates, ballots);
    }
}

