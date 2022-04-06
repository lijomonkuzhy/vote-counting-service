package app.service;

import app.model.Ballot;
import app.model.Candidate;

import java.util.List;
import java.util.Set;

public interface VoteCounter {
    String findTheWinner(final Set<Candidate> candidates, final List<Ballot> ballots) throws Exception;
}
